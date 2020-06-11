package com.example.shipping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
public class ShippingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingController.class);

    @PostMapping("/shipment")
    public Shipment createShipment(@RequestBody Shipment newShipment) {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Shipped order", kv("shipment", objectMapper.convertValue(newShipment, new TypeReference<Map<String, Object>>() {})));
        return newShipment;
    }
}
