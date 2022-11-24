package com.infosys.infytel.customer.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infytel.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	

}
