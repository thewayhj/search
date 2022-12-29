package com.api.search.service.Impl;

import com.api.search.enums.CategoryGroupCode;
import com.api.search.form.request.SearchRequest;
import com.api.search.form.response.PlaceResponse;
import com.api.search.service.SearchService;
import com.api.search.util.CommonUtils;
import com.api.search.util.HttpClientUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${property.domain.kakao}")
    private String kakaoDomain;

    @Value("${property.api.search.kakao}")
    private String kakaoApi;

    @Value("${property.client-info.kakao.rest-api-key}")
    private String kakaoRestApiKey;

    @Value("${property.domain.naver}")
    private String naverDomain;

    @Value("${property.api.search.naver}")
    private String naverApi;

    @Value("${property.client-info.naver.client-id}")
    private String naverClientId;

    @Value("${property.client-info.naver.client-secret}")
    private String naverClientSecret;

    @Value("${property.max-count.kakao}")
    private String kakaoMaxCount;

    @Value("${property.max-count.naver}")
    private String naverMaxCount;

    public List<PlaceResponse.FindAll> findAll(SearchRequest find) throws Exception {
        CategoryGroupCode categoryGroupCode = CategoryGroupCode.findByCategoryGroupCode(find.getQuery());


        //jpa insert 예정.

        String kakaoUrl = kakaoDomain + kakaoApi + "?query=" + URLEncoder.encode(find.getQuery(), StandardCharsets.UTF_8.toString())+ "&category_group_code=" + ("EMPTY".equals(categoryGroupCode.name()) ? "" : categoryGroupCode.name()) + "&size="+kakaoMaxCount;
        JSONObject kakaoResult = searchResult(kakaoUrl, "kakao");
        List<String> kakaoPlaceName = new ArrayList<>();
        if(BooleanUtils.isFalse(kakaoResult.isEmpty())) {
            JSONArray itemsArray = (JSONArray) kakaoResult.get("documents");
            for (int i = 0; i < itemsArray.size(); i++) {
                kakaoPlaceName.add(CommonUtils.removeTag(itemsArray.getJSONObject(i).get("place_name").toString()));
            }
        }

        String naverUrl = naverDomain + naverApi + "?query=" + URLEncoder.encode(find.getQuery(), StandardCharsets.UTF_8.toString()) + "&display="+naverMaxCount;
        JSONObject naverResult = searchResult(naverUrl, "naver");
        List<String> naverPlaceName = new ArrayList<>();
        if(BooleanUtils.isFalse(naverResult.isEmpty())) {
            JSONArray itemsArray = (JSONArray) naverResult.get("items");
            for (int i = 0; i < itemsArray.size(); i++)
                naverPlaceName.add(CommonUtils.removeTag(itemsArray.getJSONObject(i).get("title").toString()));
        }

        List<String> placeNames = new ArrayList<>();
        for(int i = 0; i < kakaoPlaceName.size(); i++) {
            for(int j = 0; j < naverPlaceName.size(); j++) {
                Double distance = CommonUtils.jaroWinklerDistance(kakaoPlaceName.get(i).replaceAll(" ", ""), naverPlaceName.get(j).replaceAll(" ", ""));
                if (distance < 0.2)
                    placeNames.add(kakaoPlaceName.get(i));
            }
        }

        List<String> kakaofilteredList = kakaoPlaceName.stream()
                                                .filter(target -> placeNames.stream().noneMatch(Predicate.isEqual(target)))
                                                .collect(Collectors.toList());

        List<String> naverfilteredList = naverPlaceName.stream()
                                                        .filter(target -> placeNames.stream().noneMatch(Predicate.isEqual(target)))
                                                        .collect(Collectors.toList());

        addPlaceName(placeNames, kakaofilteredList);
        addPlaceName(placeNames, naverfilteredList);

        List<PlaceResponse.FindAll> placeName = new ArrayList<>();
        for(int i=0; i< placeNames.size(); i++)
            placeName.addAll(Lists.newArrayList(PlaceResponse.FindAll.builder().placeName(placeNames.get(i)).build()));

        return placeName;
    }

    public void addPlaceName(List<String> placeNames, List<String> list) {
        for(String item : list)
            placeNames.add(item);
    }

    public JSONObject searchResult(String url, String service) {
        try {
            HttpClientUtils client = new HttpClientUtils(url);
            setAddHeader(client, service);
            return JSONObject.fromObject(client.getUrl());
        } catch (Exception e) {
            log.error("open api [" +service+ "] error [reason] : " + e.getMessage());
        }
        return new JSONObject();
    }

    private void setAddHeader(HttpClientUtils client, String service) {
        client.addHeader("Content-Type", "application/json");
        switch (service) {
            case "kakao":
                client.addHeader("Authorization", "KakaoAK" + StringUtils.SPACE + kakaoRestApiKey);
                break;
            case "naver":
                client.addHeader("X-Naver-Client-Id", naverClientId);
                client.addHeader("X-Naver-Client-Secret", naverClientSecret);
                break;
            default:
                break;
        }
    }
}
