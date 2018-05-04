package nl.crado.game.captclash.model.dao;

import nl.crado.game.captclash.game.sector.Sector;
import nl.crado.game.captclash.security.role.Role;
import org.springframework.data.repository.CrudRepository;

public interface SectorDao extends CrudRepository<Sector, Long> {
}
