package com.aiman.ezp.api;

import com.aiman.ezp.domain.subscription.SubscriptionAggregate;
import com.aiman.ezp.domain.subscription.dto.CommonCreateSubscriptionDto;
import com.aiman.ezp.domain.subscription.dto.CreateSubscriptionResponseDto;
import com.aiman.ezp.domain.subscription.service.CreateSubscriptionAggregateFacadeImpl;
import com.aiman.ezp.shared.constant.ResponseCode;
import com.aiman.ezp.shared.dto.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;


public class CreateSubscriptionControllerTest {

    @InjectMocks
    CreateSubscriptionController createSubscriptionController;

    @Mock
    private CreateSubscriptionAggregateFacadeImpl createSubscriptionAggregateFacade;

    @BeforeEach
    public void init() throws ParseException {
        MockitoAnnotations.openMocks(this);

        SubscriptionAggregate subscriptionAggregate = new SubscriptionAggregate();
        CommonCreateSubscriptionDto commonCreateSubscriptionDto = new CommonCreateSubscriptionDto();
        commonCreateSubscriptionDto.setAmount(10D);
        subscriptionAggregate.setCommonCreateSubscriptionDto(commonCreateSubscriptionDto);
        Mockito.when(createSubscriptionAggregateFacade.createSubscription(Mockito.any())).thenReturn(subscriptionAggregate);
    }

    @Test
    public void success(){
        CreateSubscriptionResponseDto subscription = (CreateSubscriptionResponseDto)createSubscriptionController.createSubscription("{\n" +
                "    \"commonCreateSubscriptionDto\": {\n" +
                "    \"amount\": 10,\n" +
                "     \"subscriptionDay\":\"Wednesday\",\n" +
                "    \"subType\": \"WEEKLY\"\n" +
                "    },\n" +
                "    \"startDate\":\"11/05/2022\",\n" +
                "    \"endDate\":\"11/07/2022\"\n" +
                "\n" +
                "}");

        Assertions.assertEquals(10D, subscription.getCommonCreateSubscriptionDto().getAmount());
    }

    @Test()
    public void Fail_Date_Missing() throws ParseException {

        BaseResponse subscription = createSubscriptionController.createSubscription("{\n" +
                "    \"commonCreateSubscriptionDto\": {\n" +
                "    \"amount\": 10,\n" +
                "     \"subscriptionDay\":\"Wednesday\"\n" +
                "    },\n" +
                "    \"startDate\":\"11/05/2022\"\n" +
                "\n" +
                "}");

        Assertions.assertEquals(ResponseCode.FAIL, subscription.getResponseCode());
    }
}
