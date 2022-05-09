package com.aiman.ezp.domain.subscription.dto;

import com.aiman.ezp.shared.dto.BaseRequest;

public class CommonCreateSubscriptionDto extends BaseRequest {

    private double amount;
    private String subType;
    private String subscriptionDay;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubscriptionDay() {
        return subscriptionDay;
    }

    public void setSubscriptionDay(String subscriptionDay) {
        this.subscriptionDay = subscriptionDay;
    }
}
