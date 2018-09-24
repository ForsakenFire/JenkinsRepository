package testweb;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import spring5.web.HelloController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


public class HelloControllerTest {
	@Test
	public void testHello() throws Exception{
		HelloController controller = new HelloController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/hello.do")).andExpect(view().name("Hello"));
	}
}
