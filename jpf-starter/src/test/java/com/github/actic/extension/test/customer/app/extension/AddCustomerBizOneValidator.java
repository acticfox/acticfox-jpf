package com.github.actic.extension.test.customer.app.extension;

import com.github.actic.extension.test.customer.app.extensionpoint.AddCustomerValidatorExtPt;
import com.github.actic.extension.test.customer.client.AddCustomerCmd;
import com.github.actic.extension.test.customer.client.Constants;
import com.github.actic.extension.test.customer.domain.CustomerType;
import com.github.acticfox.common.api.exception.BusinessException;
import com.github.acticfox.jpf.api.Extension;

/**
 * AddCustomerBizOneValidator
 *
 * @author fanyong.kfy
 * @date 2018-01-07 1:31 AM
 */
@Extension(tenantId = Constants.BIZ_1)
public class AddCustomerBizOneValidator implements AddCustomerValidatorExtPt {

    public void validate(AddCustomerCmd addCustomerCmd) {
        System.out.println("teantId:" + Constants.BIZ_1 + " validation AddCustomerCmd");
        // For BIZ TWO CustomerTYpe could not be VIP
        if (CustomerType.VIP == addCustomerCmd.getCustomerDTO().getCustomerType())
            throw new BusinessException("Customer Type could not be VIP for Biz One");
    }
}
