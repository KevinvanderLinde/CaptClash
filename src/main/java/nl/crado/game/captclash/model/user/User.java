package nl.crado.game.captclash.model.user;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

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
	@Size(min=8, max = 24)
	@Getter @Setter
	private String username;
	
	@Column(length = 100, unique = false, nullable = false)
	@Getter @Setter
	private String password;
	
	@Column(nullable = false)
	@Getter @Setter
	private boolean enabled;

	@Column(nullable = false, unique = true)
	@Getter @Setter
	private boolean accountNonExpired;

	@Column(nullable = false, unique = true)
	@Getter @Setter
	private boolean accountNonLocked;

	@Column(nullable = false, unique = true)
	@Getter @Setter
	private boolean credentialsNonExpired;

	@OneToOne
	@JoinColumn(name = "role_id")
	@Getter @Setter
	private Role role;


	//Game related fields
	@OneToMany
	@JoinColumn(name = "sector_id")
	@Getter @Setter
	private Set<Sector> sectors = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		return authorities;
	}

}
