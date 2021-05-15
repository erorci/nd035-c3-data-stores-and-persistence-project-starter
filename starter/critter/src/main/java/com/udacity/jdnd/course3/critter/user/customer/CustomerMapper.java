package com.udacity.jdnd.course3.critter.user.customer;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public Customer customerOf(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .notes(customerDTO.getNotes())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();

        return customer;
    }

    public CustomerDTO customerDTOFrom(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setPetIds
                (customer.getPets() != null ?
                        customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList())
                        : null);

        return customerDTO;
    }
}
