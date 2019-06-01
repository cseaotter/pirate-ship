package lynbrook.sail.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author carol
 *
 */
@RunWith(Suite.class)

@Suite.SuiteClasses(
{ JUActorTest.class, JUControllerTest.class, JUDataTest.class, JUGuiTest.class, JUNetworkTest.class,
		JUScenarioTest.class })

public class JUSuite
{

}