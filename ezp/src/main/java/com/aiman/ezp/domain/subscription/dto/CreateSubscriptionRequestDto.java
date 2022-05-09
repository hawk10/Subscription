package com.aiman.ezp.domain.subscription.dto;

import com.aiman.ezp.shared.dto.BaseRequest;

import java.sql.Date;

public class CreateSubscriptionRequestDto extends BaseRequest {

    private CommonCreateSubscriptionDto commonCreateSubscriptionDto;
    private Date startDate;
    private Date endDate;

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
}
