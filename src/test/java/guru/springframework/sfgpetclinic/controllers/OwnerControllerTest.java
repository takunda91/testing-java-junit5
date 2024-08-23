package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OwnerControllerTest {

  @Mock
  BindingResult bindingResult;
  @Mock
  OwnerService ownerService;
  @InjectMocks
  OwnerController ownerController;
  @Captor
  ArgumentCaptor<String> annotationCaptor;
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    }

  @Test
  void processCreationFormHasErrors() {
    // given
    Owner owner = new Owner(1L, "Joe", "Buck");
    given(bindingResult.hasErrors()).willReturn(true);
    // when
    String view = ownerController.processCreationForm(owner, bindingResult);
    // then
    assertEquals("owners/createOrUpdateOwnerForm", view);
    }

  @Test
  void processCreationFormNoErrors() {
    // given
    Owner owner = new Owner(1L, "Joe", "Buck");
    given(bindingResult.hasErrors()).willReturn(false);
    given(ownerService.save(owner)).willReturn(owner);
    // when
    String view = ownerController.processCreationForm(owner, bindingResult);
    // then
    assertEquals("redirect:/owners/1", view);
    }


    // can use annotation
    @Test
  void processFindFormWildCardString() {
    // given
      Owner owner = new Owner(1L, "Joe", "Buck");
      List<Owner> results = new ArrayList<>();
      ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
      given(ownerService.findAllByLastNameLike(captor.capture())).willReturn(results);
      // when
      ownerController.processFindForm(owner, bindingResult, null);
      assertEquals("%Buck%", captor.getValue());
    }

  @Test
  void processFindFormWildCardStringAnnotation() {
    // given
    Owner owner = new Owner(1L, "Joe", "Buck");
    List<Owner> results = new ArrayList<>();
    given(ownerService.findAllByLastNameLike(annotationCaptor.capture())).willReturn(results);
    // when
    ownerController.processFindForm(owner, bindingResult, null);
    // then
    assertEquals("%Buck%", annotationCaptor.getValue());
  }
}
