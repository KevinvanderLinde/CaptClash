package nl.crado.game.captclash.security;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.crado.game.captclash.model.dao.BuildingDao;
import nl.crado.game.captclash.model.dao.RoleDao;
import nl.crado.game.captclash.model.dao.SectorDao;
import nl.crado.game.captclash.model.user.User;
import nl.crado.game.captclash.security.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nl.crado.game.captclash.model.userservice.UserService;

@Controller
public class LoginController {

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private SectorDao sectorDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginForm(Model model, HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("error") != null) {
			model.addAttribute("error", request.getSession().getAttribute("error"));
			request.getSession().removeAttribute("error");
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
		model.addAttribute("page_text", "This is the index!");
		return "index";
	}

	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public final String profile(Model model, Principal principal) {
		addUserToModel(model, principal);
		model.addAttribute("page_text", "This is your profile page!");
		model.addAttribute("page_footer", "This is the footer!");
		return "profile";
	}

	private void addUserToModel(Model model, Principal principal) {
		model.addAttribute("user", userService.findByUsername(principal.getName()));
	}

	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String registerPage(Model model, HttpServletRequest request, Principal principal) {
		if (principal != null) {
			return "redirect:/index";
		}
	return "register";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String handleRegister(Model model, @RequestBody MultiValueMap<String,String> formData) {

		//TODO add some logic

		String username = formData.getFirst("username");
		String password = formData.getFirst("password");

		System.out.println(username + " : " + password);

		Optional<Role> role = roleDao.findById(1L);

		if (role.isPresent()) {
			User user = userService.findByUsername(username);

			if (user != null) {
				model.addAttribute("error", "Username already in use!");
				return "register";
			}
			else {
				user = new User();
				user.setUsername(username);
				user.setPassword(passwordEncoder.encode(password));
				user.setRole(role.get());

				user.setAccountNonExpired(true);
				user.setAccountNonLocked(true);
				user.setCredentialsNonExpired(true);
				user.setEnabled(true);
				
				user.getCurrentSector().setSectorName("Sector of " + username);
				
				saveUser(user);
			}
		}
		return "redirect:/login";
	}

	private void saveUser(User user) {
		user.getSectors().forEach(sector -> buildingDao.saveAll(sector.getBuildings()));
		sectorDao.saveAll(user.getSectors());
		userService.saveUser(user);
	}


	//Search user (Type ahead) in combi met javascript

}
