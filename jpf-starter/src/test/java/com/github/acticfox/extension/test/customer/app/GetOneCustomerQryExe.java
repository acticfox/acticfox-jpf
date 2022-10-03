package com.github.acticfox.extension.test.customer.app;

import org.springframework.stereotype.Component;

import com.github.acticfox.common.api.result.ResultDTO;
import com.github.acticfox.extension.test.customer.client.CustomerDTO;
import com.github.acticfox.extension.test.customer.client.GetOneCustomerQry;

/**
 * GetOneCustomerQryExe
 *
 * @author fanyong.kfy
 * @date 2020-06-22 5:12 PM
 */
@Component
public class GetOneCustomerQryExe {

    public ResultDTO<CustomerDTO> execute(GetOneCustomerQry getOneCustomerQry) {
        return null;
    }
}
