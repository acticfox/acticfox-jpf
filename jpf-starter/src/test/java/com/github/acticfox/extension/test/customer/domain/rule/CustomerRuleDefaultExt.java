package com.github.acticfox.extension.test.customer.domain.rule;

import com.github.acticfox.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.jpf.api.Extension;

/**
 * CustomerBizTwoRuleExt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 12:10 PM
 */
@Extension
public class CustomerRuleDefaultExt implements CustomerRuleExtPt {

    @Override
    public boolean addCustomerCheck(CustomerEntity customerEntity) {
        // Any Customer can be added
        return true;
    }
}
