package com.github.acticfox.extension.test.customer.app.extension;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.acticfox.extension.test.customer.app.extensionpoint.CustomerConvertorExtPt;
import com.github.acticfox.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.jpf.api.Extension;

/**
 * CustomerBizTwoConvertorExt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 3:05 AM
 */
@Extension
public class CustomerConvertorDefault implements CustomerConvertorExtPt {

    @Autowired
    private CustomerConvertor customerConvertor;

    @Override
    public CustomerEntity clientToEntity(AddCustomerCmd addCustomerCmd) {
        CustomerEntity customerEntity = customerConvertor.clientToEntity(addCustomerCmd);
        System.out.println("teantId default clientToEntity AddCustomerCmd");
        return customerEntity;
    }

}
