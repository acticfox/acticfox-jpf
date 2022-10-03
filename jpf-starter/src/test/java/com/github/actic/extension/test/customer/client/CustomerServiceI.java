package com.github.actic.extension.test.customer.client;

import com.github.acticfox.common.api.result.ResultDTO;

/**
 * CustomerServiceI
 *
 * @author fanyong.kfy 2018-01-06 7:24 PM
 */
public interface CustomerServiceI {
    public ResultDTO<?> addCustomer(AddCustomerCmd addCustomerCmd);

    public ResultDTO<CustomerDTO> getCustomer(GetOneCustomerQry getOneCustomerQry);
}
