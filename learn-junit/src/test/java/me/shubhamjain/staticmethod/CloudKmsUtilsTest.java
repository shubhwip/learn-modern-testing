package me.shubhamjain.staticmethod;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CloudKmsUtilsTest {
    @Test
    void givenAEncodedKey_whenDecryptIsCalledForCloudKMS_thenReturnsDecryptedKey() throws IOException {
        try(MockedStatic<KeyManagementServiceClient> keyManagementServiceClientMockedStatic = Mockito.mockStatic(KeyManagementServiceClient.class)) {
            // Arrange
            KeyManagementServiceClient keyManagementServiceClient = mock(KeyManagementServiceClient.class);
            keyManagementServiceClientMockedStatic.when(KeyManagementServiceClient::create)
                    .thenReturn(keyManagementServiceClient);
            DecryptResponse decryptResponse = mock(DecryptResponse.class);
            CryptoKeyName keyName = CryptoKeyName.of("", "", "", "");
            when(keyManagementServiceClient.decrypt(keyName, ByteString.copyFrom("".getBytes(StandardCharsets.UTF_8)))).thenReturn(decryptResponse);
            when(decryptResponse.getPlaintext()).thenReturn(ByteString.copyFromUtf8("answer"));
            // Act
            String actual = CloudKmsUtils.decrypt("", "", "", "", "".getBytes(StandardCharsets.UTF_8));
            // Assert
            Assertions.assertEquals("answer", actual);
        }

    }
}
