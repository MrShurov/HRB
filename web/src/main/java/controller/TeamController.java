package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojos.Event;
import pojos.Team;
import pojos.TeamsForCreate;
import services.ITeamService;

@Controller
public class TeamController {
    @Autowired
    private ITeamService teamService;

    @RequestMapping(value = "/team/add", method = RequestMethod.POST)
    public String updateEvent(ModelMap modelMap, Team team) {
        if (team == null) {
            modelMap.put("error",true);
            return "admin";
        }
        teamService.add(team);
        modelMap.put("createTeam",new Team());
        modelMap.put("AllTeams",teamService.getAll());
        modelMap.put("teams",new TeamsForCreate());
        modelMap.put("event",new Event());
        return "admin";
    }


}
