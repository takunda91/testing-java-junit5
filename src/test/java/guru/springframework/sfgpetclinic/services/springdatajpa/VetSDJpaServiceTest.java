package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

class VetSDJpaServiceTest {

  @InjectMocks
  VetSDJpaService vetSDJpaService;
  @Mock
  VetRepository vetRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    }

  @Test
  void deleteById() {
    vetSDJpaService.deleteById(1L);
    vetSDJpaService.deleteById(1L);

    verify(vetRepository, times(2)).deleteById(1L);
    verify(vetRepository, atLeast(2)).deleteById(1L);
    verify(vetRepository, atMost(3)).deleteById(1L);
    verify(vetRepository, never()).deleteById(4L);
    }

  @Test
  void findByIdTest() {
    Vet vet = mock(Vet.class);
    when(vetRepository.findById(1L)).thenReturn(Optional.of(vet));
    Vet foundVet = vetSDJpaService.findById(1L);
    assertEquals(vet, foundVet);
    assertThat(foundVet).isNotNull();
    verify(vetRepository).findById(1L);
  }

  @Test
  @DisplayName("Test that delete method using argument matchers")
  void delete() {
    Vet vet = mock(Vet.class);
    vetSDJpaService.delete(vet);
    verify(vetRepository).delete(any(Vet.class));
  }
}
