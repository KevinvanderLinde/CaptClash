package nl.crado.game.captclash.game.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UnitType {

    DESIGNER(10.0)

    ;

    @Getter private double speed; // in m/s (Since a sector has a virtual size of: 1000 x 1000 m)

    //TODO other values like costs, damage, etc


}
