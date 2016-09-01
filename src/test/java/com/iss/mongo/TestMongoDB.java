package com.iss.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cako.platform.user.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestMongoDB {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void saveTest() {

		System.out.println(mongoTemplate);

		User user1 = new User();
		user1.setLoginName("tom");
		user1.setPassword("123456");
		user1.setNickName("tom");
		user1.setEmail("tom@gmail.com");

		User user2 = new User();
		user2.setLoginName("admin");
		user2.setPassword("123456");
		user2.setNickName("admin");
		user2.setEmail("admin@admin.com");

		User user3 = new User();
		user3.setLoginName("feihong");
		user3.setPassword("123456");
		user3.setNickName("phj");
		user3.setEmail("test@gmail.com");
	}
}
