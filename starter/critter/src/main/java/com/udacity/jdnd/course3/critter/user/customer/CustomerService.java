package com.udacity.jdnd.course3.critter.user.customer;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return Lists.newArrayList(customerRepository.findAll());
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseGet(null);
    }

    public Customer findOwnerByPetId(Long petId) {
        return customerRepository.findOwnerByPetsId(petId);
    }
}