package com.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于spring和redis的redistmeple工具类
 * 针对所有的hash方法
 * 针对所有set
 * 针对所有list
 */
public class RedisUtil {
    private RedisTemplate<String,Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间 ：秒
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if(time > 0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取过期时间
     * @param key 键 不能为null
     * @return 0 代表长期有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return
     */
    public boolean haskey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param  key 可以为多个或者单个
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 普通缓存获取
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存 +过期时间
     * @param key
     * @param value
     * @param time time要大于0 如果time小于等于0 将设置无限期
     * @return
     */
    public boolean set(String key,Object value,long time){
        try {
            if(time > 0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else{
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key
     * @param delta 要增加的数量 > 0
     * @return
     */
    public long incr(String key,long delta){
        if(delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    /**
     * 递减
     * @param key
     * @param delta 要增加的数量 > 0
     * @return
     */
    public long decr(String key,long delta){
        if(delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    /**
     *
     * @param key 键 not null
     * @param item 项 not null
     * @return
     */
    public Object hget(String key,String item){
        return  redisTemplate.opsForHash().get(key,item);
    }

    /**
     * 获取对应所有键值
     * @param key
     * @return 对应的多个键值
     */
    public Map<Object,Object>hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hashset
     * @param key
     * @param map 对应多个键值
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hashset 并设置时间
     * @param key
     * @param map
     * @param time 秒
     * @return
     */
    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if(time > 0){
                expire(key,time);
            }
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
