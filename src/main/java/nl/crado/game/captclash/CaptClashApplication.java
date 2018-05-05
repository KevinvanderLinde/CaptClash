package nl.crado.game.captclash;

import nl.crado.game.captclash.game.logic.GameLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaptClashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptClashApplication.class, args);
	}
	
	@Bean
	public GameLogic getGameLogic() {
		return new GameLogic();
	}
}
