package com.github.acticfox.extension.test.customer.app.extension;

import org.springframework.stereotype.Component;

import com.github.acticfox.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.extension.test.customer.client.CustomerDTO;
import com.github.acticfox.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.extension.util.ApplicationContextHelper;

/**
 * CustomerConvertor
 *
 * @author fanyong.kfy
 * @date 2018-01-07 3:08 AM
 */
@Component
public class CustomerConvertor {

    public CustomerEntity clientToEntity(Object clientObject) {
        AddCustomerCmd addCustomerCmd = (AddCustomerCmd)clientObject;
        CustomerDTO customerDTO = addCustomerCmd.getCustomerDTO();
        CustomerEntity customerEntity = (CustomerEntity)ApplicationContextHelper.getBean(CustomerEntity.class);
        customerEntity.setCompanyName(customerDTO.getCompanyName());
        customerEntity.setCustomerType(customerDTO.getCustomerType());
        customerEntity.setBizScenario(addCustomerCmd.getBizScenario());
        return customerEntity;
    }
}
