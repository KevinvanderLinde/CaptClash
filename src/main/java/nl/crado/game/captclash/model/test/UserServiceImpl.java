package nl.crado.game.captclash.model.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nl.crado.game.captclash.model.dao.UserDao;
import nl.crado.game.captclash.model.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null) {
			throw new NullPointerException("Username is null!");
		}
		User user = userDao.findByUsername(username);
		if (user == null ) {
			throw new UsernameNotFoundException("Did not found user: " + username);
		}
		return user;
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
