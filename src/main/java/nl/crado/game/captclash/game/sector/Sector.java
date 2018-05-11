package nl.crado.game.captclash.game.sector;

import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import nl.crado.game.captclash.game.building.Building;
import nl.crado.game.captclash.game.building.BuildingType;

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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "sector_id")
	@OrderBy//TODO
	@Setter
	private Set<Building> buildings = new HashSet<>();

	private transient boolean isSorted = false;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "upgrading_building")
	@Getter @Setter
	private Building upgradingBuilding;

	@Getter @Setter
	private Long upgradeFinished;

	//Resources
	@Getter @Setter
	private Integer caffeine = 300;

	public Optional<Building> getBuildingByType(BuildingType type) {
		return buildings.stream().filter(building -> building.getBuildingType() == type).findFirst();
	}

	public synchronized Set<Building> getBuildings() {
		return buildings.stream().sorted((b1, b2) -> Integer.compare(b1.getBuildingType().getListOrder(), b2.getBuildingType().getListOrder())).collect(Collectors.toSet());
	}

	public void handleTick() {
		buildings.stream().filter(building -> building.getBuildingType().getRecourceType().isPresent()).forEach(building -> {
			//TODO also handle upgrade of a building.
			caffeine += 1;
		});
	}
}
