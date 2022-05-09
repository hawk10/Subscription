package com.aiman.ezp.domain.subscription.service.type;

import com.aiman.ezp.domain.subscription.SubscriptionAggregate;

import java.text.ParseException;

public interface SubType {

    public String getType();

    public SubscriptionAggregate executeLogic(SubscriptionAggregate subscriptionAggregate) throws ParseException;
}
