package com.example.orders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.example.orders.OrdersApplication.RANDOM_DROP_SHIPPING;
import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
public class OrdersController {

    Map<String, Boolean> featureMap;

    public OrdersController(Map featureMap) {
        this.featureMap = featureMap;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order newOrder) {
        LOGGER.info("Created new order", kv("order", from(newOrder)));

        if (!featureMap.get(RANDOM_DROP_SHIPPING)) {
            submitForShipping(newOrder);
        }

        return newOrder;
    }

    @GetMapping("/exception")
    public String printStackTrace() {
        try {
            Integer.parseInt("somestring");
        } catch (Exception e) {
            LOGGER.error("Unable to parse string",e);
        }
        return "Test";
    }

    /**
     * Converts POJO to Map for structured logging
     * @param order
     * @return
     */
    private Map from(Order order) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(order, new TypeReference<Map<String, Object>>() {});
    }

    private void submitForShipping(final Order order) {
        Map<String, Object> shipment = new HashMap<>();
        shipment.put("orderId", order.getOrderId());

        HttpEntity<Map> request = new HttpEntity<>(shipment);
        final String shippingURL = "http://localhost:9000/shipment";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(shippingURL, request, Map.class);
    }
}
