package com.api.search.controller;

import com.api.search.form.request.SearchRequest;
import com.api.search.form.response.PlaceResponse;
import com.api.search.service.SearchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/keyword")
    public List<PlaceResponse.FindAll> findAll(@Valid SearchRequest find) throws Exception {
        return searchService.findAll(find);
    }

    /*@GetMapping("/keyword/list")
    public List<SearchForm.Response.SearchList> searchList() {
        return null;
    }*/
}
