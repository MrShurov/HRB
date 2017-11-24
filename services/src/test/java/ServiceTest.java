import enums.Results;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.*;
import services.IBetService;
import services.IEventService;
import services.ITeamService;
import services.IUserService;

import java.util.List;

import static org.junit.Assert.assertNotSame;

@SuppressWarnings("Duplicates")
@ContextConfiguration("/authContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "txManager")
public class ServiceTest {
    @Autowired
    private IBetService betService;
    @Autowired
    private IEventService eventService;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IUserService userService;

    @Test
    public void UserTest(){
        User user = new User("Shurov","qwerty");
        Info info = new Info(100.0, user);
        user.setInfo(info);
        userService.add(user);
        Assert.assertEquals("is working",user,userService.getByUserName("Shurov"));
        User userFromDB = userService.getByUserName("Shurov");
        userFromDB.setPassword("1234567");
        userService.update(userFromDB);
        Assert.assertNotEquals("is working","qwerty",userService.getByUserName("Shurov").getPassword());
        Assert.assertEquals("is working",userFromDB,userService.getByUserName("Shurov"));
        List<User> list = userService.getAll();
        int size = list.size();
        userService.delete(userFromDB);
        assertNotSame(userService.getAll().size(), size);
    }

    @Test
    public void BetTest(){
        User user = new User("Shur","qwerty");
        userService.add(user);
        Event event = new Event(null,null);
        Bet bet = new Bet(event.getId(),user.getId(),0, Results.WIN1.getResult());
        eventService.add(event);
        betService.add(bet);
        Assert.assertEquals("is working",bet,betService.get(bet.getId()));
        Bet betFromDB = betService.get(bet.getId());
        betFromDB.setPossibleWin(1.0);
        betService.update(betFromDB);
        Assert.assertNotEquals("is working",0,betService.get(bet.getId()).getPossibleWin());
        Assert.assertEquals("is working",betFromDB,betService.get(bet.getId()));
        int size = betService.getAllByUserId(bet.getUserId()).size();
        betService.delete(betFromDB);
        assertNotSame(betService.getAllByUserId(bet.getUserId()).size(), size);
    }

    @Test
    public void EventTest(){
        Event event = new Event(null,null);
        eventService.add(event);
        Assert.assertEquals("is working",event,eventService.get(event.getId()));
        Event eventFromDB = eventService.get(event.getId());
        eventFromDB.setDrawCoefficient(1.0);
        eventService.update(eventFromDB);
        Assert.assertNotEquals("is working",0,eventService.get(event.getId()).getDrawCoefficient());
        Assert.assertEquals("is working",eventFromDB,eventService.get(event.getId()));
        List<Event> list = eventService.getAll();
        int size = list.size();
        eventService.delete(eventFromDB);
        assertNotSame(eventService.getAll().size(), size);
    }

    @Test
    public void TeamTest(){
        Team team = new Team("MU",0,0);
        teamService.add(team);
        Assert.assertEquals("is working",team,teamService.getByName("MU"));
        Team teamFromDB = teamService.getByName("MU");
        teamFromDB.setLoseAmount(17);
        teamService.update(teamFromDB);
        Assert.assertNotEquals("is working",0,teamService.getByName("MU").getLoseAmount());
        Assert.assertEquals("is working",teamFromDB,teamService.getByName("MU"));
        List<Team> list = teamService.getAll();
        int size = list.size();
        teamService.delete(teamFromDB);
        assertNotSame(teamService.getAll().size(), size);
    }
}
