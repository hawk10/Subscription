package com.aiman.ezp.domain.subscription.service;

import com.aiman.ezp.domain.subscription.SubscriptionAggregate;
import com.aiman.ezp.shared.BaseDomain;

import java.text.ParseException;

public interface CreateSubscriptionFacade {

    public SubscriptionAggregate createSubscription(BaseDomain baseDomain) throws ParseException;
}
