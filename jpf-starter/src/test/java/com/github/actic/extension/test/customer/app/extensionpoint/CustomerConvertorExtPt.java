package com.github.actic.extension.test.customer.app.extensionpoint;

import com.github.actic.extension.test.customer.client.AddCustomerCmd;
import com.github.actic.extension.test.customer.domain.CustomerEntity;
import com.github.acticfox.jpf.api.ExtensionPoint;

/**
 * CustomerConvertorExtPt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 2:37 AM
 */
public interface CustomerConvertorExtPt extends ExtensionPoint {

    public CustomerEntity clientToEntity(AddCustomerCmd addCustomerCmd);
}
