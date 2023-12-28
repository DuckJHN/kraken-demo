package com.example.kraken.utils;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;

@Component
public class KrakenUtils {
    public static String generateKrakenSignature(String urlPath, Map<String, Object> data, String secret) {
        try {
            String nonce = String.valueOf(System.currentTimeMillis());
            String postData = "nonce=" + nonce + "&" + getQueryString(data);
            byte[] sha256Hash = MessageDigest.getInstance("SHA-256").digest(postData.getBytes(StandardCharsets.UTF_8));

            String hmacSha512 = getHmacSha512(urlPath.getBytes(StandardCharsets.UTF_8), sha256Hash, secret);
            return Base64.getEncoder().encodeToString(hmacSha512.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getQueryString(Map<String, Object> data) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return queryString.toString();
    }

    private static String getHmacSha512(byte[] urlPath, byte[] sha256Hash, String secret) {
        try {
            javax.crypto.SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(
                    Base64.getDecoder().decode(secret), "HmacSHA512");
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA512");
            mac.init(secretKey);
            mac.update(urlPath);
            return new String(Base64.getEncoder().encode(mac.doFinal(sha256Hash)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
