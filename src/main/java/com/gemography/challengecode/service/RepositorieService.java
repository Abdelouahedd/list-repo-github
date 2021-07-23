package com.gemography.challengecode.service;

import com.gemography.challengecode.dto.RepoDto;
import com.gemography.challengecode.model.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RepositorieService {
    Map<String, List<Repository>> getNbrRepoUseSameLanguage() throws IOException;

    List<RepoDto>getRepoNbrWithLanguage();
}
