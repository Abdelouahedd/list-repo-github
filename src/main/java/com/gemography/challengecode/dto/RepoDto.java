package com.gemography.challengecode.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RepoDto {
    private Set<String> languages;
    private Long nbrRepo;
}
