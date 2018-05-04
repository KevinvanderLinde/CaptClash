package nl.crado.game.captclash.model.dao;

import nl.crado.game.captclash.model.user.User;
import nl.crado.game.captclash.security.role.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Long> {
}
