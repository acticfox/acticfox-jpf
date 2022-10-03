package com.github.actic.extension.test.customer.app.extension;

import com.github.actic.extension.test.customer.app.extensionpoint.AddCustomerValidatorExtPt;
import com.github.actic.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.jpf.api.Extension;

@Extension
public class AddCustomerDefault implements AddCustomerValidatorExtPt {

    public void validate(AddCustomerCmd addCustomerCmd) {
        System.out.println("Default validation");
    }
}