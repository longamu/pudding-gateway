package com.mohism.pudding.gateway.modular.consumer;

import com.mohism.pudding.base.route.api.DynamicVersionApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;


/**
 * 路由版本消费者
 *
 * @author real earth
 */
@FeignClient("pudding-system")
@Service
public interface DynamicVersionConsumer extends DynamicVersionApi {

}
