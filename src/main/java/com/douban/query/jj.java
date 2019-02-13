/*
package com.wormpex.dynamic.promotion.experiment.man.biz.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.swing.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wormpex.api.pojo.Status;
import com.wormpex.bach.root.bundles.lang.WAssert;
import com.wormpex.biz.BizTemplate;
import com.wormpex.biz.lang.Biz;
import com.wormpex.common.exception.StatusCodeException;
import com.wormpex.dynamic.promotion.experiment.api.constant.IndexTagConst;
import com.wormpex.dynamic.promotion.experiment.api.dto.IndexData;
import com.wormpex.dynamic.promotion.experiment.api.dto.IndexQuery;
import com.wormpex.dynamic.promotion.experiment.api.dto.IndexResult;
import com.wormpex.dynamic.promotion.experiment.api.dto.QueryParam;
import com.wormpex.dynamic.promotion.experiment.api.enums.CompareEnum;
import com.wormpex.dynamic.promotion.experiment.api.expressionBuilder.ExpressionBuilder;
import com.wormpex.dynamic.promotion.experiment.api.expressionBuilder.GroupBy;
import com.wormpex.dynamic.promotion.experiment.api.expressionBuilder.Relation;
import com.wormpex.dynamic.promotion.experiment.api.expressionBuilder.RelationBuilder;
import com.wormpex.dynamic.promotion.experiment.api.remote.IndexSearchRemote;
import com.wormpex.dynamic.promotion.experiment.api.remote.ShopCodeRemote;
import com.wormpex.dynamic.promotion.experiment.man.biz.ShopfilterBiz;
import com.wormpex.dynamic.promotion.experiment.man.biz.calculation.VectorCalculation;
import com.wormpex.dynamic.promotion.experiment.man.biz.executor.ConfigExecutors;
import com.wormpex.dynamic.promotion.experiment.man.model.vo.BackIndicatorVo;
import com.wormpex.dynamic.promotion.experiment.man.model.vo.FilterIndicatorVo;
import com.wormpex.dynamic.promotion.experiment.man.model.vo.ShopGroupVo;
import com.wormpex.dynamic.promotion.experiment.man.model.vo.ShopfilterReq;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

*/
/**
 *	根据条件 筛选门店
 * @author haoran.duan
 * @since 11 九月 2018
 *//*

@Biz
public class ShopfilterBizImpl implements ShopfilterBiz {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopfilterBizImpl.class);

	@Resource
	public ShopCodeRemote shopCodeRemote;

	@Resource
	public IndexSearchRemote indexSearchRemote;

	*/
/**
	 * 批次查询的条数，每个线程执行查询的数量
	 *//*

	public static final int ONES_SHOP_NUM = 40;

	private ExecutorService executor = ConfigExecutors.getExecutor("strategy_executor.json");

	@Override
	public ShopGroupVo selectShopByCodeList(ShopfilterReq shopfilterReq) {
		return new BizTemplate<ShopGroupVo>() {
			@Override
			protected void checkParams() {
				WAssert.notNull(shopfilterReq, "传入参数不能为空！");
				WAssert.notNull(shopfilterReq.getBackIndicatorVos(), "请输入需要展示的指标！");
			}

			@Override
			protected ShopGroupVo process() {
				if (Objects.nonNull(shopfilterReq.getGroupA()) && shopfilterReq.getGroupA().size() > 0 &&
						Objects.nonNull(shopfilterReq.getGroupB()) && shopfilterReq.getGroupB().size() > 0
						&& shopfilterReq.getGroupA().size() == shopfilterReq.getGroupB().size()) {
					List<Map<String, Object>> groupA = filterShop(shopfilterReq, shopfilterReq.getGroupA());
					List<Map<String, Object>> groupB = filterShop(shopfilterReq, shopfilterReq.getGroupB());
					return ShopGroupVo.builder().groupA(groupA)
							.groupB(groupB)
							.aGroupAvg(getIndexAvg(groupA))
							.bGroupAvg(getIndexAvg(groupB))
							.build();
				}
				List<String> allShopCode = shopCodeRemote.getAllShopCode();
				if (allShopCode == null || allShopCode.size() == 0) {
					LOGGER.error("获取ShopCodeList失败！");
					throw new StatusCodeException(Status.error("查询所有门店失败！"));
				}
				List<Map<String, Object>> lists = filterShop(shopfilterReq, allShopCode);
				if (lists.size() == 0) {return null;}
				ShopGroupVo shopGroupVo = VectorCalculation.calculate(lists);
				shopGroupVo.setAGroupAvg(getIndexAvg(shopGroupVo.getGroupA()));
				shopGroupVo.setBGroupAvg(getIndexAvg(shopGroupVo.getGroupB()));
				return shopGroupVo;
			}

			protected Map<String, Object> getIndexAvg(List<Map<String, Object>> list) {
				Map<String, Object> avgMap = Maps.newHashMap();
				for (String key : list.get(0).keySet()) {
					double x = 0;
					for (Map<String, Object> map : list) {
						if (NumberUtils.isNumber(map.get(key).toString())) {
							x += Double.valueOf(map.get(key).toString());
						}
					}
					avgMap.put(key, x);
				}
				return avgMap;
			}
		}.execute();
	}

	public List<Map<String, Object>> filterShop(ShopfilterReq req, List<String> allShopCode) {
		try {
			List<String> partShopCode;
			int count = allShopCode.size() % ONES_SHOP_NUM == 0 ? allShopCode.size() / ONES_SHOP_NUM : allShopCode.size() / ONES_SHOP_NUM + 1;
			List<Map<String, Object>> list = Lists.newArrayList();
			List<String>[] lists=new List[count];
			for (int i = 0; i < count; i++) {
				if (i == count - 1) {
					partShopCode = allShopCode.subList(allShopCode.size() * ONES_SHOP_NUM, allShopCode.size());
				}
				else {
					partShopCode = allShopCode.subList(allShopCode.size() * ONES_SHOP_NUM, allShopCode.size() * (ONES_SHOP_NUM + 1));
				}
				final List<String> listStr = partShopCode;
				lists[i]=listStr;
			}
			List<Map<String, Object>> finalList=new ArrayList<>();
			return  Stream.of(lists).map(i ->
					CompletableFuture.supplyAsync(() -> repeatFilterShop(req, i), executor))
					.collect(Collectors.toList())
					.stream()
					.map((i) -> {
						try {
							finalList.addAll(i.get());
							return i.get();
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
						catch (ExecutionException e) {
							e.printStackTrace();
						}
						return null;
					}).collect(ArrayList<Map<String, Object>>::new
							,ArrayList::addAll,
							ArrayList::addAll);
		}
		catch (Exception e) {
			//todo
			LOGGER.error("执行过滤逻辑失败!", e);
		}
		return null;
	}


	*/
