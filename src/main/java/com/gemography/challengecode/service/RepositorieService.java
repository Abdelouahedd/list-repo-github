package com.gemography.challengecode.service;

import com.gemography.challengecode.dto.RepoDto;

import java.io.IOException;

public interface RepositorieService {
    RepoDto getNbrRepoUseSameLanguage() throws IOException;
}
