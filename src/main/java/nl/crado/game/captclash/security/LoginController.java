package nl.crado.game.captclash.security;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.crado.game.captclash.model.test.UserService;
import nl.crado.game.captclash.model.user.User;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginForm(Model model, HttpServletRequest request) {
		if (request.getHeader("error") != null) {
			model.addAttribute("error", request.getHeader("error"));
		}
		return "login";
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public final String root(Model model, HttpServletRequest request) {
		return "redirect:/index";
	}


	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public final String index(Model model, Principal principal) {
		addUserToModel(model, principal);
		Set<String> index = new HashSet<>();
		index.add("profile");
		model.addAttribute("index", index);
		model.addAttribute("page_text", "This is the index!");
		return "index";
	}

	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public final String profile(Model model, Principal principal) {
		addUserToModel(model, principal);
		model.addAttribute("page_text", "This is your profile page!");
		return "profile";
	}

	private void addUserToModel(Model model, Principal principal) {
		model.addAttribute("user", userService.findByUsername(principal.getName()));
	}
}
