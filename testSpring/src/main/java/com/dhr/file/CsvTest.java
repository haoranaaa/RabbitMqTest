package com.dhr.file;

import com.dhr.file.model.ClubOperateLogModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.guman.common.json.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author duanhaoran
 * @since 2020/1/7 11:38 AM
 */
public class CsvTest {

    private static Map<String, Set<String>> map = Maps.newHashMap();

    private static Map<String, Map<String,String>> nameMap = Maps.newHashMap();

    private static int k = 0;

    public static void main(String[] args) {
        File file = new File("/Users/duanhaoran/Desktop/app_club_operate_log.csv");
        try(InputStreamReader is = new InputStreamReader(new FileInputStream(file))) {
            BufferedReader br = new BufferedReader(is);
            String line = br.readLine();
            List<ClubOperateLogModel> list  = Lists.newArrayList();
            int i = 0;
            while (true) {
                String a = line;
                line = br.readLine();
                ClubOperateLogModel models = getModels(line);
                if (models != null) {
                    list.add(models);
                }
                if (i++ > 20000) {
                    break;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Map<String, List<ClubOperateLogModel>> collect = list.stream().sorted(Comparator.comparing(ClubOperateLogModel::getCreateTime))
                    .collect(Collectors.groupingBy(log -> sdf.format(log.getCreateTime())));
            List<Map.Entry<String, List<ClubOperateLogModel>>> collect1 = collect.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).collect(Collectors.toList());
            for (Map.Entry<String, List<ClubOperateLogModel>> k : collect1) {
                System.out.println("开始计算" + k.getKey());
                resolveData(k.getValue(), k.getKey());
            }
            System.out.println(k);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void resolveData(List<ClubOperateLogModel> list1, String date) {
        Map<String, List<ClubOperateLogModel>> collect = list1.stream().collect(Collectors.groupingBy(ClubOperateLogModel::getClubId));
        collect.forEach((clubId, list)->{
            list.stream().sorted(Comparator.comparing(ClubOperateLogModel::getCreateTime)).forEach(i->{
                if (i.getState() == 0) {
                    nameMap.putIfAbsent(clubId, Maps.newHashMap());
                    nameMap.get(clubId).put(i.getDesc(), i.getUid());
                    map.computeIfAbsent(clubId, (v) -> Sets.newHashSet()).add(i.getUid());
                } else if (i.getState() == -1){
                    Set<String> set = map.get(clubId);
                    if (CollectionUtils.isNotEmpty(set)) {
                        if (!set.remove(i.getUid())) {
                            System.out.println(i.getUid() + ":" + i.getUid() + "未查询到");
                        }
                    }
                } else {
                    Set<String> set = map.get(clubId);
                    String o = nameMap.get(clubId).get(i.getDesc());
                    if (!set.remove(o)) {
                        k++;
                        System.out.println("踢人" + i.getUid() + ":" + o + "未查询到");
                    }
                }
            });
        });
        File file = new File("/Users/duanhaoran/Desktop/data/" + date + ".csv");
        try {
            if (file.exists()) {
                file = File.createTempFile(date, "csv", new File("/Users/duanhaoran/Desktop/data"));
            }
            if (MapUtils.isNotEmpty(map) && date.equals("2019-12-09")) {
                System.out.println(JSON.writeValueAsString(map));
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            map.forEach((k,v)->{
                v.forEach(i->{
                    try {
                        bufferedWriter.newLine();
                        bufferedWriter.write(k+","+i);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            });
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ClubOperateLogModel getModels(String line) {
        if (org.apache.commons.lang3.StringUtils.isBlank(line) || line.contains("设置为") || line.contains("编辑公告") || line.contains("加入方式")
                || line.contains("将圈子加入方式修改为") || line.contains("编辑简介") || line.contains("圈子名称修改为") || line.contains("修改了圈子头像")) {
            return null;
        }
        String s = "审核通过,加入圈子";
        int state = 0;
        if (line.contains(s)) {
            line = line.replace(line.substring(line.lastIndexOf("被"), line.lastIndexOf("圈子")) + "圈子", "");
        }

        if (line.contains("加入圈子")) {
            line = line.replace("加入圈子", "");
        }
        if (line.contains("离开圈子")) {
            state = -1;
            line = line.replace("离开圈子", "");
        }
        if (line.contains("踢出圈子")) {
            state = -2;
            try {
                String target = new String(line).substring(line.lastIndexOf("被"), line.lastIndexOf("圈子")) + "圈子";
                line = line.replace(target, "");
            } catch (StringIndexOutOfBoundsException st) {
                System.out.println(line);
            }
        }
        String[] split = line.split(",");
        if (split.length != 6) {
            return null;
        }
        ClubOperateLogModel clubOperateLogModel = new ClubOperateLogModel(split);
        clubOperateLogModel.setState(state);
        return clubOperateLogModel;

    } 

//            return true;
//        }).collect(Collectors.toList());
//        System.out.println(JSON.writeValueAsString(collect));
}
