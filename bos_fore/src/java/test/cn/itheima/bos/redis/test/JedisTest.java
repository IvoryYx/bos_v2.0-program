package cn.itheima.bos.redis.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	@Test
	public void testRedis() {
		// ����localhost Ĭ�϶˿� 6379
		Jedis jedis = new Jedis("localhost");

		jedis.setex("company",60, "�������Ա");

		System.out.println(jedis.get("company"));
	}
}
