package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojos.TeamsForCreate;
import pojos.User;
import services.IUserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/registration/page", method = RequestMethod.GET)
    public String getRegistrationPage(ModelMap modelMap) {
        modelMap.put("user",new User());
        return "registration";
    }

    @RequestMapping(value = "/login/page", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/admin/page", method = RequestMethod.GET)
    public String getAdminPage(ModelMap modelMap) {
        modelMap.put("teams",new TeamsForCreate());
        return "admin";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid User user, BindingResult br, ModelMap model) {
        if (!br.hasErrors()) {
            if (userService.isUnique(user)) {
                userService.createUser(user);
                model.put("user", user);
                return "login";
            } else {
                model.put("isUnique",false);
                return "registration";
            }
        } else {
            return "registration";
        }
    }
}
