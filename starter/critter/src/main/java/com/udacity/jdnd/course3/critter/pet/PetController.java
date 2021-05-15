package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    Logger logger = LoggerFactory.getLogger(PetController.class);

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        logger.info("Saving new Pet");
        Pet petSaved = petService.save(petOf(petDTO));

        return petDTOFrom(petSaved);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petDTOFrom(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService
                .findAll()
                .stream()
                .map(pet -> petDTOFrom(pet))
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService
                .findAllByOwnerId(ownerId)
                .stream()
                .map(pet -> petDTOFrom(pet))
                .collect(Collectors.toList());
    }

    private Pet petOf(PetDTO petDTO) {
        Pet pet = Pet.builder()
                .name(petDTO.getName())
                .notes(petDTO.getNotes())
                .birthDate(petDTO.getBirthDate())
                .owner(customerService.findById(petDTO.getOwnerId()))
                .type(petDTO.getType())
                .build();

        return pet;
    }

    private PetDTO petDTOFrom(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setOwnerId(pet.getOwner().getId());
        petDTO.setType(pet.getType());
        petDTO.setNotes(pet.getNotes());
        petDTO.setBirthDate(pet.getBirthDate());

        return petDTO;
    }
}
