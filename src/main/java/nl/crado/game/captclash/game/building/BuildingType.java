package nl.crado.game.captclash.game.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
public enum BuildingType {

    OFFICE(2, "Office", 10, RecourceType.NONE),
    CAFFE(1, "Cafe",  30, RecourceType.CAFFEINE)

    ;


    //TODO add all the default building values
    @Getter private final int defaultLevel;

    //Details about the building
    @Getter private final String name;
    @Getter private final int maxLevel;

    private final RecourceType type;

    public Optional<RecourceType> getRecourceType() {
        return (type == RecourceType.NONE ? Optional.empty() : Optional.ofNullable(type));
    }




    public enum RecourceType {
        NONE,
        CAFFEINE,
        ELECTRICITY

        ;

    }

}
