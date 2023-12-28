package com.example.kraken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KrakenApplication {

	public static void main(String[] args) {
		SpringApplication.run(KrakenApplication.class, args);


//        try {
//            System.out.println("|=========================================|");
//            System.out.println("| KRAKEN.COM JAVA TEST APP |");
//            System.out.println("|=========================================|");
//            System.out.println();
//
//            /** PUBLIC REST API */
//            if (1 == 0) {
//                CompletableFuture<String> publicResponse;
//                String publicEndpoint = "SystemStatus";
//                String publicInputParameters = "";
//                /** MORE PUBLIC REST EXAMPLES
//                 * String publicEndpoint = "AssetPairs";
//                 * String publicInputParameters = "pair=ethusd,xbtusd";
//                 * String publicEndpoint = "Ticker";
//                 * String publicInputParameters = "pair=ethusd";
//                 * String publicEndpoint = "Trades";
//                 * String publicInputParameters = "pair=ethusd&since=0";
//                 */
//                publicResponse = QueryPublicEndpoint(publicEndpoint, publicInputParameters);
//                while (!publicResponse.isDone()) {
//                    // WAIT
//                }
//                System.out.println(publicResponse.get());
//            }
//
//            /** PRIVATE REST API Examples */
//            if (1 == 0) {
//                String privateResponse = "";
//                String privateEndpoint = "Balance";
//                String privateInputParameters = "";
//                /** MORE PRIVATE REST EXAMPLES
//                 * String privateEndpoint = "AddOrder";
//                 * String privateInputParameters = "pair=xbteur&type=buy&ordertype=limit&price=1.00&volume=1";
//                 * String privateEndpoint = "AddOrder"
//                 * String privateInputParameters = "pair=xdgeur&type=sell&ordertype=limit&volume=3000&price=%2b10.0%" //Positive
//                 * Percentage Example (%2 represents +, which is a reserved character in HTTP)
//                 * String privateEndpoint = "AddOrder"
//                 * String privateInputParameters = "pair=xdgeur&type=sell&ordertype=limit&volume=3000&price=-10.0%" //Negative
//                 * Percentage Example
//                 * String privateEndpoint = "AddOrder"
//                 * String privateInputParameters = "pair=xdgeur&type=buy&ordertype=market&volume=3000&userref=789" //Userref
//                 * Example
//                 * String privateEndpoint = "Balance" //{"error":[]} IS SUCCESS, Means EMPTY
//                 * BALANCE
//                 * String privateInputParameters = ""
//                 * String privateEndpoint = "QueryOrders"
//                 * String privateInputParameters = "txid=OFUSL6-GXIIT-KZ2JDJ"
//                 * String privateEndpoint = "AddOrder"
//                 * String privateInputParameters = "pair=xdgusd&type=buy&ordertype=market&volume=5000"
//                 * String privateEndpoint = "DepositAddresses"
//                 * String privateInputParameters = "asset=xbt&method=Bitcoin"
//                 * String privateEndpoint = "DepositMethods"
//                 * String privateInputParameters = "asset=eth"
//                 * String privateEndpoint = "WalletTransfer"
//                 * String privateInputParameters = "asset=xbt&to=Futures Wallet&from=Spot Wallet&amount=0.0045"
//                 * String privateEndpoint = "TradesHistory"
//                 * String privateInputParameters = "start=1577836800&end=1609459200"
//                 * String privateEndpoint = "GetWebSocketsToken"
//                 * String privateInputParameters = ""
//                 */
//                privateResponse = QueryPrivateEndpoint(privateEndpoint, privateInputParameters, apiPublicKey,
//                        apiPrivateKey);
//                System.out.println(privateResponse);
//            }
//
//            /** PUBLIC WEBSOCKET Examples */
//            if (1 == 0) {
//                String publicWebSocketURL = "wss://ws.kraken.com/";
//                String publicWebSocketSubscriptionMsg = "{ \"event\":\"subscribe\", \"subscription\":{\"name\":\"trade\"},\"pair\":[\"XBT/USD\"] }";
//                /** MORE PUBLIC WEBSOCKET EXAMPLES
//                 * String publicWebSocketSubscriptionMsg =
//                 * "{ \"event\": \"subscribe\", \"subscription\": { \"interval\": 1440, \"name\": \"ohlc\"}, \"pair\": [ \"XBT/EUR\" ]}"
//                 * ;
//                 * String publicWebSocketSubscriptionMsg =
//                 * "{ \"event\": \"subscribe\", \"subscription\": { \"name\": \"spread\"}, \"pair\": [ \"XBT/EUR\",\"ETH/USD\" ]}"
//                 * ;
//                 */
//                OpenAndStreamWebSocketSubscription(publicWebSocketURL, publicWebSocketSubscriptionMsg);
//            }
//
//            /** PRIVATE WEBSOCKET Examples */
//            if (1 == 0) {
//                String privateWebSocketURL = "wss://ws-auth.kraken.com/";
//                // GET THE WEBSOCKET TOKEN FROM THE JSON RESPONSE
//                Object webSocketToken = "";
//                String tokenQuery = QueryPrivateEndpoint("GetWebSocketsToken", "", apiPublicKey, apiPrivateKey);
//                JsonFactory factory = new JsonFactory();
//                ObjectMapper mapper = new ObjectMapper(factory);
//                TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
//                };
//                HashMap<String, HashMap<String, Object>> o = mapper.readValue(tokenQuery, typeRef);
//                webSocketToken = o.get("result").get("token");
//                /** MORE PRIVATE WEBSOCKET EXAMPLES
//                 * String privateWebSocketSubscriptionMsg =
//                 * "{ \"event\": \"subscribe\", \"subscription\": { \"name\": \"openOrders\", \"token\": \"" + webSocketToken
//                 * + "\" }}";
//                 * String privateWebSocketSubscriptionMsg =
//                 * "{ \"event\": \"subscribe\", \"subscription\": { \"name\": \"balances\", \"token\": \"" + webSocketToken
//                 * + "\" }}";
//                 * String privateWebSocketSubscriptionMsg =
//                 * "{ \"event\":\"addOrder\",\"reqid\":1234,\"ordertype\":\"limit\",\"pair\":\"XBT/EUR\",\"token\":\"" +
//                 * webSocketToken + "\",\"type\":\"buy\",\"volume\":\"1\", \"price\":\"1.00\"}";
//                 * // REPLACE PLACEHOLDER WITH TOKEN
//                 * String privateWebSocketSubscriptionMsg = "{ \"event\": \"subscribe\", \"subscription\": { \"name\": \"balances\",
//                 * \"token\": \""+ webSocketToken + "\" }}";
//                 */
//                String privateWebSocketSubscriptionMsg = "{ \"event\": \"subscribe\", \"subscription\": { \"name\": \"balances\", \"token\": \""+ webSocketToken + "\" }}";
//
//                System.out.println(privateWebSocketSubscriptionMsg);
//                OpenAndStreamWebSocketSubscription(privateWebSocketURL, privateWebSocketSubscriptionMsg);
//            }
//
//            System.out.println("|=======================================|");
//            System.out.println("| END OF PROGRAM - HAVE A GOOD DAY :) |");
//            System.out.println("|=======================================|");
//            System.out.println("\n");
//        } catch (Exception e) {
//            System.out.println(e);
//        }

	}

}
