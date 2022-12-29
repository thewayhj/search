package com.api.search.form.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
public class SearchRequest {
    @ApiModelProperty(value = "검색을 원하는 질의어", required = true)
    private String query;
}
