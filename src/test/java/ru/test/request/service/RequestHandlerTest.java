package ru.test.request.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import ru.test.request.Application;
import ru.test.request.model.StockInfo;
import ru.test.request.model.command.CreateRequest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RequestHandlerTest {

    @Autowired
    private RequestHandler handler;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${service.stock.url}")
    private String serviceUrl;

    @Test
    public void createRequest() throws Exception {

        String stockCode = "GMK";
        var mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(serviceUrl + "/api/v1/info/" + stockCode)))
                .andRespond(withSuccess(objectMapper.writeValueAsString(
                        new StockInfo(stockCode, 50, BigDecimal.valueOf(10_000))), APPLICATION_JSON));

        handler.createRequest(new CreateRequest(UUID.randomUUID(), UUID.randomUUID(), stockCode, 5, LocalDateTime.now()));

        mockServer.verify();
    }
}