package com.aiman.ezp.domain.subscription.service;

import com.aiman.ezp.domain.subscription.Constants;
import com.aiman.ezp.domain.subscription.SubscriptionAggregate;
import com.aiman.ezp.domain.subscription.service.type.SubTypeFactory;
import com.aiman.ezp.shared.BaseDomain;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
public class CreateSubscriptionAggregateFacadeImpl implements CreateSubscriptionFacade{

    private static final List<String> ALLOWED_TYPES = Arrays.asList(Constants.DAILY, Constants.MONTHLY,
            Constants.WEEKLY);

    @Override
    public SubscriptionAggregate createSubscription(BaseDomain domain) throws ParseException {

        SubscriptionAggregate subscription = (SubscriptionAggregate)domain;

        /*validateBusinessLogicRules
        * Type are recognized
        * Duration is not more than 3 months
        * */
        checkBusinessRules(subscription);

        /*
        will generate the invoices and return data
        * */
        return generateInvoiceDates(subscription);
    }

    private SubscriptionAggregate generateInvoiceDates(SubscriptionAggregate subscription) throws ParseException {

        /*at this point need to check for Subscription Type and based on that it'll route the logic based on sub type*/
        return SubTypeFactory.SUB_TYPE_IMPL_MAP.get(subscription.getCommonCreateSubscriptionDto().
                getSubType()).executeLogic(subscription);

    }

    /**
     * checks type
     * start date before end date
     * start date != end date
     * start date end date format
     * start date and endd date diff  < 3 months
     * @param subscription
     */
    private void checkBusinessRules(SubscriptionAggregate subscription) {

        Assert.isTrue(ALLOWED_TYPES.contains(subscription.getCommonCreateSubscriptionDto().getSubType()), "Subscription Type Error");

        Assert.isTrue(!subscription.getStartDate().equals(subscription.getEndDate()), "Date Start Error");

        Assert.isTrue(subscription.getStartDate().before(subscription.getEndDate()), "Date Start Error");

        Assert.isTrue((subscription.getStartDate().toLocalDate().until(subscription.getEndDate().toLocalDate(),
                ChronoUnit.MONTHS)) <= 3, "Date Duration Error");

    }


}
