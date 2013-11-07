
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Lukas Lowinger
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/WEB-INF/context/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true, transactionManager="txManager")
@Transactional //extend the transactions to whole tests in order to rollback the tests
public abstract class AbstractServiceTest {
    
    //TODO: vytvoreni uzivatele a ten vytvori event a prida do nej terminy a prida do nej participanta - uzivatel, prida do eventu jinej termin a prvni zmeni status
    public AbstractServiceTest() {
    }
     
}
