package com.zzyy.study.monitor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

/**
 * @auther zzyy
 * @create 2020-11-08 16:29
 */
public class RedisMonitorDemo
{
    public static final String HOST = "172.24.29.14";
    public static final int PORT = 7005;

    public static void main(String[] args)
    {
        //只能监控单机redis，对于集群只能多个监听一块走
        Jedis jedis = new Jedis(HOST, PORT);

        System.out.println("------开始监听。。。。。。");
        jedis.monitor(new JedisMonitor()
        {
            @Override
            public void onCommand(String command)
            {
                System.out.println("------最近正在使用的key： "+command);
            }
        });




    }
}
