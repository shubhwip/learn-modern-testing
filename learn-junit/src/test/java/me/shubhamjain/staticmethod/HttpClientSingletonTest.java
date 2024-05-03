package me.shubhamjain.staticmethod;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.http.HttpClient;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class HttpClientSingletonTest {

    @Test
    void givenAClassWithStaticMethod_whenStaticMethodIsTested_thenStaticMethodShouldBeCalled() {
        try(MockedStatic<HttpClientSingleton> httpClientSingletonMockedStatic = Mockito.mockStatic(HttpClientSingleton.class)) {
            // Arrange
            HttpClient mockedHttpClient = mock(HttpClient.class);
            // Act
            httpClientSingletonMockedStatic.when(HttpClientSingleton::getHttpClientInstance)
                    .thenReturn(mockedHttpClient);
            HttpClientSingleton.getHttpClientInstance();
            // Assert
            httpClientSingletonMockedStatic.verify(HttpClientSingleton::getHttpClientInstance, times(1));
        }

    }
}
