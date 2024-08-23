package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SpecialitySDJpaServiceBDDTest {


  @InjectMocks
  SpecialitySDJpaService specialitySDJpaService;

  @Mock
  SpecialtyRepository specialtyRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    }

  @Test
  void findAll() {
    Set<Speciality> specialities = new HashSet<>();
    //given
    given(specialtyRepository.findAll()).willReturn(specialities);
    //when
    Set<Speciality> foundSpecialties = specialitySDJpaService.findAll();
    //then
    assertNotNull(foundSpecialties);
    then(specialtyRepository).should(times(1)).findAll();
  }

  @Test
  void findById() {
    Speciality speciality = new Speciality();
    // given
    given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
    // when
    Speciality foundSpeciality = specialitySDJpaService.findById(1L);
    // then
    assertNotNull(foundSpeciality);
    then(specialtyRepository).should(times(1)).findById(1L);
    then(specialtyRepository).should(atLeastOnce()).findById(1L);
    }

  @Test
  void save() {
    }

  @Test
  void delete() {
    }

  @Test
  void deleteById() {
    }
}
