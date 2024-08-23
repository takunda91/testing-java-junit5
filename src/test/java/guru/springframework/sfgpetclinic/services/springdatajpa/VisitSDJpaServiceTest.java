package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VisitSDJpaServiceTest {

  @InjectMocks
  VisitSDJpaService service;
  @Mock
  VisitRepository visitRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    }

  @Test
  void findAll() {
    Visit visit = new Visit();
    Set<Visit> visits = Set.of(visit);
    when(visitRepository.findAll()).thenReturn(visits);
    Set<Visit> foundVisits = service.findAll();
    verify(visitRepository).findAll();
    assertThat(foundVisits).hasSize(1);
    }

  @Test
  void findById() {
    Visit visit = new Visit();

    when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
    Visit foundVisit = service.findById(1L);
    verify(visitRepository).findById(1L);
    assertThat(foundVisit).isNotNull();
      }


      // given-when-then BDD style
      @Test
      void findByIdBDD() {
        Visit visit = new Visit();

        //given
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));
        //when
        Visit foundVisit = service.findById(1L);
        // then
        assertThat(foundVisit).isNotNull();
        then(visitRepository).should(times(1)).findById(1L);
        then(visitRepository).shouldHaveNoMoreInteractions();

      }

  @Test
  void save() {
    Visit visit = new Visit();
    when(visitRepository.save(any())).thenReturn(visit);
    Visit savedVisit = service.save(any(Visit.class));
    verify(visitRepository, times(1)).save(any());
    assertThat(savedVisit).isNotNull();
  }

  @Test
  void delete() {
    Visit visit = new Visit();
    service.delete(visit);
    verify(visitRepository, times(1)).delete(any());
    }

  @Test
  void deleteById() {
    service.deleteById(1L);
    verify(visitRepository, times(1)).deleteById(anyLong());
    }
}
