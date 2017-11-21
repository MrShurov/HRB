package MnvHRB;

import dao.IBetDao;
import dao.IEventDao;
import dao.ITeamDao;
import dao.IUserDao;
import enums.Results;
import enums.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.*;

import java.util.List;

import static org.junit.Assert.assertNotSame;

@SuppressWarnings("Duplicates")
@ContextConfiguration("/test-beans-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "txManager")
public class DaoTest {
    @Autowired
    private IBetDao betDao;
    @Autowired
    private IEventDao eventDao;
    @Autowired
    private ITeamDao teamDao;
    @Autowired
    private IUserDao userDao;

    @Test
    public void EnumTest(){
        User user = new User("Shurov","qwerty");
        Info info = new Info(100.0, user);
        user.setInfo(info);
        userDao.add(user);
        System.out.println(userDao.getByUserName("Shurov").getInfo());
        System.out.println(Status.ACTIVE.getStatus());
        System.out.println(Results.DRAW.getResult());
    }

    @Test
    public void getAllUsernamesTest(){
        List<String> list = userDao.getAllUsernames();
        System.out.println(list);
    }

    @Test
    public void UserTest(){
        User user = new User("Shurov","qwerty");
        Info info = new Info(100.0, user);
        user.setInfo(info);
        userDao.add(user);
        Assert.assertEquals("is working",user,userDao.getByUserName("Shurov"));
        User userFromDB = userDao.getByUserName("Shurov");
        userFromDB.setPassword("1234567");
        userDao.update(userFromDB);
        Assert.assertNotEquals("is working","qwerty",userDao.getByUserName("Shurov").getPassword());
        Assert.assertEquals("is working",userFromDB,userDao.getByUserName("Shurov"));
        List<User> list = userDao.getAll();
        int size = list.size();
        userDao.delete(userFromDB);
        assertNotSame(userDao.getAll().size(), size);
    }

    @Test
    public void BetTest(){
        User user = new User("Shur","qwerty");
        userDao.add(user);
        Event event = new Event(null,null);
        Bet bet = new Bet(event,user.getId(),0,0, Results.WIN1.getResult());
        eventDao.add(event);
        betDao.add(bet);
        Assert.assertEquals("is working",bet,betDao.get(bet.getId()));
        Bet betFromDB = betDao.get(bet.getId());
        betFromDB.setBet(1.0);
        betDao.update(betFromDB);
        Assert.assertNotEquals("is working",0,betDao.get(bet.getId()).getBet());
        Assert.assertEquals("is working",betFromDB,betDao.get(bet.getId()));
        int size = betDao.getAllByUserId(bet.getUserId()).size();
        betDao.delete(betFromDB);
        assertNotSame(betDao.getAllByUserId(bet.getUserId()).size(), size);
    }

    @Test
    public void EventTest(){
        Event event = new Event(null,null);
        eventDao.add(event);
        Assert.assertEquals("is working",event,eventDao.get(event.getId()));
        Event eventFromDB = eventDao.get(event.getId());
        eventFromDB.setDrawCoefficient(1.0);
        eventDao.update(eventFromDB);
        Assert.assertNotEquals("is working",0,eventDao.get(event.getId()).getDrawCoefficient());
        Assert.assertEquals("is working",eventFromDB,eventDao.get(event.getId()));
        List<Event> list = eventDao.getAll();
        int size = list.size();
        eventDao.delete(eventFromDB);
        assertNotSame(eventDao.getAll().size(), size);
    }

    @Test
    public void TeamTest(){
        Team team = new Team("MU",0,0);
        teamDao.add(team);
        Assert.assertEquals("is working",team,teamDao.getByName("MU"));
        Team teamFromDB = teamDao.getByName("MU");
        teamFromDB.setLoseAmount(17);
        teamDao.update(teamFromDB);
        Assert.assertNotEquals("is working",0,teamDao.getByName("MU").getLoseAmount());
        Assert.assertEquals("is working",teamFromDB,teamDao.getByName("MU"));
        List<Team> list = teamDao.getAll();
        int size = list.size();
        teamDao.delete(teamFromDB);
        assertNotSame(teamDao.getAll().size(), size);
    }
}
