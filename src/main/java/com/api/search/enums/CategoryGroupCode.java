package com.api.search.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum CategoryGroupCode {
    MT1(Arrays.asList("대형마트")),
    CS2(Arrays.asList("편의점")),
    PS3(Arrays.asList("어린이집", "유치원")),
    SC4(Arrays.asList("학교")),
    AC5(Arrays.asList("학원")),
    PK6(Arrays.asList("주차장")),
    OL7(Arrays.asList("주유소", "충전소")),
    SW8(Arrays.asList("지하철역")),
    BK9(Arrays.asList("은행")),
    CT1(Arrays.asList("문화시설")),
    AG2(Arrays.asList("중개업소")),
    PO3(Arrays.asList("공공기관")),
    AT4(Arrays.asList("관광명소")),
    AD5(Arrays.asList("숙박")),
    FD6(Arrays.asList("음식점")),
    CE7(Arrays.asList("카페")),
    HP8(Arrays.asList("병원")),
    PM9(Arrays.asList("약국")),
    EMPTY(Arrays.asList(""));

    @Getter
    private List<String> description;

    CategoryGroupCode(List<String> description){
        this.description = description;
    }

    public static CategoryGroupCode findByCategoryGroupCode(String code) {
        return Arrays.stream(CategoryGroupCode.values()).filter(categoryGroup -> categoryGroup.hashCategoryGroupCode(code)).findAny().orElse(EMPTY);
    }

    public boolean hashCategoryGroupCode(String code) {
        return description.stream().anyMatch(categoryCode -> categoryCode.equals(code));
    }
}
