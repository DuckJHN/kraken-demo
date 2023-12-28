package com.example.kraken.client;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class KrakenClient {

    public CompletableFuture<String> queryPublicEndpoint(String endPointName, String inputParameters) {
        CompletableFuture<String> jsonData;
        String baseDomain = "https://api.kraken.com";
        String publicPath = "/0/public/";
        String apiEndpointFullURL = baseDomain + publicPath + endPointName + "?" + inputParameters;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiEndpointFullURL)).build();
        jsonData = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body);
        return jsonData;
    }

    public String queryPrivateEndpoint(String endPointName, String inputParameters, String apiPublicKey,
                                              String apiPrivateKey) {

        StringBuilder responseJson = new StringBuilder();
        String baseDomain = "https://api.kraken.com";
        String privatePath = "/0/private/";
        String apiEndpointFullURL = baseDomain + privatePath + endPointName;
        String nonce = String.valueOf(System.currentTimeMillis());
        String apiPostBodyData = "nonce=" + nonce + "&" + inputParameters;
        String signature = generateSignature(apiPrivateKey, privatePath, endPointName, nonce,
                apiPostBodyData);
        
        try {
            HttpsURLConnection httpConnection = null;
            URL apiUrl = new URL(apiEndpointFullURL);
            httpConnection = (HttpsURLConnection) apiUrl.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("API-Key", apiPublicKey);
            httpConnection.setRequestProperty("API-Sign", signature);
            httpConnection.setDoOutput(true);


            DataOutputStream os = new DataOutputStream(httpConnection.getOutputStream());
            os.writeBytes(apiPostBodyData);
            os.flush();
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            String line;
            while ((line = br.readLine()) != null) {
                responseJson.append(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return responseJson.toString();
    }


    public String generateSignature(String apiPrivateKey, String apiPath, String endPointName,
                                    String nonce, String apiPostBodyData) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedMessage = digest.digest((nonce + apiPostBodyData).getBytes(StandardCharsets.UTF_8));

            byte[] secretKeyByte = Base64.getDecoder().decode(apiPrivateKey);
            SecretKey secretKey = new SecretKeySpec(secretKeyByte, "HmacSHA512");

            Mac hmac = Mac.getInstance("HmacSHA512");
            hmac.init(secretKey);
            String fullPath = apiPath + endPointName;

            byte[] content = ArrayUtils.addAll(fullPath.getBytes(StandardCharsets.UTF_8), encodedMessage);

            byte[] result = hmac.doFinal(content);
            return Base64.getEncoder().encodeToString(result);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonResponse(HttpsURLConnection connection) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())))  {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        }
    }
}
