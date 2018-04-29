package nl.crado.game.captclash.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.crado.game.captclash.model.user.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
