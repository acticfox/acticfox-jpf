package com.github.acticfox.extension.test;

import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.acticfox.common.api.result.ResultDTO;
import com.github.acticfox.extension.test.customer.client.AddCustomerCmd;
import com.github.acticfox.extension.test.customer.client.Constants;
import com.github.acticfox.extension.test.customer.client.CustomerDTO;
import com.github.acticfox.extension.test.customer.client.CustomerServiceI;
import com.github.acticfox.extension.test.customer.client.NoticeService;
import com.github.acticfox.extension.test.customer.domain.CustomerType;
import com.github.acticfox.jpf.api.BizScenario;

/**
 * ExtensionTest
 *
 * @author fanyong.kfy
 * @date 2020-11-14 2:55 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ExtensionTest {
    @Resource
    private CustomerServiceI customerService;

    @Resource
    private NoticeService noticeService;

    @Test
    public void testBiz1OfAddCustomerSuccess() {
        // 1. Prepare
        AddCustomerCmd addCustomerCmd = new AddCustomerCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("alibaba");
        customerDTO.setSource(Constants.SOURCE_RFQ);
        customerDTO.setCustomerType(CustomerType.IMPORTANT);
        addCustomerCmd.setCustomerDTO(customerDTO);
        BizScenario scenario = BizScenario.valueOf(Constants.BIZ_1);
        addCustomerCmd.setBizScenario(scenario);

        // 2. Execute
        ResultDTO<?> response = customerService.addCustomer(addCustomerCmd);

        // 3. Expect Success
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testBiz2OfAddCustomerSuccess() {
        // 1. Prepare
        AddCustomerCmd addCustomerCmd = new AddCustomerCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("alibaba");
        customerDTO.setSource(Constants.SOURCE_RFQ);
        customerDTO.setCustomerType(CustomerType.IMPORTANT);
        addCustomerCmd.setCustomerDTO(customerDTO);
        BizScenario scenario = BizScenario.valueOf(Constants.BIZ_2);
        addCustomerCmd.setBizScenario(scenario);

        // 2. Execute
        ResultDTO<?> response = customerService.addCustomer(addCustomerCmd);

        // 3. Expect Success
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testDefaultAddCustomerSuccess() {
        // 1. Prepare
        AddCustomerCmd addCustomerCmd = new AddCustomerCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCompanyName("jingdong");
        customerDTO.setSource(Constants.SOURCE_RFQ);
        customerDTO.setCustomerType(CustomerType.IMPORTANT);
        addCustomerCmd.setCustomerDTO(customerDTO);
        BizScenario scenario = BizScenario.valueOf("HENGMEI");
        addCustomerCmd.setBizScenario(scenario);

        // 2. Execute
        ResultDTO<?> response = customerService.addCustomer(addCustomerCmd);

        // 3. Expect Success
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testExtensionPluginSuccess() {
        List<Long> userList = Lists.newArrayList();
        userList.add(1L);
        userList.add(2L);
        ResultDTO result = noticeService.notice(userList);
        // 3. Expect Success
        Assert.assertTrue(result.isSuccess());
    }
}
