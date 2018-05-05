package nl.crado.game.captclash.game.logic;

import nl.crado.game.captclash.game.sector.Sector;
import nl.crado.game.captclash.model.dao.SectorDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

public class GameLogic {

    @Autowired
    private SectorDao sectorDao;

    private Timer resourcesTimer;

    public GameLogic() {
        resourcesTimer = new Timer();
        resourcesTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handleResourceTick();
            }
        }, 1000, 1000);
    }

   public void handleResourceTick() {
        Iterable<Sector> sectors = sectorDao.findAll();
        sectors.forEach(sector -> {
                sector.handleResourceTick();
            });
        sectorDao.saveAll(sectors);
   }
}
