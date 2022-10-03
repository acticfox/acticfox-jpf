package com.github.actic.extension.test.customer.app.extension;

import com.github.actic.extension.test.customer.app.extensionpoint.AddCustomerValidatorExtPt;
import com.github.actic.extension.test.customer.client.AddCustomerCmd;
import com.github.actic.extension.test.customer.client.Constants;
import com.github.acticfox.jpf.api.Extension;
import com.zhichubao.common.api.exception.BusinessException;

/**
 * AddCustomerBizTwoValidator
 *
 * @author fanyong.kfy
 * @date 2018-01-07 1:31 AM
 */
@Extension(tenantId = Constants.BIZ_2)
public class AddCustomerBizTwoValidator implements AddCustomerValidatorExtPt {

    public void validate(AddCustomerCmd addCustomerCmd) {
        // For BIZ TWO CustomerTYpe could not be null
        System.out.println("teantId:" + Constants.BIZ_2 + " validation AddCustomerCmd");
        if (addCustomerCmd.getCustomerDTO().getCustomerType() == null)
            throw new BusinessException("CustomerType could not be null");
    }
}
