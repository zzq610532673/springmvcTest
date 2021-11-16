package com.zzq.redis;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    
    private static JedisConnectionFactory jedisConnectionFactory;
    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisCache(String id) {
        if (id==null){
            throw new IllegalArgumentException("Cache instance require an ID");
        }
        this.id = id;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            connection.set(serializer.serialize(o), serializer.serialize(o1));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public Object getObject(Object o) {
        Object result = null;
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = serializer.deserialize(connection.get(serializer.serialize(o)));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.close();
            }
        }
        return result;
    }

    @Override
    public Object removeObject(Object o) {
        RedisConnection connection = null;
        Object result = null;
        try{
            connection = jedisConnectionFactory.getConnection();
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = connection.expire(serializer.serialize(o), 0);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public void clear() {
        RedisConnection connection = null;
        try{
            connection = jedisConnectionFactory.getConnection();
            connection.flushDb();
            connection.flushAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.close();
            }
        }
        
    }

    @Override
    public int getSize() {
        int result = 0;
        RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }
}
