package com.api.search.service;
import com.api.search.form.request.SearchRequest;
import com.api.search.form.response.PlaceResponse;

import java.util.List;

public interface SearchService {
    List<PlaceResponse.FindAll> findAll(SearchRequest find) throws Exception;
}
