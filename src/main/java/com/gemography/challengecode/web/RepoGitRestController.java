package com.gemography.challengecode.web;

import com.gemography.challengecode.service.RepositorieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RepoGitRestController {
    private RepositorieService repositorieService;

    @GetMapping("/repositorie")
    public ResponseEntity<?> getNbrRepoUseSameLanguage() throws IOException {
        return ResponseEntity.ok(this.repositorieService.getNbrRepoUseSameLanguage());
    }

    @GetMapping("/repositorie/statistique")
    public ResponseEntity<?> getRepoNbrWithLanguage() {
        return ResponseEntity.ok(this.repositorieService.getRepoNbrWithLanguage());
    }
}
