package com.github.actic.extension.test.customer.domain.rule;

import com.github.actic.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.jpf.api.ExtensionPoint;

/**
 * CustomerRuleExtPt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 12:03 PM
 */
public interface CustomerRuleExtPt extends ExtensionPoint {

    // Different business check for different biz
    public boolean addCustomerCheck(CustomerEntity customerEntity);

    // Different upgrade policy for different biz
    default public void customerUpgradePolicy(CustomerEntity customerEntity) {
        // Nothing special
    }
}
