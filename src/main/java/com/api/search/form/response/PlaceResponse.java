package com.api.search.form.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@RequiredArgsConstructor
public class PlaceResponse {
    private static FindAll placeNameList;

    @Data
    @Builder(toBuilder=true)
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class FindAll {
        @ApiModelProperty(value="장소명")
        private String placeName;
    }
}
