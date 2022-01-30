package com.intech.spacemonopoly;

import com.intech.spacemonopoly.controller.CreatePlayerController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePlayerControllerControllerTest {
    @Test
    public void isValidNameWithNumberTest() {
        //Arrange
        CreatePlayerController createPlayerControllerTest = new CreatePlayerController();

        //Act
        boolean test = createPlayerControllerTest.isValidName("Gregory44");

        //Assert
        assertFalse(test);
    }

    @Test
    public void isValidNameWithSpecialCharTest() {
        //Arrange
        CreatePlayerController createPlayerControllerTest = new CreatePlayerController();

        //Act
        boolean test = createPlayerControllerTest.isValidName("Gregory_=");

        //Assert
        assertFalse(test);
    }

    @Test
    public void isValidNameWithTooMuchCharTest() {
        //Arrange
        CreatePlayerController createPlayerControllerTest = new CreatePlayerController();

        //Act
        boolean test = createPlayerControllerTest.isValidName("JemappelleGregoryPotesGonzalez");

        //Assert
        assertFalse(test);
    }

    @Test
    public void isValidNameWithSpaceTest() {
        //Arrange
        CreatePlayerController createPlayerControllerTest = new CreatePlayerController();

        //Act
        boolean test = createPlayerControllerTest.isValidName("   ");

        //Assert
        assertFalse(test);
    }

    @Test
    public void isValidNameTestTrue() {
        //Arrange
        CreatePlayerController createPlayerControllerTest = new CreatePlayerController();

        //Act
        boolean test = createPlayerControllerTest.isValidName("Gregory");

        //Assert
        assertTrue(test);
    }
}
