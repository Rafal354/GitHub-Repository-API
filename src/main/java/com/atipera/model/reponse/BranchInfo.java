package com.atipera.model.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BranchInfo {
    private String name;
    @JsonProperty("last_commit_sha")
    private String lastCommitSha;
}
