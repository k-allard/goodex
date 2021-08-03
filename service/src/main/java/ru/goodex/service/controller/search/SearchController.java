package ru.goodex.service.controller.search;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.service.search.SearchService;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService)
    {
        this.searchService = searchService;
    }

    @GetMapping("/search/profiles/byFirstName")
    public ResponseEntity<Page<ProfileDTO>> searchProfilesByFirstName(@RequestParam String firstName) {
        Page<ProfileDTO> result = searchService.findByFirstName(firstName);
        return ResponseEntity.ok(result);
    }
}

