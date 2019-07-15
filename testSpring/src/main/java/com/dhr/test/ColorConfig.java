package com.dhr.test;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;

public class ColorConfig {
    private Map<Integer, ColorConf> nomalConfMap;

    private Map<Integer, ColorConf> vipConfMap;

    private Map<Integer, ColorConf> nomalCanvasConfMap;

    private Map<Integer, ColorConf> vipCanvasConfMap;

    private Map<Integer, SpecialColorConf> specialColorConfMap;

    private int canvasLockLevel = 3;

    private String canvasLockDesc = "3级解锁画布颜色";


    public Map<Integer, ColorConf> getNomalConfMap() {
        return nomalConfMap;
    }

    public void setNomalConfMap(Map<Integer, ColorConf> nomalConfMap) {
        this.nomalConfMap = nomalConfMap;
    }

    public Map<Integer, ColorConf> getVipConfMap() {
        return vipConfMap;
    }

    public void setVipConfMap(Map<Integer, ColorConf> vipConfMap) {
        this.vipConfMap = vipConfMap;
    }

    public Map<Integer, ColorConf> getNomalCanvasConfMap() {
        return nomalCanvasConfMap;
    }

    public void setNomalCanvasConfMap(Map<Integer, ColorConf> nomalCanvasConfMap) {
        this.nomalCanvasConfMap = nomalCanvasConfMap;
    }

    public Map<Integer, ColorConf> getVipCanvasConfMap() {
        return vipCanvasConfMap;
    }

    public void setVipCanvasConfMap(Map<Integer, ColorConf> vipCanvasConfMap) {
        this.vipCanvasConfMap = vipCanvasConfMap;
    }

    public int getCanvasLockLevel() {
        return canvasLockLevel;
    }

    public void setCanvasLockLevel(int canvasLockLevel) {
        this.canvasLockLevel = canvasLockLevel;
    }

    public String getCanvasLockDesc() {
        return canvasLockDesc;
    }

    public void setCanvasLockDesc(String canvasLockDesc) {
        this.canvasLockDesc = canvasLockDesc;
    }

    public Map<Integer, SpecialColorConf> getSpecialColorConfMap() {
        return specialColorConfMap;
    }

    public void setSpecialColorConfMap(Map<Integer, SpecialColorConf> specialColorConfMap) {
        this.specialColorConfMap = specialColorConfMap;
    }

    public static class ColorConf {
        private String color;
        private int level;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }

    /**
     * Created by wenju on 17/9/12.
     */
    public static class MsResponse {
        private int v;
        private String callId;
        private int cd; // 0:成功
        private String desc = "";
        private long time;

        private Map<String, Object> data;

        public MsResponse() {
        }

        public MsResponse(int cd, String desc, Map<String, Object> data) {
            this.v =1;
            this.callId = UUID.randomUUID().toString();
            this.cd = cd;
            this.desc = desc;
            this.time = System.currentTimeMillis();
            this.data = data;
        }

        public MsResponse(int cd, Map<String, Object> data) {
            this(cd, null, data);
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public String getCallId() {
            return callId;
        }

        public void setCallId(String callId) {
            this.callId = callId;
        }

        public int getCd() {
            return cd;
        }

        public void setCd(int cd) {
            this.cd = cd;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "MsResponse{" +
                    "v=" + v +
                    ", callId='" + callId + '\'' +
                    ", cd=" + cd +
                    ", desc='" + desc + '\'' +
                    ", time=" + time +
                    ", data=" + data +
                    '}';
        }
    }


    public static class SpecialColorConf {
        /**
         * 颜色 暂时没用
         */
        private String color;
        /**
         * 最小等级
         */
        private int minLevel;
        /**
         * 画笔类型 1 普通画笔，2亲密关系 3，会员
         */
        private Integer paintType;
        /**
         * 展示图片
         */
        private String showImageUrl;
        /**
         * 是否可以使用 0不能 1能
         */
        private Integer canUse = 0;
        /**
         * 展示文字
         */
        private String name;

        /**
         * 道具Id
         */
        private List<String> propIds;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getMinLevel() {
            return minLevel;
        }

        public void setMinLevel(int minLevel) {
            this.minLevel = minLevel;
        }

        public Integer getPaintType() {
            return paintType;
        }

        public void setPaintType(Integer paintType) {
            this.paintType = paintType;
        }

        public String getShowImageUrl() {
            return showImageUrl;
        }

        public void setShowImageUrl(String showImageUrl) {
            this.showImageUrl = showImageUrl;
        }

        public Integer getCanUse() {
            return canUse;
        }

        public void setCanUse(Integer canUse) {
            this.canUse = canUse;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getPropIds() {
            return propIds;
        }

        public void setPropIds(List<String> propIds) {
            this.propIds = propIds;
        }
    }
}
