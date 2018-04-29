package nl.crado.game.captclash.security;

import java.security.Principal;

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
		return "login";
	}


	@RequestMapping(path = "/", method = RequestMethod.GET)
	public final String root(Model model, HttpServletRequest request) {
		return "redirect:/index";
	}


	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public final String index(Model model, Principal principal) {
		return "index";
	}

	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public final String profile(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		return "profile";
	}
}
