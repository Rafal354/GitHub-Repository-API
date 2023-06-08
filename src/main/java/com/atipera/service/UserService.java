package com.atipera.service;

import com.atipera.model.repo.Branch;
import com.atipera.model.repo.Repository;
import com.atipera.model.reponse.RepositoryCollection;
import com.atipera.model.reponse.BranchInfo;
import com.atipera.model.reponse.ErrorResponse;
import com.atipera.model.reponse.RepositoryInfo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final String hostName = "https://api.github.com";
    private final RestTemplate restTemplate;

    public UserService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ResponseEntity<?> getUserRepositoriesInfo(String username, String header) {
        if (header.equals("application/xml")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ErrorResponse.response(406));
        }
        RepositoryCollection repositoryInfoList;
        try {
            repositoryInfoList = getRepositories(username);
        } catch (UnknownHostException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.response(500));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.response(404));
        }
        return ResponseEntity.status(HttpStatus.OK).body(repositoryInfoList);
    }

    private RepositoryCollection getRepositories(String username) throws UnknownHostException, HttpClientErrorException {

        ResponseEntity<List<Repository>> response = restTemplate.exchange(
                hostName + "/users/" + username + "/repos",
                HttpMethod.GET,
                getHttpEntity(),
                new ParameterizedTypeReference<>() {
                });

        return new RepositoryCollection(Objects.requireNonNull(response.getBody())
                .stream()
                .filter(repository -> !repository.isFork())
                .filter(repository -> !repository.getName().equals(username))
                .map(repository -> RepositoryInfo.builder()
                        .name(repository.getName())
                        .ownerLogin(repository.getOwner().getLogin())
                        .branchInfoList(getBranches(repository.getBranchesUrl().split("\\{")[0]))
                        .build())
                .collect(Collectors.toList()));
    }

    private List<BranchInfo> getBranches(String url) {

        ResponseEntity<List<Branch>> branchResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                getHttpEntity(),
                new ParameterizedTypeReference<>() {
                });

        return Objects.requireNonNull(branchResponse.getBody())
                .stream()
                .map(branch -> new BranchInfo(branch.getName(), branch.getCommit().getSha()))
                .collect(Collectors.toList());
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/vnd.github+json");
        headers.add("X-GitHub-Api-Version", "2022-11-28");
        return new HttpEntity<>(headers);
    }
}
