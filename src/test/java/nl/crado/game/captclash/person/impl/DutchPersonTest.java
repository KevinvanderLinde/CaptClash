package nl.crado.game.captclash.person.impl;

import nl.crado.game.captclash.person.Person;
import nl.crado.game.captclash.person.PersonFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DutchPersonTest {

    private Person person;
    @Before
    public void setUp() throws Exception {
    person = new PersonFactory().getPerson(1);
    }

    @Test
    public void checkIfDutchGreetingIsHallo() {
        Assert.assertEquals("Hallo", person.sayHello());
    }
}