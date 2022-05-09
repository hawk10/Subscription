package com.aiman.ezp.domain.subscription.dto;

import com.aiman.ezp.shared.dto.BaseResponse;

import java.util.List;

public class CreateSubscriptionResponseDto extends BaseResponse {

    private CommonCreateSubscriptionDto commonCreateSubscriptionDto;
    private List<String> invoiceDates;

    public CommonCreateSubscriptionDto getCommonCreateSubscriptionDto() {
        return commonCreateSubscriptionDto;
    }

    public void setCommonCreateSubscriptionDto(CommonCreateSubscriptionDto commonCreateSubscriptionDto) {
        this.commonCreateSubscriptionDto = commonCreateSubscriptionDto;
    }

    public List<String> getInvoiceDates() {
        return invoiceDates;
    }

    public void setInvoiceDates(List<String> invoiceDates) {
        this.invoiceDates = invoiceDates;
    }
}
