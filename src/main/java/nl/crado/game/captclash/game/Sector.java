package nl.crado.game.captclash.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"loc_x", "loc_z"}) )
public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@Column(length = 32, unique = false, nullable = false, name = "Sector")
	@Getter @Setter
	private String sectorName;
	
	@Column(nullable = false, name = "loc_x")
	@Getter @Setter
	private Integer locationX;
	
	@Column(nullable = false, name = "loc_z")
	@Getter @Setter
	private Integer locationZ;
	
	//TODO add all the buildings.
	
	
}
