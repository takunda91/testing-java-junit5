package guru.springframework.sfgpetclinic.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import guru.springframework.sfgpetclinic.PersonTestInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class PersonTest implements PersonTestInterface {

  Person person;
  @BeforeEach
  void setUp() {
    person = new Person(1L, "Joe", "Buck");
  }

  @Test
  void groupedAssertions() {
    //then
    assertAll("Test Props Set",
        () -> assertEquals("Joe", person.getFirstName(), "First Name Failed"),
        () -> assertEquals("Buck", person.getLastName(), "Last Name Failed"));

    // assert j
    assertThat(person.getFirstName()).isEqualTo("Joe");
  }

  @Test
  void groupedAssertionsMsgs() {
    //then
    assertAll("Test Props Set 2",
        () -> assertEquals("Joe", person.getFirstName(), "First Name Failed"),
        () -> assertEquals("Buck", person.getLastName(), "Last Name Failed"));
  }

  @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
  @DisplayName("My Repeated Person Test")
  void repeatedPersonTest() {
   // Person person = new Person(1l, "Joe", "Buck");
  }

}
