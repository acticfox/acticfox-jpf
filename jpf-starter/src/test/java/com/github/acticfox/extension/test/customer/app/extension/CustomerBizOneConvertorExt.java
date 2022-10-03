package com.github.acticfox.extension.test.customer.app.extension;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.acticfox.extension.test.customer.app.extensionpoint.CustomerConvertorExtPt;
import com.github.acticfox.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.extension.test.customer.client.Constants;
import com.github.acticfox.extension.test.customer.client.CustomerDTO;
import com.github.acticfox.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.extension.test.customer.domain.SourceType;
import com.github.acticfox.jpf.api.Extension;

/**
 * CustomerBizOneConvertorExt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 3:05 AM
 */
@Extension(tenantId = Constants.BIZ_1)
public class CustomerBizOneConvertorExt implements CustomerConvertorExtPt {

    @Autowired
    private CustomerConvertor customerConvertor;// Composite basic convertor to do basic conversion

    @Override
    public CustomerEntity clientToEntity(AddCustomerCmd addCustomerCmd) {
        CustomerEntity customerEntity = customerConvertor.clientToEntity(addCustomerCmd);
        CustomerDTO customerDTO = addCustomerCmd.getCustomerDTO();
        // In this business, AD and RFQ are regarded as different source
        if (Constants.SOURCE_AD.equals(customerDTO.getSource())) {
            customerEntity.setSourceType(SourceType.AD);
        }
        if (Constants.SOURCE_RFQ.equals(customerDTO.getSource())) {
            customerEntity.setSourceType(SourceType.RFQ);
        }
        System.out.println("teantId:" + Constants.BIZ_1 + " clientToEntity AddCustomerCmd");
        return customerEntity;
    }
}
