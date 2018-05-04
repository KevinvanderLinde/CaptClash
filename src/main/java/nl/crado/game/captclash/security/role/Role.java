package nl.crado.game.captclash.security.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Column(unique = true)
	@Getter @Setter
	private String name;

	public Role() {

	}

	public Role(String name) {
	this.name = name;
	}
}
