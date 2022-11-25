package com.gmail.kulacholeg.task_02.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Map;

@JsonRootName("amounts")
public class TrafficViolationAmounts {
    @JsonProperty("from_max_to_min")
    private final Map<String, Double> amounts;

    public TrafficViolationAmounts(Map<String, Double> violations) {
        this.amounts = violations;
    }

    public Map<String, Double> getAmounts() {
        return amounts;
    }
}
