package nl.crado.game.captclash.model.user;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import nl.crado.game.captclash.game.sector.Sector;

@Entity
public class User implements UserDetails {
	
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

	@OneToOne
	@JoinColumn(name = "role_id")
	@Getter @Setter
	private Role role;
	
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

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


}
