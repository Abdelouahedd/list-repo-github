package com.gemography.challengecode.service.imp;

import com.gemography.challengecode.model.Repository;
import com.gemography.challengecode.service.RepositorieService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@AllArgsConstructor
@Slf4j
public class RepositorieServiceImp implements RepositorieService {
    private final RestTemplate restTemplate;

    @Override
    public Map<String, List<Repository>> getNbrRepoUseSameLanguage() {
        String dateOfLastThirtyDays = this.returnLastThirty();
        URI uri = URI.create(String.format("https://api.github.com/search/repositories?q=created:%s&sort=stars&order=desc&per_page=100", dateOfLastThirtyDays));
        ResponseEntity<Response> listRepos = this.restTemplate.getForEntity(uri, Response.class);
        Map<String, List<Repository>> listMap = listRepos.getBody().items.stream()
                .filter(r -> r.getLanguage() != null)
                .collect(groupingBy(Repository::getLanguage));
        return listMap;
    }

    private String returnLastThirty() {
        LocalDate now = LocalDate.now();
        LocalDate thirty = now.minusDays(30);
        return thirty.format(DateTimeFormatter.ISO_DATE);
    }


}

@Data
class Response {
    Long total_count;
    boolean incomplete_results;
    List<Repository> items;
}