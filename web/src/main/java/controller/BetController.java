package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pojos.Bet;
import services.IBetService;
import services.IUserService;

import java.util.List;

@Controller
@RequestMapping("/bets")
public class BetController {
    @Autowired
    private IBetService betService;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getMainPage(ModelMap modelMap) {
        fillModel(modelMap);
        return "bets/main";
    }

    private void fillModel(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        pojos.User user1 = userService.getByUserName(user.getUsername());
        populatePageName(modelMap);
        List<Bet> list = betService.getAllByUserId(user1.getId());
        modelMap.put("bets", list);
    }

    private void populatePageName(ModelMap modelMap) {
        modelMap.addAttribute("currentPageName", "bets");
    }
}
