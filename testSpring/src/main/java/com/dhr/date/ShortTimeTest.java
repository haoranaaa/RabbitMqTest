package com.dhr.date;

import com.google.common.collect.Lists;
import com.guman.common.json.JSON;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/5/7 8:23 PM
 */
public class ShortTimeTest {
    public static void main(String[] args) {

        Lists.partition(IntStream.range(0,10000).boxed().collect(Collectors.toList()),1000)
                .stream().map((list)-> CompletableFuture.supplyAsync(() ->
                 {
                     Integer integer = list.stream().reduce((a, b) -> a + b).get();
                     System.out.println(integer);
                     return integer;
                 })).collect(Collectors.toList());

    }
    public enum  OfficialGameTypeEnum {
        /**
         * 狼人杀
         */
        NONE(-1, "未知"),
        WOLF(1004, "狼人杀");

        private String name;

        private Integer gameType;

        OfficialGameTypeEnum(Integer gameType, String name) {
            this.gameType = gameType;
            this.name = name;
        }

        public static OfficialGameTypeEnum getOfficialGameTypeEnum(Integer gameType) {
            for (OfficialGameTypeEnum officialGameTypeEnum : OfficialGameTypeEnum.values()) {
                if (Objects.equals(officialGameTypeEnum.getGameType(), gameType)) {
                    return officialGameTypeEnum;
                }
            }
            return OfficialGameTypeEnum.NONE;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGameType() {
            return gameType;
        }

        public void setGameType(Integer gameType) {
            this.gameType = gameType;
        }

        @Override
        public String toString() {
            return "OfficialGameTypeEnum{" +
                    "name='" + name + '\'' +
                    ", gameType=" + gameType +
                    '}';
        }
    }

}
