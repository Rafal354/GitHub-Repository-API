package com.atipera.model.reponse;


import lombok.Getter;

import java.util.List;

@Getter
public class RepositoryCollection {
    List<RepositoryInfo> repositories;

    public RepositoryCollection(List<RepositoryInfo> repositories) {
        this.repositories = repositories;
    }
}
