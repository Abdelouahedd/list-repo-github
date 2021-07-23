package com.gemography.challengecode.service.imp;

import com.gemography.challengecode.dto.RepoDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, List<Repository>> mapListDesRepoParLanguage = listRepos.getBody().items.stream()
                .filter(r -> r.getLanguage() != null)
                .collect(groupingBy(Repository::getLanguage));

        return mapListDesRepoParLanguage;
    }

    @Override
    public List<RepoDto> getRepoNbrWithLanguage() {
        Map<String, List<Repository>> listMap = this.getNbrRepoUseSameLanguage();
        List<RepoDto> list = new ArrayList<>();
        listMap.forEach((k, v) -> mapToRepoDto(list, k, v));
        return list;
    }

    private void mapToRepoDto(List<RepoDto> list, String k, List<Repository> v) {
        RepoDto r = new RepoDto();
        r.setLanguage(k);
        v.stream().map(re -> r.getRepoUrl().add(re.getHtmlUrl())).collect(Collectors.toList());
        r.setNbrRepo(v.size());
        list.add(r);
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

