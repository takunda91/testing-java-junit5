package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public interface PersonTestInterface {

  @BeforeEach
  default void beforeAll(TestInfo testInfo) {
  // can add RepetitionInfo repetitionInfo
    System.out.println("Running test - " + testInfo.getDisplayName());
    }
}
