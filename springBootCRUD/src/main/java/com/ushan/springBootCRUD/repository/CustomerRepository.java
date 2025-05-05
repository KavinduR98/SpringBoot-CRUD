package com.ushan.springBootCRUD.repository;

import com.ushan.springBootCRUD.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
