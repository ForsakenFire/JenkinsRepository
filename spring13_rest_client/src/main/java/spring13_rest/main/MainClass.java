package spring13_rest.main;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import spring13_rest.bean.User;

public class MainClass {
	public static void main(String[] args) {
		RestTemplate template = new RestTemplate();
		//doGet(template);
		//doPut(template);
		//doPost(template);
		doExchange(template);
	}
	
	/**
	 * getForObject只有object，getForEntity返回的是ResponseEntity，包含其他信息。
	 * @param template
	 * @param url
	 * @param paramMap
	 */
	@SuppressWarnings("unused")
	private static void doGet(RestTemplate template) {
		Map<String,String> param = new HashMap<>();
		String url = "http://127.0.0.1:9999/spring13_rest_server/restGet/{id}.do";
		param.put("id", "2");
		User user1 = template.getForObject(url, User.class, param);
		System.out.println(user1);
		ResponseEntity<User> entity = template.getForEntity(url, User.class,param);
		System.out.println(entity.getBody());
		HttpHeaders headers = entity.getHeaders();
		System.out.println(headers.getLastModified());
	}
	
	
	/**
	 * PUT方法用于更新
	 * @param template
	 */
	private static void doPut(RestTemplate template) {
		Map<String,String> param = new HashMap<>();
		String url = "http://127.0.0.1:9999/spring13_rest_server/restPut/{id}.do";
		param.put("id", "2");
		User user1 = new User();
		user1.setName("restPutModify");
		user1.setPhone("updatePhone123");
		template.put(url, user1, param);
	}
	
	/**
	 * Post方法用于新增.
	 * postforentity包括头部信息，postforlocation从头部信息中获取location
	 * @param template
	 */
	private static void doPost(RestTemplate template) {
		String url = "http://127.0.0.1:8089/spring13_rest_server/restPost.do";
		User user1 = new User();
		user1.setName("restPostAdd");
		user1.setPhone("addPhone123");
//		User retUser = template.postForEntity(url, user1, User.class).getBody();
//		System.out.println("client接收反馈的user："+retUser.toString());
		System.out.println(template.postForLocation(url, user1).toString());
		
	}
	
	/**
	 * exchange方法可以完成其他方法的所有功能
	 * 通过httpmethod参数指定操作
	 * @param template
	 */
	private static void doExchange(RestTemplate template) {
		String url = "http://127.0.0.1:8089/spring13_rest_server/restExchange.do";
		User user1 = new User();
		user1.setName("restPostAdd");
		user1.setPhone("addPhone123");
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> typeList = new ArrayList<>();
		typeList.add(MediaType.APPLICATION_XML);
		typeList.add(MediaType.APPLICATION_JSON);
		headers.setAccept(typeList);
		//headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<User> requestEntity = new HttpEntity<>(user1,headers);
		User user2 = template.exchange(url, HttpMethod.POST, requestEntity, User.class).getBody();
		System.out.println(user2);
	}
}
