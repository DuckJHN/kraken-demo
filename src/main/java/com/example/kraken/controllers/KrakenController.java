package com.example.kraken.controllers;

import com.example.kraken.client.KrakenClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.example.kraken.utils.StringUtils.convertDataToInputParameters;

@Slf4j
@RestController
@RequestMapping("/kraken")
@RequiredArgsConstructor
public class KrakenController {
    private final KrakenClient krakenClient;

    String apiPublicKey = "0mmVABOR2odGWWsfSlpdyvnMEi7cGIGTRyJbapIq2ywhlyy/+DDCLl0K";
    String apiPrivateKey = "vc+Bqwb4I9InD2J8EV7QZiP+7yAYV9ukV1Fw7y+XvPr1y/k3yCCzeY5nKMf9WiPNVlMErd+gPfJQVYy539UPvg==";

    @GetMapping("/assets")
    public CompletableFuture<ResponseEntity<String>> getAssets() {
        String endPointName = "AssetPairs";
        String inputParameters = "pair=ethusd,xbtusd";
        return krakenClient.queryPublicEndpoint(endPointName, inputParameters)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/DepositAddresses")
    public ResponseEntity<String> getBalance() {
        String endPointName = "DepositAddresses";

        // Create new deposit set true or else set false
        Map<String, Object> data = Map.of(
                "asset","XBT",
                "method", "Bitcoin",
                "new",true
        );
        String inputParameters = convertDataToInputParameters(data);

        String responseJson = krakenClient.queryPrivateEndpoint(endPointName, inputParameters, apiPublicKey, apiPrivateKey);
        return ResponseEntity.ok(responseJson);
    }

    @GetMapping("/WithdrawAddresses")
    public ResponseEntity<String> getBalance(@RequestBody Map<String, Object> requestBody) {
        String endPointName = "WithdrawAddresses";

        String inputParameters = convertDataToInputParameters(requestBody);

        String responseJson = krakenClient.queryPrivateEndpoint(endPointName, inputParameters, apiPublicKey, apiPrivateKey);
        return ResponseEntity.ok(responseJson);
    }

}
