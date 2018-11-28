package spring13_rest.main;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import spring13_rest.bean.User;

public class MainClass {
	public static void main(String[] args) {
		RestTemplate template = new RestTemplate();
		//doGet(template);
		doPut(template);
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
}
