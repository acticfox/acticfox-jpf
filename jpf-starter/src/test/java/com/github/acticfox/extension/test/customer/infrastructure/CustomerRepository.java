package com.github.acticfox.extension.test.customer.infrastructure;

import org.springframework.stereotype.Repository;

import com.github.acticfox.extension.test.customer.domain.CustomerEntity;

/**
 * CustomerRepository
 *
 * @author fanyong.kfy
 * @date 2018-01-07 11:59 AM
 */
@Repository
public class CustomerRepository {

    public void persist(CustomerEntity customerEntity){
        System.out.println("Persist customer to DB : "+ customerEntity);
    }
}
