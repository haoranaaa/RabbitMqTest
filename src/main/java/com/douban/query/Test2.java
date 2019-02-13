package com.douban.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 05 九月 2018
 */
public class Test2 {
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,1000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(100));

	public static Integer ONES_SHOP_NUM=2;
	public static void main(String[] args) {
		Test2 test2=new Test2();
		List<String> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			list.add("a"+i);
		}
		List<String> list1 = test2.filterShop(list);
		System.out.println(list1);
		executor.shutdown();
	}

	public List<String> filterShop(List<String> allShopCode) {
		try {
			List<String> partShopCode;
			int count = allShopCode.size() % ONES_SHOP_NUM == 0 ? allShopCode.size() / ONES_SHOP_NUM : allShopCode.size() / ONES_SHOP_NUM + 1;
			List<String>[] lists=new List[count];
			for (int i = 0; i < count; i++) {
				if (i == count -1) {
					partShopCode = allShopCode.subList(i * ONES_SHOP_NUM, allShopCode.size());
				}
				else {
					partShopCode = allShopCode.subList(i * ONES_SHOP_NUM, (i+1) * ONES_SHOP_NUM );
				}
				final List<String> listStr = partShopCode;
				lists[i]=listStr;
			}
			System.out.println("我要开始了");
			Stream.of(lists).forEach(System.out::print);
			 return Stream.of(lists).map(i ->
					CompletableFuture.supplyAsync(() -> repeatFilterShop(i), executor))
					.collect(Collectors.toList())
					.stream()
					.map(i->i.join())
			  .collect(ArrayList<String>::new,
					  ArrayList::addAll,ArrayList::addAll);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public List<String> repeatFilterShop(List<String> strings) {
		List<String> list=new ArrayList<>();
		System.out.println("我正在执行"+strings.get(0));
		if(strings.get(0).equals("a0")){
			try {
				System.out.println("我睡了");
				Thread.sleep(2000);
				System.out.println("我醒了！");
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<strings.size();i++){
			list.add("b"+strings.get(i));
		}
		return list;
	}

}