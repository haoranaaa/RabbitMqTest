package com.dhr.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.common.collect.Lists;
import com.guman.common.json.JSON;
import lombok.Data;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.config.MapFactoryBean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/4/26 2:57 PM
 */
public class JsonTest {

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        A a = JSON.readValue("{\"a\":\"a\",\"others\":\"aaa\"}", A.class);
        System.out.println(a);
        int sum = IntStream.range(0, 9).boxed().mapToInt(i->(int) i).sum();
        AtomicBoolean atomicBoolean = new AtomicBoolean(Boolean.FALSE);
        for (int i = 0; i < 10; i++) {
            System.out.println(atomicBoolean.compareAndSet(Boolean.FALSE, Boolean.TRUE));
        }

        String x="{\"v\":1,\"callId\":\"ce604f02-2bb0-4670-934e-9c038a3b8d68\",\"cd\":0,\"desc\":null,\"time\":1558084975680,\"data\":{\"personalRecords\":[{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1139317\",\"matchId\":\"262832134\",\"role\":6,\"win\":0,\"playerCount\":10,\"roomLevel\":6,\"roleConfig\":\"{\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1,\\\"13\\\":1,\\\"2\\\":2,\\\"3\\\":4}\",\"escape\":0,\"createTime\":\"2019-05-07 12:22:55.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1139317\",\"matchId\":\"262537225\",\"role\":3,\"win\":0,\"playerCount\":10,\"roomLevel\":6,\"roleConfig\":\"{\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1,\\\"13\\\":1,\\\"2\\\":2,\\\"3\\\":4}\",\"escape\":0,\"createTime\":\"2019-05-07 12:14:28.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1139317\",\"matchId\":\"262799364\",\"role\":3,\"win\":0,\"playerCount\":11,\"roomLevel\":6,\"roleConfig\":\"{\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1,\\\"13\\\":1,\\\"12\\\":1,\\\"2\\\":2,\\\"3\\\":4}\",\"escape\":0,\"createTime\":\"2019-05-07 12:02:53.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1265848\",\"matchId\":\"262569985\",\"role\":3,\"win\":1,\"playerCount\":9,\"roomLevel\":3,\"roleConfig\":\"{\\\"17\\\":1,\\\"4\\\":1,\\\"6\\\":1,\\\"15\\\":1,\\\"2\\\":2,\\\"3\\\":3}\",\"escape\":0,\"createTime\":\"2019-05-06 20:54:29.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1103043\",\"matchId\":\"247862556\",\"role\":3,\"win\":0,\"playerCount\":9,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":3,\\\"3\\\":3,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-04-18 15:27:46.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1103043\",\"matchId\":\"247862501\",\"role\":2,\"win\":1,\"playerCount\":8,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":3,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-04-18 14:54:59.0\",\"campId\":\"1\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1103043\",\"matchId\":\"247862464\",\"role\":6,\"win\":1,\"playerCount\":10,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":3,\\\"3\\\":4,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-04-18 14:41:41.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1300328\",\"matchId\":\"206407476\",\"role\":2,\"win\":0,\"playerCount\":12,\"roomLevel\":3,\"roleConfig\":\"{\\\"17\\\":1,\\\"4\\\":1,\\\"5\\\":1,\\\"11\\\":1,\\\"15\\\":1,\\\"2\\\":3,\\\"3\\\":4}\",\"escape\":0,\"createTime\":\"2019-03-06 16:28:41.0\",\"campId\":\"1\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1300328\",\"matchId\":\"206405649\",\"role\":2,\"win\":0,\"playerCount\":9,\"roomLevel\":3,\"roleConfig\":\"{\\\"17\\\":1,\\\"5\\\":1,\\\"6\\\":1,\\\"15\\\":1,\\\"2\\\":2,\\\"3\\\":3}\",\"escape\":0,\"createTime\":\"2019-03-04 15:51:41.0\",\"campId\":\"1\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1014033\",\"matchId\":\"199917614\",\"role\":3,\"win\":1,\"playerCount\":8,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":3,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-02-22 15:56:08.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1277894\",\"matchId\":\"178990529\",\"role\":2,\"win\":1,\"playerCount\":10,\"roomLevel\":2,\"roleConfig\":\"{\\\"5\\\":1,\\\"6\\\":1,\\\"7\\\":1,\\\"2\\\":3,\\\"3\\\":4}\",\"escape\":0,\"createTime\":\"2019-02-12 15:49:25.0\",\"campId\":\"2\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1014033\",\"matchId\":\"171966618\",\"role\":4,\"win\":1,\"playerCount\":8,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":3,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-01-24 15:46:03.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1014033\",\"matchId\":\"155746618\",\"role\":3,\"win\":1,\"playerCount\":8,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":3,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-01-07 16:21:10.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1014033\",\"matchId\":\"155746545\",\"role\":3,\"win\":1,\"playerCount\":8,\"roomLevel\":1,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":3,\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1}\",\"escape\":0,\"createTime\":\"2019-01-07 16:08:10.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1014033\",\"matchId\":\"152709205\",\"role\":6,\"win\":0,\"playerCount\":12,\"roomLevel\":2,\"roleConfig\":\"{\\\"4\\\":1,\\\"5\\\":1,\\\"6\\\":1,\\\"7\\\":1,\\\"15\\\":1,\\\"2\\\":3,\\\"3\\\":4}\",\"escape\":0,\"createTime\":\"2018-12-29 11:54:21.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1089690\",\"matchId\":\"152378290\",\"role\":4,\"win\":0,\"playerCount\":6,\"roomLevel\":0,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":2,\\\"4\\\":1,\\\"9\\\":1}\",\"escape\":0,\"createTime\":\"2018-12-20 08:16:33.0\",\"campId\":\"0\"},{\"id\":0,\"uid\":\"59686815\",\"roomId\":\"1096018\",\"matchId\":\"15350195715251096018647\",\"role\":3,\"win\":0,\"playerCount\":6,\"roomLevel\":0,\"roleConfig\":\"{\\\"2\\\":2,\\\"3\\\":2,\\\"4\\\":1,\\\"9\\\":1}\",\"escape\":0,\"createTime\":\"2018-08-23 18:22:47.0\",\"campId\":\"0\"}]}}";
        ObjectMapper mapper = new ObjectMapper();
        MsResponse msResponse = mapper.readValue(x, MsResponse.class);
        String personalRecords1 = mapper.writeValueAsString(msResponse.getData().get("personalRecords"));
        System.out.println(personalRecords1);
        List<WolfPersonalRecord> lists = mapper.readValue(personalRecords1, new TypeReference<List<WolfPersonalRecord>>() {
        });
        for (WolfPersonalRecord re : lists) {
            System.out.println(re.getUid());
        }
        System.out.println(lists);
    }
}

@Data
class A {
    private String a;
}
