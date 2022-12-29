//package com.api.search.form;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.List;
//
//public class SearchForm {
//
//    public static class Request {
//
//        @Data
//        public static class Find {
//            @ApiModelProperty(value = "검색을 원하는 질의어", required = true)
//            private String query;
//        }
//    }
//
//    public static class Response {
//
//        @Builder(toBuilder=true)
//        @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
//        public static class FindAll {
//            @ApiModelProperty(value="장소 목록")
//            private List<placeName> placeNameList;
//            @Data
//            public static class placeName {
//                @ApiModelProperty(value="장소명")
//                private String placeName;
//            }
//        }
//
//        @Data
//        public static class SearchList {
//
//        }
//    }
//}
