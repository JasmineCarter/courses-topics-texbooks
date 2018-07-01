package courses;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
public class CourseControllerMockMvcTest {

	
	@Resource
	private MockMvc mvc; 
	
	@MockBean
	private CourseRepository courseRepo; 
	
	@MockBean
	private TopicRepository topicRepo; 
	
	@Mock
	private Course course; 
	
	@Mock 
	private Course anotherCourse; 
	
	@Test 
	public void shouldRouteToSingleCourseView() throws Exception {
		long arbitraryCourseId = 1; 
		when(courseRepo.findById(arbitraryCourseId)).thenReturn(Optional.of(course)); 
		mvc.perform(get("/course?id=1")).andExpect(view().name(is("course"))); 
	}
	
	@Test 
	public void shouldBeOkForSingleCourse() throws Exception {
		long arbitraryCourseId = 1; 
		when(courseRepo.findById(arbitraryCourseId)).thenReturn(Optional.of(course)); 
		mvc.perform(get("/course?id=1")).andExpect(status().isOk());
	}
	
	@Test 
	public void shouldNotBeOkForASingleCourse() throws Exception {
		 
		mvc.perform(get("/course?id=1")).andExpect(status().isNotFound());
		
	}
	
	@Test 
	public void shouldPutSingleCourseIntoModel() throws Exception {
		when(courseRepo.findById(1L)).thenReturn(Optional.of(course)); 
		
		mvc.perform(get("/course?id=1")).andExpect(model().attribute("courses", is(course)));

	}
	
	@Test 
	public void shouldRouteToAllCoursesView() throws Exception {
		mvc.perform(get("/show-courses")).andExpect(view().name(is("courses")));

	}
	
	@Test
	public void shouldBeOkForAllCourses() throws Exception {
		mvc.perform(get("/show-courses")).andExpect(status().isOk()); 

	}
	
	@Test 
	public void shouldPutAllCoursesIntoModel() throws Exception { 
		Collection<Course> allCourses = Arrays.asList(course, anotherCourse); 
		when(courseRepo.findAll()).thenReturn(allCourses); 
		mvc.perform(get("/show-courses")).andExpect(model().attribute("courses", is(allCourses))); 
		
	}
	
	
}
