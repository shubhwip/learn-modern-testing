package me.shubhamjain.staticmethod;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

import java.io.IOException;

public class CloudKmsUtils {
  public static String decrypt(String projectId, String locationId, String keyRingId, String keyId, byte[] ciphertext)
            throws IOException {
        try (KeyManagementServiceClient client = KeyManagementServiceClient.create()) {
            CryptoKeyName keyName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);
            DecryptResponse response = client.decrypt(keyName, ByteString.copyFrom(ciphertext));
            return response.getPlaintext().toStringUtf8();
        }
    }

}
