import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emrekoca.SpringBootCamelApplication;
import com.emrekoca.camel.domain.User;
import com.emrekoca.camel.repository.UserRepository;
import com.emrekoca.camel.repository.UserRepositoryCrud;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringBootCamelApplication.class)
public class UserRepositoryIntegrationTests {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserRepositoryCrud userRepoCrud;

	@Test
	public void testUserRepositoryTest() {
		System.out.println(" Find all: ");
		for (User user : userRepo.findAll()) {
			System.out.println(user);
		}
		System.out.println(" Find by email and name: ");
		System.out.println(userRepo.findByNameAndEmail("Emre", "ek@ek.com"));
		System.out.println(" Find by email and order by emal asc: ");
		for (User user : userRepo.findFirst3ByEmailOrderByEmailAsc("ek@ek.com")) {
			System.out.println(user);
		}
		System.out.println(" Find by name or email contains one of inputs and ignore case sensative: ");
		for (User user : userRepo.findByNameContainsOrEmailContainsAllIgnoreCase("Emre", "none")) {
			System.out.println(user);
		}
		System.out.println(" Search by input using native sql: ");
		for (User user : userRepo.findBySearchTermNative("Nilo")) {
			System.out.println(user);
		}
		// Let's write some JUnit test cases
		assertEquals(userRepo.findAll().size(), 8);
	}

	@Test
	public void testTodoRepositoryTest() {
		userRepoCrud.findAll().forEach(action -> {
			assertNotNull(action);
		});
		assertEquals(userRepoCrud.count(), 8);
		assertNotNull(userRepoCrud.findOne(1));
	}
}