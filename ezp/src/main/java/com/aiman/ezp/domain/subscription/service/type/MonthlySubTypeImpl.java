package com.aiman.ezp.domain.subscription.service.type;

import com.aiman.ezp.domain.subscription.Constants;
import com.aiman.ezp.domain.subscription.SubscriptionAggregate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class MonthlySubTypeImpl implements SubType {

    @Override
    public String getType() {
        return Constants.MONTHLY;
    }

    /**
     * if day of month matches the day from user
     * creates the invoice date
     * @param subscription
     * @return
     * @throws ParseException
     */
    @Override
    public SubscriptionAggregate executeLogic(SubscriptionAggregate subscription) throws ParseException {
        long between =
                ChronoUnit.DAYS.between(subscription.getStartDate().toLocalDate(), subscription.getEndDate().toLocalDate());

        Integer subscriptionDay = Integer.valueOf(subscription.getCommonCreateSubscriptionDto().getSubscriptionDay());
        LocalDate startDate = subscription.getStartDate().toLocalDate();

        for(int i =1; i <= between; i++) {
            LocalDate nextDate = startDate.plus(i, ChronoUnit.DAYS);

            if( nextDate.getDayOfMonth() == subscriptionDay) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateFormat = nextDate.format(formatter);
                subscription.getInvoiceDates().add(dateFormat);
            }

        }
        return subscription;
    }

    @PostConstruct
    public void postDo(){
        SubTypeFactory.SUB_TYPE_IMPL_MAP.put(getType(), this);
    }
}
