package com.douban.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

import com.guman.common.json.JSON;
import org.joda.time.DateTime;

import com.sun.deploy.util.StringUtils;

/**
 * Created by duanhaoran on 2018/8/21. 
 */
public class Test {
	public static void main(String[] args) {
		Map<String,Object> map=new HashMap<>();
		map.put("a","1");
		map.put("b","2");
		Object obj=map;
		Object objs=new Object();
		System.out.println(objs instanceof Map);
		String string = JSON.writeValueAsString(new int[]{0, 1, 2, 3, 4, 5});
		System.out.println(string);
	}

	public void viladateStringBufeerAndBuilder(){
		long timeMillis = System.currentTimeMillis();
		StringBuffer sb=new StringBuffer();
		//StringBuilder sb=new StringBuilder();
		for(int i=0;i<10000000;i++){
			sb.append("snjdsdsjdjsdjsdhjsd");
		}
		System.out.println(System.currentTimeMillis()-timeMillis);
	}



	/*private static int compare(String a, String b) {
		String c1 = a + b;
		String c2 = b + a;
		return c1.compareTo(c2);
	}*/

	public void test1() {
		Map map = new HashMap();
		map.put("a", "b");
		map.put(null, "c");
		List<String> list = new ArrayList<>();
		list.add("asvasvsadasdasd");
		list.add("ajhbfhabdasdak");
		list.add("sjandfbijasbd");
		String join = StringUtils.join(list, ",");
		System.out.println(join);
		System.out.println(map.get(null) + " " + map.containsKey(null));
		Stream.of(map).forEach(System.out::println);
		Queue queue = new ArrayBlockingQueue(10);

	}
}
