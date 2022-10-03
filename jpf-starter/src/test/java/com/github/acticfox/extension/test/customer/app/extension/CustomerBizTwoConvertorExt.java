package com.github.acticfox.extension.test.customer.app.extension;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.acticfox.extension.test.customer.app.extensionpoint.CustomerConvertorExtPt;
import com.github.acticfox.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.extension.test.customer.client.Constants;
import com.github.acticfox.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.extension.test.customer.domain.SourceType;
import com.github.acticfox.jpf.api.Extension;

/**
 * CustomerBizTwoConvertorExt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 3:05 AM
 */
@Extension(tenantId = Constants.BIZ_2)
public class CustomerBizTwoConvertorExt implements CustomerConvertorExtPt {

    @Autowired
    private CustomerConvertor customerConvertor;// Composite basic convertor to do basic conversion

    @Override
    public CustomerEntity clientToEntity(AddCustomerCmd addCustomerCmd) {
        CustomerEntity customerEntity = customerConvertor.clientToEntity(addCustomerCmd);
        // In this business, if customers from RFQ and Advertisement are both regarded as Advertisement
        if (Constants.SOURCE_AD.equals(addCustomerCmd.getCustomerDTO().getSource())
            || Constants.SOURCE_RFQ.equals(addCustomerCmd.getCustomerDTO().getSource())) {
            customerEntity.setSourceType(SourceType.AD);
        }
        System.out.println("teantId:" + Constants.BIZ_2 + " clientToEntity AddCustomerCmd");
        return customerEntity;
    }

}
