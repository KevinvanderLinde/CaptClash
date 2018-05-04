package nl.crado.game.captclash.model.dao;

import nl.crado.game.captclash.game.building.Building;
import nl.crado.game.captclash.game.sector.Sector;
import org.springframework.data.repository.CrudRepository;

public interface BuildingDao extends CrudRepository<Building, Long> {
}
