package nl.crado.game.captclash.model.test;

import org.springframework.security.core.userdetails.UserDetailsService;

import nl.crado.game.captclash.model.user.User;

public interface UserService extends UserDetailsService {

	User findByUsername(String username);
	
}
