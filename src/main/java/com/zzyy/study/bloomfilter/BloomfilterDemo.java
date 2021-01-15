package com.zzyy.study.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import sun.security.util.AuthResources_zh_CN;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther zzyy
 * @create 2020-11-04 16:53
 */
public class BloomfilterDemo
{

    public static final Integer _1W = 10000;

    static Integer size = 100 * _1W;

    //该数字越小，误判率越低啊
    static double fpp = 0.01;


    public static void main(String[] args)
    {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size,fpp);

        //初始化布隆过滤器的100W样本数据，input
        for (int i = 1; i <=size ; i++) {
            bloomFilter.put(i);
        }

        ArrayList<Integer> list = new ArrayList<>(10 * _1W);

        for (int i = size+1; i <=size+100000 ; i++) {
            if (bloomFilter.mightContain(i)) {
                System.out.println("-----发生了误判："+i);
                list.add(i);
            }
        }
        System.out.println("------误判的数量："+list.size());


    }
}
