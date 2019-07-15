package com.dhr.json;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

/**
 * @author duanhaoran
 * @since 2019/5/17 7:05 PM
 */
public class MsResponse {
        private int v;
        private String callId;
        private int cd; // 0:成功
        private String desc = "";
        private long time;

        private Map<String, Object> data;

        public MsResponse() {
        }

        public MsResponse(int cd, String desc, Map<String, Object> data) {
            this.v = 1;
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
    @Data
    class WolfPersonalRecord implements Serializable {
        private long id;
        private String uid;
        private String roomId;
        private String matchId;
        private int role;
        private int win;
        private int playerCount;
        private int roomLevel;
        private String roleConfig;
        private int escape;
        private String createTime;
        private String campId;

    }
