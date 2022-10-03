package com.github.acticfox.extension.test.customer.app;

import com.github.acticfox.common.api.result.ResultDTO;
import com.github.acticfox.extension.test.customer.client.CustomerCreatedEvent;

/**
 * CustomerCreatedEventHandler
 *
 * @author fanyong.kfy
 * @date 2020-06-22 7:00 PM
 */
public class CustomerCreatedEventHandler {

    public ResultDTO<?> execute(CustomerCreatedEvent customerCreatedEvent) {
        System.out.println("customerCreatedEvent processed");
        return null;
    }
}
