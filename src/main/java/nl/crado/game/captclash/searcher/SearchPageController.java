package nl.crado.game.captclash.searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import nl.crado.game.captclash.game.user.Gameuser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.crado.game.captclash.model.dao.UserDao;

@Controller
public class SearchPageController {

    private static final Logger logger = Logger.getLogger(SearchPageController.class.getName());

	@Autowired
	private UserDao userDao;
	
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String getUsers(Model model) {
        return "search";
    }

    @RequestMapping(value = "/api/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Gameuser> autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {
        logger.info("Searching for users with searchString: " + searchstr);
    	List<Gameuser> suggestions = new ArrayList<>();
    	int max = 0;
    	userDao.findAll().forEach(user -> {
    		if (user.getUsername().contains(searchstr) && max < 10) {
    			suggestions.add(user);
    		}
    	});
         return suggestions;
    }
}
