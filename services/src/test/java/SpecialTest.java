import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojos.Team;
import pojos.User;
import services.IBetService;
import services.IEventService;
import services.ITeamService;
import services.IUserService;

@SuppressWarnings("Duplicates")
@ContextConfiguration("/authContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "txManager")
public class SpecialTest {
    @Autowired
    private IBetService betService;
    @Autowired
    private IEventService eventService;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IUserService userService;

    @Test
    public void createUserTest(){
        User user = new User("Shurov","3424323");
        userService.createUser(user);
        Assert.assertNotNull(userService.getByUserName(user.getUsername()));
    }
}
