package nl.crado.game.captclash.searcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class SearchPageController {

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String getUsers(Model model) {

        return "search";
    }

    @RequestMapping(path = "/remote", method = RequestMethod.GET)
    public String remote(Model model) {

        return "remote";
    }

    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArrayList<String> autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {
        System.out.println("searchstr: " + searchstr);


        ArrayList<String> suggestions = new ArrayList<>();

        String[] locales = Locale.getISOCountries();

        for (String countryCode : locales) {

            Locale obj = new Locale("", countryCode);
            // add all countries to the arraylist
            // if on the query string
            if (obj.getDisplayCountry().toLowerCase().contains(searchstr.toLowerCase())) {

            }
        }


        // truncate the list to the first n, max 20 elements
        int n = suggestions.size() > 5 ? 5 : suggestions.size();
        List<String> sulb = new ArrayList<>(suggestions.subList(0, n));

        return sulb;
    }
}
