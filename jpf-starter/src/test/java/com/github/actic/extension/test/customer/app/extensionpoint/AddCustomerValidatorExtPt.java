package com.github.actic.extension.test.customer.app.extensionpoint;

import com.github.actic.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.jpf.api.ExtensionPoint;

/**
 * AddCustomerValidatorExtPt
 *
 * @author fanyong.kfy
 * @date 2018-01-07 1:27 AM
 */
public interface AddCustomerValidatorExtPt extends ExtensionPoint {

    public void validate(AddCustomerCmd addCustomerCmd);
}
