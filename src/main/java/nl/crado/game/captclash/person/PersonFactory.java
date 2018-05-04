package nl.crado.game.captclash.person;

import nl.crado.game.captclash.person.impl.DutchPerson;
import nl.crado.game.captclash.person.impl.GermanPerson;

public class PersonFactory {

    public Person getPerson(int type) {
        switch (type) {
            case 1: return new DutchPerson();
            case 2: return new GermanPerson();
            default: return null; //Exception
        }
    }

}