/**
	 * 根据过滤条件循环查询、取并集
	 * @param req
	 * @param shopCodes
	 * @return
	 *//*

	public List<Map<String, Object>> repeatFilterShop(ShopfilterReq req, List<String> shopCodes) {
		//增加条件
		RelationBuilder relationBuilder = ExpressionBuilder.create().and();
		relationBuilder.in(IndexTagConst.shop_code, shopCodes.toArray());
		for (FilterIndicatorVo filterIndicatorVo : req.getFilterIndicatorVos()) {
			switch (filterIndicatorVo.getType()) {
				case lt:
					relationBuilder.cmp(filterIndicatorVo.getIndexCode(), filterIndicatorVo.getValue(), CompareEnum.lt);
					break;
				case ge:
					relationBuilder.cmp(filterIndicatorVo.getIndexCode(), filterIndicatorVo.getValue(), CompareEnum.ge);
					break;
				case le:
					relationBuilder.cmp(filterIndicatorVo.getIndexCode(), filterIndicatorVo.getValue(), CompareEnum.le);
					break;
				case gt:
					relationBuilder.cmp(filterIndicatorVo.getIndexCode(), filterIndicatorVo.getValue(), CompareEnum.gt);
					break;
				case eq:
					relationBuilder.eq(filterIndicatorVo.getIndexCode(), filterIndicatorVo.getValue());
					break;
				case in:
					relationBuilder.in(filterIndicatorVo.getIndexCode(), filterIndicatorVo.getValue().split(","));
					break;
				default:
					LOGGER.error("传入参数有误，找不到符号：{}", filterIndicatorVo.getType());
					break;
			}
		}

		Relation relation = relationBuilder.build();
		QueryParam param = QueryParam.builder().expression(relation).build();
		if (req.getGroupBys() != null && req.getGroupBys().size() != 0) {
			GroupBy groupBy = new GroupBy(req.getGroupBys().toArray(new String[req.getGroupBys().size()]));
			param.setGroupBy(groupBy);
		}
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Map<String, Object>> map = Maps.newHashMap();
		for (BackIndicatorVo backIndicatorVo : req.getBackIndicatorVos()) {
			IndexResult result = indexSearchRemote.search(IndexQuery.builder()
					.combinedCalculation(true)
					.indexTag(backIndicatorVo.getKey())
					.queryParam(param)
					.build());
			getIntersection(result, map);
		}
		list.addAll(map.values());
		return list;
	}

	public Map<String, Map<String, Object>> getIntersection(IndexResult result, Map<String, Map<String, Object>> map) {
		for (IndexData data : result.getData()) {
			Map<String, Object> map2;
			if (!map.containsKey(data.getShopCode())) {
				map2 = Maps.newHashMap();
				map2.put(IndexTagConst.abc, data.getAbc());
				map2.put(IndexTagConst.data_date, data.getDataDate());
				map2.put(IndexTagConst.classify_code, data.getClassifyCode());
				map2.put(IndexTagConst.shop_code, data.getShopCode());
			}
			else {
				map2 = map.get(data.getShopCode());
			}
			map2.put(result.getIndexCode(), data.getValue());
		}
		return map;
	}
}
*/
