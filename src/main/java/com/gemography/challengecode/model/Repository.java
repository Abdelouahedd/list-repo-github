package com.gemography.challengecode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Repository implements Serializable {
    private Long id;
    @JsonProperty(value = "full_name")
    private String fullName;
    private String language;
    @JsonProperty("html_url")
    private String htmlUrl;
}
