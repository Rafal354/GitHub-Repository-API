package com.atipera.model.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RepositoryInfo {
    private String name;
    @JsonProperty("owner")
    private String ownerLogin;
    @JsonProperty("branches")
    private List<BranchInfo> branchInfoList;
}
