package part3.redisMailStore;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * JedisClient
 */
public class JedisClient {
    /**
     * Jedis instance
     */
    Jedis jedis;

    /**
     * JedisClient Constructor
     */
    public JedisClient(){

    }
    /**
     * Method to connect with localhost
     */
    public void connect(){
        jedis = new Jedis("localhost");
    }
    /**
     * Method to set key and value
     * @param key Key
     * @param value Value
     */
    public void set(String key, String value){
        jedis.set(key, value);
    }
    /**
     * Method to get value
     * @param key Key
     * @return Value
     */
    public String get(String key){
        return jedis.get(key);
    }
    /**
     * Method to push key with value
     * @param key Key
     * @param value Value
     */
    public void lpush(String key, String value){
        jedis.lpush(key, value);
    }
    /**
     * Method to get list of strings from list with key
     * @param key Key
     * @return strings list
     */
    public List<String> lrange(String key){
        return jedis.lrange(key, 0, -1);    //-1=last one
    }
}
