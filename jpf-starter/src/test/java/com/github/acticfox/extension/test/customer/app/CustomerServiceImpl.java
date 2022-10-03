package com.github.acticfox.extension.test.customer.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.acticfox.common.api.result.ResultDTO;
import com.github.acticfox.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.extension.test.customer.client.CustomerDTO;
import com.github.acticfox.extension.test.customer.client.CustomerServiceI;
import com.github.acticfox.extension.test.customer.client.GetOneCustomerQry;

/**
 * CustomerServiceImpl
 *
 * @author fanyong.kfy 2018-01-06 7:40 PM
 */
@Service
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private AddCustomerCmdExe addCustomerCmdExe;

    @Resource
    private GetOneCustomerQryExe getOneCustomerQryExe;

    @Override
    public ResultDTO<?> addCustomer(AddCustomerCmd addCustomerCmd) {
        return addCustomerCmdExe.execute(addCustomerCmd);
    }

    @Override
    public ResultDTO<CustomerDTO> getCustomer(GetOneCustomerQry getOneCustomerQry) {
        return getOneCustomerQryExe.execute(getOneCustomerQry);
    }
}
