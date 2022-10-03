package com.github.acticfox.extension.test.customer.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.acticfox.extension.ExtensionExecutor;
import com.github.acticfox.extension.test.customer.Entity;
import com.github.acticfox.extension.test.customer.domain.rule.CustomerRuleExtPt;
import com.github.acticfox.extension.test.customer.infrastructure.CustomerRepository;
import com.github.acticfox.jpf.api.BizScenario;

import lombok.Data;

/**
 * Customer Entity
 *
 * @author fanyong.kfy
 * @date 2018-01-07 2:38 AM
 */
@Entity
@Data
public class CustomerEntity {

    private String companyName;
    private SourceType sourceType;
    private CustomerType customerType;
    private BizScenario bizScenario;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ExtensionExecutor extensionExecutor;

    public CustomerEntity() {

    }

    public void addNewCustomer() {
        // Add customer policy
        extensionExecutor.execute(CustomerRuleExtPt.class, this.getBizScenario(),
            extension -> extension.addCustomerCheck(this));

        // Persist customer
        customerRepository.persist(this);

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
