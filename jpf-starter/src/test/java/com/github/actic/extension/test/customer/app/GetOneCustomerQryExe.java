package com.github.actic.extension.test.customer.app;

import org.springframework.stereotype.Component;

import com.github.actic.extension.test.customer.client.CustomerDTO;
import com.github.actic.extension.test.customer.client.GetOneCustomerQry;
import com.zhichubao.common.api.result.ResultDTO;

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
