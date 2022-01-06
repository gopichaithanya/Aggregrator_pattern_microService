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
public class ProductInformationClientImpl implements ProductInformationClient{

    Logger logger = LoggerFactory.getLogger(ProductInformationClientImpl.class);


    @Override
    public String getProductTitle() {
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8945/information"))
                .build();
        var client = HttpClient.newHttpClient();
        try {
            var httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (IOException ioe) {
            logger.error("IOException Occurred", ioe);
        } catch (InterruptedException ie) {
            logger.error("InterruptedException Occurred", ie);
            Thread.currentThread().interrupt();
        }
        return null;
    }


}
