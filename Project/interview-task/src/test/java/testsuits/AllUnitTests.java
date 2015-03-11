package testsuits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.e3learning.onlineeducation.service.impl.AccountServiceImpTest;

@RunWith(Suite.class)
@SuiteClasses({ AccountServiceImpTest.class})
public class AllUnitTests {

}
