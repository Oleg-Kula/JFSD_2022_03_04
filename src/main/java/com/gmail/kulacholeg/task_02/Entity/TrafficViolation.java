package com.gmail.kulacholeg.task_02.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrafficViolation {
    @JsonProperty("date_time")
    private String dateTime;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("fine_amount")
    private double fineAmount;

    public String getType() {
        return type;
    }

    public double getFineAmount() {
        return fineAmount;
    }
}
