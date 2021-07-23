package com.gemography.challengecode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class Response {
    @JsonProperty("total_count")
    private Long totalCount;
    @JsonProperty("incomplete_results")
    private boolean incompleteResults;
    private List<Repository> items;
}
