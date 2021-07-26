package com.gemography.challengecode.service;

import com.gemography.challengecode.dto.RepoDto;
import com.gemography.challengecode.model.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class RepositorieServiceTest {

    @Autowired
    private RepositorieService repositorieService;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testGetNbrRepoUseSameLanguage() throws IOException {
        String dateOfLastThirtyDays = this.returnLastThirty();
        URI uri = URI.create(String.format("https://api.github.com/search/repositories?q=created:%s&sort=stars&order=desc&per_page=100", dateOfLastThirtyDays));
        ResponseEntity<Response> listRepos = this.restTemplate.getForEntity(uri, Response.class);
        Assertions.assertThat(listRepos.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(listRepos.getBody()).isNotEqualTo(null);
        Assertions.assertThat(listRepos.getBody()).isInstanceOf(Response.class);
    }

    @Test
    void testGetRepoNbrWithLanguage() {
        List<RepoDto> repoDtoList = repositorieService.getRepoNbrWithLanguage();
        Assertions.assertThat(repoDtoList).isNotEqualTo(null);
    }

    private String returnLastThirty() {
        LocalDate now = LocalDate.now();
        LocalDate thirty = now.minusDays(30);
        return thirty.format(DateTimeFormatter.ISO_DATE);
    }

}