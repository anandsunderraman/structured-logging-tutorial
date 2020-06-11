package com.example.orders;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "features")
public class FeaturesEndPoint {

    private Map<String, Boolean> featureMap;

    public FeaturesEndPoint(Map<String, Boolean> featureMap) {
        this.featureMap = featureMap;
    }

    @ReadOperation
    public Map<String, Boolean> features() {
        return featureMap;
    }

    @WriteOperation
    public Map<String, Boolean> updateFeature(@Selector final String name, final Boolean value) {
        featureMap.put(name, value);
        return featureMap;
    }
}
