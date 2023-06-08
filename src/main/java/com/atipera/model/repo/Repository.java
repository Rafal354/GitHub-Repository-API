package com.atipera.model.repo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Repository {
    private String name;
    private Owner owner;
    private boolean fork;
    @JsonProperty("branches_url")
    private String branchesUrl;
}
