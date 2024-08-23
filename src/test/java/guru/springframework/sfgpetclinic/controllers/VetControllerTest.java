package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VetControllerTest {

  VetController vetController;
  VetService vetService;
  Model model;
  @BeforeEach
  void setUp() {
    SpecialtyService specialtyService = new SpecialityMapService();
    vetService = new VetMapService(specialtyService);
    vetController = new VetController(vetService);
     model = new ModelImpl();

    Vet vet = new Vet(1L, "Joe", "Buck", null);
    Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);
    vetService.save(vet);
    vetService.save(vet2);

    }

  @Test
  void listVets() {
    model.addAttribute("vets", vetService.findAll());

    Set vets = (Set) model.getMap().get("vets");

    String listVets = vetController.listVets(model);

    assertThat("vets/index").isEqualTo(listVets);
    assertThat(vets.size()).isEqualTo(2);
  }
}
