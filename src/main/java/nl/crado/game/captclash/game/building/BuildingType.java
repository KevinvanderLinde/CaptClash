package nl.crado.game.captclash.game.building;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.crado.game.captclash.game.sector.Sector;

import java.util.Optional;

@AllArgsConstructor
public enum BuildingType {

    OFFICE(1, 2, "Office", 10, ResourceType.NONE),
    CAFFE(2, 1, "Cafe",  30, ResourceType.CAFFEINE)

    ;

	@Getter private final int listOrder;
	
    //TODO add all the default building values
    @Getter private final int defaultLevel;

    //Details about the building
    @Getter private final String name;
    @Getter private final int maxLevel;

    private final ResourceType type;

    public Optional<ResourceType> getRecourceType() {
        return (type == ResourceType.NONE ? Optional.empty() : Optional.ofNullable(type));
    }

    public enum ResourceType {
        NONE,
        CAFFEINE,
        ELECTRICITY() {
            @Override
            public void generate(Sector sector, Building buidling) {

            }
        }
        ;

        public void generate(Sector sector, Building building) {

        }
    }
}
