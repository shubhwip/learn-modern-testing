package me.shubhamjain.staticmethod;

import java.net.http.HttpClient;

public class HttpClientSingleton {
    public static HttpClient getHttpClientInstance() {
        return HttpClientSingleton.HttpClientFactoryHelper.INSTANCE;
    }

    private HttpClientSingleton() {
    }

    private static class HttpClientFactoryHelper {
        private static final HttpClient INSTANCE = HttpClient.newHttpClient();

        private HttpClientFactoryHelper() {
        }
    }
}

