package com.itcast.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {

    @Autowired
    static JedisPool jedisPool;

    public static String setex(String key, int seconds, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, seconds, value);
        } catch (Exception e) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return "";
    }


    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return "";
    }


    public static Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {

        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return 0L;
    }
}
