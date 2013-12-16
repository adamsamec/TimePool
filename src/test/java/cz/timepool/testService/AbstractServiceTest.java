package cz.timepool.testService;

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
@ContextConfiguration(locations = {"/WEB-INF/context/applicationContext.xml"})
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@Transactional
public abstract class AbstractServiceTest {
    
}
