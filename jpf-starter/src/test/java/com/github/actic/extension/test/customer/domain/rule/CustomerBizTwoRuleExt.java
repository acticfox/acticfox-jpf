package com.github.actic.extension.test.customer.domain.rule;

import com.github.actic.extension.test.customer.client.Constants;
import com.github.actic.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.jpf.api.Extension;

/**
 * CustomerBizTwoRuleExt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 12:10 PM
 */
@Extension(tenantId = Constants.BIZ_2)
public class CustomerBizTwoRuleExt implements CustomerRuleExtPt {

    @Override
    public boolean addCustomerCheck(CustomerEntity customerEntity) {
        // Any Customer can be added
        return true;
    }
}
