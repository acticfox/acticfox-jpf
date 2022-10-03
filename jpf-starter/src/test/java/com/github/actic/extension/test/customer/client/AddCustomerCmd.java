package com.github.actic.extension.test.customer.client;

import com.github.acticfox.jpf.api.BizScenario;
import com.zhichubao.common.api.Command;

import lombok.Data;

/**
 * AddCustomerCmd
 *
 * @author fanyong.kfy 2018-01-06 7:28 PM
 */
@Data
public class AddCustomerCmd extends Command {

    private CustomerDTO customerDTO;

    private String biz;

    private BizScenario bizScenario;
}
