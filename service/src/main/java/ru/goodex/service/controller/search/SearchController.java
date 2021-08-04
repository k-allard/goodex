package ru.goodex.service.controller.search;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.goodex.service.entity.profile.ProfileDTO;
import ru.goodex.service.service.search.SearchService;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService)
    {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String index()
    {
        return "search.form";
    }


    @GetMapping("/search/profiles/byFirstName")
    public String searchProfilesByFirstName(Model model, @RequestParam(required = false) String firstName) {
        Page<ProfileDTO> result = searchService.findByFirstName(firstName);
        model.addAttribute("usersFound", result.getContent());
        return "search.profiles";
    }
}

