package com.dhr.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
public class Team {
    //基本属性
    private long id;
    private String topic;
    private int gameType;
    private int subType;
    private Status status;
    private long matchId;
    private String master;
    private Set<String> npcUids;
    private int chatType;
    private long chId;
    private String title;
    private String abs;
    //队伍属性
    private int source;
    private int pubInviteMsg;
    private int voiceType;
    private List<Camp> camps;
    private int viewerCount;
    private Set<String> viewers;
    private int viceType;
    private Map<String, Integer> uidViceMap = new HashMap<>();
    private long readyLimitTime;
    private long masterStartLimitTime;
    private long beMasterTimestamp;//成为房管的时间戳
    private long lowerPersonTimestamp;//低于需要到期解散的人数时间戳

    private Invite invite; //邀请信息

    //房间属性  可以用来判断当前队伍是否已经是房间队伍。
    private Room room; //游戏房间

    //匹配属性
    private Match match;

    private String groupId;
    private int inGroup;//是否在群中 1.是,0.否(进游戏后)

    private long createTime;
    private long updateTime;

    private List<AccompanyInvite> accompanyInviteList; //uid

    private Map<String, Map<String, Long>> accompanyMap;
    private Map<String, String> accompanySourceMap; //playerUid#viewerUid=>source

    private WimInfo wimInfo;

    public Team(String topic, long teamId, int gameType, int subType, Status status) {
        this.id = teamId;
//        this.status = status;
        this.gameType = gameType;
        this.subType = subType;
        this.topic = topic;
        this.createTime = System.currentTimeMillis();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Invite {
        private String msgId;
        private long inviteTimeout;


        /**私聊自动开局添加*/

        //   		1 自动开局， 没有配置或者不等于1相当于一般游戏
        private int autoStart;

        //       游戏结束后操作   0  房间进入普通匹配池    1  房间销毁
        private int gameOverHandle;

        //        组队超时时间（秒）
        private int organizeTeamTimeOut;

        //     开始匹配后，过 canCancelTime 时间可以取消匹配
        private int canCancelTime;

        public Invite(String msgId, long inviteTimeout) {
            this.msgId = msgId;
            this.inviteTimeout = inviteTimeout;
        }
    }

    @Data
    public static class VipRoom {
        private String owner;
        private boolean passwordEnable;
        private String password;
        private int vipLevel;
    }


    @Data
    public static class Camp {
        private int id;
        private List<Position> positions;
    }

    @Data
    public static class Position implements Comparable<Position> {
        private int sid;
        private int status = 1; //位置状态 1开启 0关闭
        private int ban; //是否禁言 1禁言, 0未禁言
        private User user;

//        private String positionId;
//        private String uid;
//        private Team.UserState userState;
//
//        private String campId;
//        private boolean prepared;
//        private int viceMoney;
//        private int chip;
//        private int limitTime;
//        private Set<String> accompany;


        public Position() {
        }

        public Position(int sid) {
            this.sid = sid;
        }

        @Override
        public int compareTo(Position o) {
            return sid - o.getSid();
        }
    }

    @Data
    public static class User {
        //用户基本信息
        private String uid;
        private String name;
        private String avatarUrl;  //头像
        private String iconImgLarge; //大头像
        private int sex;        //性别 1:男 0:女
        private int vipLevel;
        private int level;
        private int money;
        private int chip;
        private String location;

        private int gameLevel;    //游戏等级
        private int gameLevelScore;    //游戏等级积分

        private int rank;
        //队伍用户信息
        private UserState userState;
        private int prepare;
        private long limitTime;
        /**
         * 进入房间时间 timestamp
         */
        private long enterTime;
        private int micState;       //麦克风状态
        private int speakerState;//扬声器状态
        private int gameSoundState;////游戏音效状态

        private double longitude; //lbs 经度
        private double latitude;  //lbs 纬度

        private long escapeTimeOut;    //能连上的时间

        public User() {
        }

        public User(String uid, long limitTime) {
            this.uid = uid;
            this.limitTime = limitTime;
        }

        public User(String uid, int rank, int sex, int micState) {
            this.uid = uid;
            this.rank = rank;
            this.sex = sex;
            this.micState = micState;
        }
    }

    public enum Status {
        WAIT(1), //等待

        READY(2), //准备好了

        MATCH(3), //匹配池中匹配

        CONFIRM(4), //等待客户端确认

        PLAYING(5); //游戏开局

        private int value;

        private Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static Status from(int value) {
            for (Status status : Status.values()) {
                if (status.getValue() == value) {
                    return status;
                }
            }
            throw new IllegalArgumentException("no Status from value: " + value);
        }
    }

    public enum UserState {
        /***
         * 离线
         */
        OFFLINE(1, "offline"),

        /***
         * 主动离线
         */
        OFFLINE_QUIT_SELF(2, "quit_self"),

        /***
         * 断网离线
         */
        OFFLINE_QUIT_DISCONNECT(3, "quit_disconnect"),

        /***
         * 被踢
         */
        OFFLINE_BY_KICK(4, "kicked"),

        /***
         * 游戏结束后离线
         */
        OFFLINE_QUIT_OUT_GAME(5, "quit_out_game"),

        /***
         * 在线
         */
        ONLINE(0, "online");

        private int state;

        private String value;

        UserState(int state, String value) {
            this.value = value;
            this.state = state;
        }

        public String getValue() {
            return this.value;
        }

        public int getState() {
            return state;
        }

        public static UserState fromValue(String value) {
            for (UserState userState : UserState.values()) {
                if (userState.getValue().equals(value)) {
                    return userState;
                }
            }
            throw new IllegalArgumentException("no UserState from value: " + value);
        }
    }

    @Data
    public static class Room {
        private Map<Integer, Integer> roleConf;
        private int isPrivate;
        private Map<String, Integer> lastCampIdMap;
        private int callUp; //召唤卡标志
        private int comeOn; //快进卡标志
        private VipRoom vipRoom; //游戏包间
        private int isTempRoom; //是否是临时房间
        private int roomLock;  //是否房间锁定




//        private int status;
//        private int level;
//        private String title;
////        private String roomId;
//        private String matchId;
//        private String owner;
//        private String master;
//        private List<Position> positions;
//        private Set<String> observers;
//        private Map<String,RoomUser> observerMap;
//        private Map<String, RoomUser> userMap;
//        private int maxCloseSitNum;
//        private String lastRoomId;
//        private int combineEnable;
//        private int combineMinNum;
//        private int combineMaxNum;
//        private int changeRoomEnable;
//
//        private int callUpEnable;           //是否开放使用加时卡
//        private int callUpMinNum;
//        private int callUpMaxNum;
//        private Map<String, Map<String, Long>> accompanyMap;
    }

    @Data
    public static class Match {
        private int matchCount; //参与匹配失败的次数
        private int ladderFlag;       //是否开启天梯
        private long lastStartMatchTime;
        private int matchTime;
        private long confirmLimitTime;
        private Set<Long> childTeamIds;
    }

    @Data
    public static class WimInfo {
        private int statue;
        private String gameId;
        private long createTime;
    }
}