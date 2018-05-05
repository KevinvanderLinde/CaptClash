package nl.crado.game.captclash.game.building;

import lombok.Getter;
import lombok.Setter;
import nl.crado.game.captclash.game.sector.Sector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Building {

    public static Building generateDefaultBuilding(BuildingType type) {
        Building building = new Building();

        building.setBuildingType(type);
        //TODO get default values from BuildingType
        building.currentLevel = type.getDefaultLevel();

        return building;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private BuildingType buildingType;

    @Getter @Setter
    private Integer currentLevel;

    @Override
    public String toString() {
        return buildingType.getName() + ": " + currentLevel;
    }


}
