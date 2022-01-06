package com.gopi.works;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class ProductInventoryClientImpl implements ProductInventoryClient {

    Logger logger = LoggerFactory.getLogger("ProductInventoryClientImpl.class");

    @Override
    public Integer getProductInventories() {

        var response = "";

        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8946/inventories"))
                .build();
        var client = HttpClient.newHttpClient();
        try {
            var httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            response = httpResponse.body();
        } catch (IOException ioe) {
            logger.error("IOException Occurred", ioe);
        } catch (InterruptedException ie) {
            logger.error("InterruptedException Occurred", ie);
            Thread.currentThread().interrupt();
        }
        if ("".equalsIgnoreCase(response)) {
            return null;
        } else {
            return Integer.parseInt(response);
        }
    }

    }

