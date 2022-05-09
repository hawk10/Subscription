package com.aiman.ezp.api;

import com.aiman.ezp.domain.subscription.SubscriptionAggregate;
import com.aiman.ezp.domain.subscription.dto.CommonCreateSubscriptionDto;
import com.aiman.ezp.domain.subscription.dto.CreateSubscriptionRequestDto;
import com.aiman.ezp.domain.subscription.dto.CreateSubscriptionResponseDto;
import com.aiman.ezp.domain.subscription.service.CreateSubscriptionAggregateFacadeImpl;
import com.aiman.ezp.shared.BaseDomain;
import com.aiman.ezp.shared.constant.ResponseCode;
import com.aiman.ezp.shared.controller.AbstractBaseController;
import com.aiman.ezp.shared.dto.BaseRequest;
import com.aiman.ezp.shared.dto.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CreateSubscriptionController extends AbstractBaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CreateSubscriptionController.class);

    private static List<String> REQUIRED_FIELDS_ADD_USER = Arrays.asList("amount","subType","startDate","endDate");

    @Autowired
    private CreateSubscriptionAggregateFacadeImpl createSubscriptionAggregateFacade;

    @PostMapping(path = "api/createSubscription")
    public BaseResponse createSubscription(@RequestBody String body) {

        LOG.info("request: "+body);
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCode.SUCCESS);

        Gson gson=  new GsonBuilder().setLenient().setDateFormat("dd/MM/yyyy").create();
        try {
            CreateSubscriptionRequestDto dto = gson.fromJson(body, CreateSubscriptionRequestDto.class);

            /*
            checks if the mandatory request data is available
            * */
            response =
                    doCommonValidation(dto, response, CreateSubscriptionRequestDto.class);

            Assert.isTrue(ResponseCode.SUCCESS.equals(response.getResponseCode()), "Validation Failed");

            BaseDomain baseDomain = convert2Aggregate(dto);
            SubscriptionAggregate subscription = createSubscriptionAggregateFacade.createSubscription(baseDomain);

            return convert2Response(response, subscription);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            populateSystemError(response,e);
        }
        return response;
    }

    /**
     * @return the required fields in the request
     */
    @Override
    public List<String> getRequiredFields() {
        return REQUIRED_FIELDS_ADD_USER;
    }

    private CreateSubscriptionResponseDto convert2Response(BaseResponse baseResponse, SubscriptionAggregate aggregate) {
        CreateSubscriptionResponseDto response = new CreateSubscriptionResponseDto();

        response.setResponseCode(baseResponse.getResponseCode());
        response.setInvoiceDates(aggregate.getInvoiceDates());
        CommonCreateSubscriptionDto commonCreateSubscriptionDto = new CommonCreateSubscriptionDto();
        commonCreateSubscriptionDto.setSubType(aggregate.getCommonCreateSubscriptionDto().getSubType());
        commonCreateSubscriptionDto.setAmount(aggregate.getCommonCreateSubscriptionDto().getAmount());
        commonCreateSubscriptionDto.setSubscriptionDay(aggregate.getCommonCreateSubscriptionDto().getSubscriptionDay());
        response.setCommonCreateSubscriptionDto(commonCreateSubscriptionDto);

        return response;

    }

    public BaseDomain convert2Aggregate(BaseRequest baseRequest) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(baseRequest, SubscriptionAggregate.class);
    }
}
