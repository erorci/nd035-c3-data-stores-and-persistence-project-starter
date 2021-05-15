package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        Pet petSaved = petRepository.save(pet);
        Customer customer = petSaved.getOwner();

        customer.addPet(petSaved);
        customerRepository.save(customer);

        return petSaved;
    }

    public Pet findById(long petId) {
        return petRepository.findById(petId).orElseGet(null);
    }

    public List<Pet> findAll() {
        return Lists.newArrayList(petRepository.findAll());
    }

    public List<Pet> findAllByOwnerId(Long ownerId) {
        return Lists.newArrayList(petRepository.findAllByOwnerId(ownerId));
    }
}
