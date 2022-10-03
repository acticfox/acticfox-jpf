package com.github.actic.extension.test.customer.domain.rule;

import com.github.actic.extension.test.customer.client.Constants;
import com.github.actic.extension.test.customer.domain.CustomerEntity;
import com.github.actic.extension.test.customer.domain.SourceType;
import com.github.acticfox.common.api.exception.BusinessException;
import com.github.acticfox.jpf.api.Extension;

/**
 * CustomerBizOneRuleExt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 12:10 PM
 */
@Extension(tenantId = Constants.BIZ_1)
public class CustomerBizOneRuleExt implements CustomerRuleExtPt {

    @Override
    public boolean addCustomerCheck(CustomerEntity customerEntity) {
        if (SourceType.AD == customerEntity.getSourceType()) {
            throw new BusinessException("Sorry, Customer from advertisement can not be added in this period");
        }
        return true;
    }
}
