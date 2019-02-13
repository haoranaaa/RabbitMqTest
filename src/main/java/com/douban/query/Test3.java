package com.douban.query;

import com.douban.query.model.Test3Vo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.annotation.Resource;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 19 九月 2018
 */
public class Test3 {
    public static void main(String[] args) {
        /*Test3Vo test3Vo=new Test3Vo();
        sss(test3Vo);
        LogAdaptor<Test3Vo> logAdaptor=new CommonLogBase<>();
        logAdaptor.commonLog(null,test3Vo);*/
        List<String> list=new ArrayList();
        list.add("Jjjjjjj");
        list.add("Jjjjjjj");
        list.add("Jjjjjjj");
        list.add("Jjjjjjj");
        list.add("Jjjjjjj");
        String x="100037001, 100008002, 100008001, 100001050, 100001051, 100001052, 100001053, 100008003, 100001055";
        List<String> collect = Stream.of(x.trim().split(",")).collect(Collectors.toList());

        StringBuilder sb=new StringBuilder();
        sb.append("insert into shop_date_data(shop_code,data_key,data_value) VALUES(");
        for(int i=0;i<collect.size();i++){
            sb.append("'"+collect.get(i)+"',"+"'shop_payable_amount','"+(10+i)*50+"'),(");
        }
        System.out.println(sb.toString());


    }
    public static void sss(Object obj){

    }
}
