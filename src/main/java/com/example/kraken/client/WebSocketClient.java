package com.example.kraken.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;

public class WebSocketClient implements WebSocket.Listener {
    private final CountDownLatch latch;

    public WebSocketClient(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + ": " + webSocket.getSubprotocol());
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now) + ": " + data);
        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    public static void OpenAndStreamWebSocketSubscription(String connectionURL, String webSocketSubscription) {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            WebSocket ws = HttpClient.newHttpClient().newWebSocketBuilder().buildAsync(URI.create(connectionURL),
                    new WebSocketClient(latch)).join();
            ws.sendText(webSocketSubscription, true);
            latch.await();
        } catch (Exception e) {
            System.out.println();
            System.out.println("AN EXCEPTION OCCURRED :(");
            System.out.println(e);
        }
    }
}