package com.api.search.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.JaroWinklerDistance;

public class CommonUtils {
    /**
     * 모든 HTML 태그를 제거하고 반환한다.
     * @param html
     */
    public static String removeTag(String html)  {
        return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    public static Double jaroWinklerDistance(String firstTarget, String SecondTarget) {
        JaroWinklerDistance distance = new JaroWinklerDistance();
        return distance.apply(firstTarget, SecondTarget);
    }
}
