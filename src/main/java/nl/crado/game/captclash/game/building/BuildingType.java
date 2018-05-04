package nl.crado.game.captclash.game.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BuildingType {

    OFFICE(2, "Office", 10),
    CAFFE(1, "Cafe",  30)

;


    //TODO add all the default building values
    @Getter private final int defaultLevel;


    @Getter private String name;
    @Getter private final int maxLevel;


}
