package cn.itheima.bos.redis.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RedisTemplateTest {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void testRedis() {
		// ����key value
		// ����30��ʧЧ
		redisTemplate.opsForValue().set("city", "����", 30, TimeUnit.SECONDS);

		System.out.println(redisTemplate.opsForValue().get("city"));
	}
}

