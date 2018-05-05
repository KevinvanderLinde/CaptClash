package nl.crado.game.captclash.game.sector;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import nl.crado.game.captclash.game.building.Building;
import nl.crado.game.captclash.game.building.BuildingType;
import nl.crado.game.captclash.game.user.Gameuser;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"loc_x", "loc_z"}) )
public class Sector {

	public static Sector generateNewDefaultSector() {
		Sector sector = new Sector();
		sector.setSectorName("New sector");

		//TODO get location logic.
		Random random = new Random();
		sector.setLocationX(random.nextInt());
		sector.setLocationZ(random.nextInt());

		//TODO add default buildings with values
		for (BuildingType type : BuildingType.values()) {
			sector.buildings.add(Building.generateDefaultBuilding(type));
		}
		return sector;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Column(length = 32, unique = false, nullable = false, name = "Sector")
	@Getter @Setter
	private String sectorName;
	
	@Column(nullable = false, name = "loc_x")
	@Getter @Setter
	private Integer locationX;
	
	@Column(nullable = false, name = "loc_z")
	@Getter @Setter
	private Integer locationZ;

	@OneToMany
	@JoinColumn(name = "sector_id")
	@Getter @Setter
	private Set<Building> buildings = new HashSet<>();

	//Resources
	@Getter @Setter
	private Integer caffeine = 300;

	public Optional<Building> getBuildingByType(BuildingType type) {
		return buildings.stream().filter(building -> building.getBuildingType() == type).findFirst();
	}

	public void handleResourceTick() {
		buildings.stream().filter(building -> building.getBuildingType().getRecourceType().isPresent()).forEach(building -> {
			caffeine += 1;
		});
	}
}
