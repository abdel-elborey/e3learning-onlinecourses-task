package testsuits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.e3learning.onlineeducation.controller.AccountControllerIT;
import com.e3learning.onlineeducation.controller.CourseControllerIT;
import com.e3learning.onlineeducation.repository.AccountRepositoryIT;
import com.e3learning.onlineeducation.repository.AddressRepositoryIT;
import com.e3learning.onlineeducation.repository.CountryRepositoryIT;
import com.e3learning.onlineeducation.repository.CourseRepositoryIT;
import com.e3learning.onlineeducation.repository.TrainingRepositoryIT;
import com.e3learning.onlineeducation.service.impl.AccountServiceImplT;
import com.e3learning.onlineeducation.service.impl.CountryServiceImplT;
import com.e3learning.onlineeducation.service.impl.CourseServiceImplT;
import com.e3learning.onlineeducation.service.impl.TrainingServiceImplT;

@RunWith(Suite.class)
@SuiteClasses({ AccountControllerIT.class, CourseControllerIT.class, AccountRepositoryIT.class,
		AddressRepositoryIT.class, CountryRepositoryIT.class, CourseRepositoryIT.class, TrainingRepositoryIT.class,
		AccountServiceImplT.class, CountryServiceImplT.class, CourseServiceImplT.class, TrainingServiceImplT.class })
public class AllIntegrationTests {

}
