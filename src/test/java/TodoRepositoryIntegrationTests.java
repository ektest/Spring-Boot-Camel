import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emrekoca.SpringBootCamelApplication;
import com.emrekoca.camel.domain.Todo;
import com.emrekoca.camel.repository.TodoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringBootCamelApplication.class)
public class TodoRepositoryIntegrationTests {
	@Autowired
	TodoRepository todoRepo;

	@Test
	public void testPagenationFunctionalityTest() {
		/*
		 * A Page knows about the total number of elements and pages available.
		 * It does so by the infrastructure triggering a count query to
		 * calculate the overall number. As this might be expensive depending on
		 * the store used, Slice can be used as return instead. A Slice only
		 * knows about whether there’s a next Slice available which might be
		 * just sufficient when walking thought a larger result set.
		 */
		for (Todo todo : todoRepo.findBySearchTerm("test", new PageRequest(0, 5,
				new Sort(Sort.Direction.DESC, "description").and(new Sort(Sort.Direction.ASC, "title"))))) {
			System.out.println(todo);
		}
		// sort the query results in descending order by using the value of the
		// description field and in ascending order
		// return new Sort(Sort.Direction.DESC, "description").and(new Sort(Sort.Direction.ASC, "title"));
		System.out.println(" Page the result: ");
		Slice<Todo> todos = todoRepo.findBySearchTerm3("test", new PageRequest(0, 2,
				new Sort(Sort.Direction.DESC, "description").and(new Sort(Sort.Direction.ASC, "title"))));
		for (Todo todo : todos.getContent()) {
			System.out.println(todo);
		}
		System.out.println("Or use For each and lamba expression;");
		todos.forEach(action -> {
			System.out.println(action);
		});
		// Let's write some JUnit test cases
		Page<Todo> pageableTodo = todoRepo.findBySearchTerm2("test",
				new PageRequest(0, 2, new Sort(Sort.Direction.ASC, "title")));
		assertEquals(pageableTodo.getNumberOfElements(), 2);
		pageableTodo = todoRepo.findBySearchTerm2("test", new PageRequest(1, 2, new Sort(Sort.Direction.ASC, "title")));
		assertEquals(pageableTodo.getNumberOfElements(), 1);
		pageableTodo = todoRepo.findBySearchTerm2("test", new PageRequest(2, 2, new Sort(Sort.Direction.ASC, "title")));
		assertEquals(pageableTodo.getNumberOfElements(), 0);
	}

	@Test
	public void testTodoRepositoryTest() {

		System.out.println(" Find by name or email contains one of inputs and ignore case sensative: ");
		for (Todo todo : todoRepo.findBySearchTermNamed("something")) {
			System.out.println(todo);
		}

		System.out.println(" Sort the result: ");
		for (Todo todo : todoRepo.findBySearchTerm("something",
				new Sort(Sort.Direction.DESC, "title", "description"))) {
			System.out.println(todo);
		}
		System.out.println(todoRepo.findByTitleIs("testing").size());
		// Let's write one JUnit test cases
		assertEquals(todoRepo.findByTitleIs("testing").size(), 1);
	}
}