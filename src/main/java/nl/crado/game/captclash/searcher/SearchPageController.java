package nl.crado.game.captclash.searcher;

import java.util.Arrays;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchPageController {

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String getUsers(Model model) {
        return "search";
    }

    @Async
    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {
    	//TODO perform search here
         return Arrays.asList("Koek", "Tafel", "beker");
    }
}
