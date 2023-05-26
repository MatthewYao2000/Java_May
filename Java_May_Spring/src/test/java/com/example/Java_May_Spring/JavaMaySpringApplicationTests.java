package com.example.Java_May_Spring;

import com.example.Java_May_Spring.controllers.CalculatorController;
import com.example.Java_May_Spring.entity.MyFirstTdd;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)// sample spring context for unit testing

@WebMvcTest(CalculatorController.class) // specify which controller you want to test
//@SpringBootTest
@AutoConfigureMockMvc
class JavaMaySpringApplicationTests {

	private static MyFirstTdd myFirstTdd;

	@BeforeAll// before all tests execute, you need to do beforeAll function
	public static void init(){
		myFirstTdd = new MyFirstTdd();
	}

	@Autowired
	private MockMvc mockMvc;
// action: type localhost:8080/hello -> enter
	@Test
	public void testHelloMethodInCalculatorController() throws  Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/hello")// performs a request to /hello with the accept header value MediaType.APPLICATION_JSON
				.accept(MediaType.APPLICATION_JSON))// make it Json
				.andExpect(status().isOk())// I expect http status code 200 from the return
				.andExpect(content().string(equalTo("hello")));// I expect "hello" from the return

	}

	@Test
	public  void testAddFunctionInCalCulatorControllerWithVariable() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/add/1.0/2.0")// performs a request to /hello with the accept header value MediaType.APPLICATION_JSON
						.accept(MediaType.APPLICATION_JSON))// make it Json
				.andExpect(status().isOk())// I expect http status code 200 from the return
				.andExpect(content().string(containsStringIgnoringCase("3.0")));
		//"{"result" : 3.0}" != 3.0
	}

	@Test
	public  void testWelcomeMethodWithObject() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/welcome")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsStringIgnoringCase("welcome object")));

	}

	@Test
	public void emptyInputWithMyFirstTdd(){
		// first input str = "" -> IllegalArgument exception
		assertThrows(IllegalArgumentException.class, ()->myFirstTdd.firstUniqueCharacter(""));

	}

	@Test
	public void nullInputWithMyFirstTdd(){
		assertThrows(IllegalArgumentException.class, ()-> myFirstTdd.firstUniqueCharacter(null));
	}

	@Test
	public void noUniqueChar(){
		assertThrows(IllegalArgumentException.class, ()-> myFirstTdd.firstUniqueCharacter("AAAAA"));
	}

	@Test
	public void validInput(){
		assertEquals(myFirstTdd.firstUniqueCharacter("A A"), ' ');
		assertEquals(myFirstTdd.firstUniqueCharacter("1 2"), '1');
		assertEquals(myFirstTdd.firstUniqueCharacter("Aa"), 'A');
	}
}
