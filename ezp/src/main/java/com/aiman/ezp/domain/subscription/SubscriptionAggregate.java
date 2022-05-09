package com.aiman.ezp.domain.subscription;

import com.aiman.ezp.domain.subscription.dto.CommonCreateSubscriptionDto;
import com.aiman.ezp.shared.BaseDomain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionAggregate extends BaseDomain {

    private CommonCreateSubscriptionDto commonCreateSubscriptionDto;
    private Date startDate;
    private Date endDate;
    private List<String> InvoiceDates = new ArrayList<>();

    public CommonCreateSubscriptionDto getCommonCreateSubscriptionDto() {
        return commonCreateSubscriptionDto;
    }

    public void setCommonCreateSubscriptionDto(CommonCreateSubscriptionDto commonCreateSubscriptionDto) {
        this.commonCreateSubscriptionDto = commonCreateSubscriptionDto;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<String> getInvoiceDates() {
        return InvoiceDates;
    }

    public void setInvoiceDates(List<String> invoiceDates) {
        InvoiceDates = invoiceDates;
    }
}
