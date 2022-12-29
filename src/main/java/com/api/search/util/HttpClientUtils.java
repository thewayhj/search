package com.api.search.util;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.impl.DefaultConnectionReuseStrategy;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HttpClientUtils {

    @Setter
    private String url = "";
    private int maxTotalSize = 300;
    private int maxConnPerRoute = 200;
    private long connRequestTimeout = 10000;
    private long connTimeout = 10000;
    private int responseTimeout = 10000;

    private RequestConfig requestConfig;
    private CloseableHttpClient client;
    private PoolingHttpClientConnectionManager cm;

    private List<Header> headerList = new ArrayList<>();

    public HttpClientUtils(String url) {
        this.url = url;

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(connRequestTimeout))
                .setConnectTimeout(Timeout.ofMilliseconds(connTimeout))
                .setResponseTimeout(Timeout.ofMilliseconds(responseTimeout)).build();

        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxTotalSize);
        cm.setDefaultMaxPerRoute(maxConnPerRoute);
        cm.closeIdle(TimeValue.ZERO_MILLISECONDS);

        client = HttpClientBuilder.create()
                .setConnectionManager(cm)
                .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
                .build();
    }

    public String getUrl() {
        HttpGet request = new HttpGet(url);

        if (headerList != null && !headerList.isEmpty()) {
            request.setHeaders(headerList.toArray((Header[]) headerList.toArray(new Header[0])));
        }
        request.setConfig(requestConfig);
        String returnValue = execute(request, "UTF-8");

        return returnValue;
    }

    public void addHeader(String name, String value) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("name is empty(Header)!!");
        }

        if (value != null) {
            headerList.add(new BasicHeader(name, value));
        }
    }

    private String execute(HttpUriRequestBase request, String resCharSet) {
        CloseableHttpResponse response = null;
        String result = null;
        try {
            try {
                response = client.execute(request);
                result = EntityUtils.toString(response.getEntity(), resCharSet);
                //log.info("[result][" + response.getCode() + "][" + result + "]");
            } finally {
                response.close();
            }
        } catch (IOException | ParseException e) {
            log.error("error message : "+e.getMessage());
        } finally {
            cm.close();
        }
        return result;
    }
}
