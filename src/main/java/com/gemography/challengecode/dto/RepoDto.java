package com.gemography.challengecode.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RepoDto {
    Integer nbrRepo;
    List<String> repoUrl = new ArrayList<>();
    String language;
}
