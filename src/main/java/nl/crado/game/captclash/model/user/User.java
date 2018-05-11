package nl.crado.game.captclash.model.user;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.experimental.Accessors;
import nl.crado.game.captclash.game.user.Gameuser;
import nl.crado.game.captclash.security.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import nl.crado.game.captclash.game.sector.Sector;

@Entity
public class User implements UserDetails, Gameuser {

	//Security related fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@Size(min=4, max = 24)
	@Getter @Setter
	private String username;
	
	@Column(length = 100, unique = false, nullable = false)
	@Getter @Setter
	private String password;
	
	@Column(nullable = false)
	@Getter @Setter
	private boolean enabled;

	@Column(nullable = false, unique = false)
	@Getter @Setter
	private boolean accountNonExpired;

	@Column(nullable = false, unique = false)
	@Getter @Setter
	private boolean accountNonLocked;

	@Column(nullable = false, unique = false)
	@Getter @Setter
	private boolean credentialsNonExpired;

	@ManyToOne
	@JoinColumn(name = "role_id", unique = false)
	@Getter @Setter
	private Role role;


	//Game related fields
	@OneToMany
	@JoinColumn(name = "user_id")
	@Getter @Setter
	private Set<Sector> sectors = new HashSet<>();

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "current_sector_id")
	private Sector currentSector;

	public User() {
		Sector testSector = Sector.generateNewDefaultSector();
		sectors.add(testSector);
		currentSector = testSector;
		getCurrentSector();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

	public boolean isUser() {
		return role.getName().equalsIgnoreCase("ROLE_USER") || role.getName().equalsIgnoreCase("ROLE_ADMIN");
	}

	public boolean isAdmin() {
		return role.getName().equalsIgnoreCase("ROLE_ADMIN");
	}

	//TODO all implementations of the Gameuser.
	@Override
	public int getPoints() {
		return 0;
	}

	public void setCurrentSector(Sector currentSector) {
		if (currentSector != null) {
			this.currentSector = currentSector;
		}
		else {
			throw new IllegalArgumentException("Cuurent sector null is not allowed.");
		}
	}

	public Sector getCurrentSector() {
		//TODO handle optional - if called an error 500 will return. (Means that currentSector is still empty after the get.)
		return currentSector;
	}
}
