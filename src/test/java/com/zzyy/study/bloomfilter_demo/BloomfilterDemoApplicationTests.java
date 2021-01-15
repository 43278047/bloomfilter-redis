package com.zzyy.study.bloomfilter_demo;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class BloomfilterDemoApplicationTests
{
    public static final int _1W = 10000;

    //布隆过滤器里预计要插入多少数据
    public static int size = 100 * _1W;
    //误判率,它越小误判的个数也就越少(思考，是不是可以设置的无限小，没有误判岂不更好)
    public static double fpp = 0.03;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test01()
    {
        RBloomFilter<String> rBloomFilter = redissonClient.getBloomFilter("phoneList", new StringCodec());

        rBloomFilter.tryInit(size,fpp);
        rBloomFilter.add("huawei");

        System.out.println(rBloomFilter.contains("huawei"));
        System.out.println(rBloomFilter.contains("iphone12"));


    }
}
