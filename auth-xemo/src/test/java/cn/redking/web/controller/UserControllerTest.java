package cn.redking.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testQuery() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/user").param("username", "redking").param("age", "18")
//				.param("ageTo", "40").param("xx", "xx")
//				.contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
		/**
		 * 测试JsonView的UserSimpleView效果，打印结果显示，User只会显示username字段，却屏蔽了password字段
		 */
		String result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user").param("username", "redking").param("age", "18")
				.param("ageTo", "40").param("xx", "xx")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void testGetInfo() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("redking"));
		/**
		 * 测试JsonView的UserDetailView效果，打印结果显示，User显示username和password字段
		 */
		String result = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("redking")).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void testGetInfoParamId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user/xx").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void testCreateUser() throws Exception {
		Date date = new Date();
		String content = "{\"username\":\"redking\", \"password\":null,\"username\":\"" + date.getTime() + "\"}";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
	}

	@Test
	public void testUpdate() throws Exception {
		Date date = new Date(
				LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		String content = "{\"id\":\"1\",\"username\":\"redking\", \"password\":null,\"birthday\":\"" + date.getTime()
				+ "\"}";
		mockMvc.perform(
				MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));
	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
