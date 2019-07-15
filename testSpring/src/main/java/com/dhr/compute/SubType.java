package com.dhr.compute;


import org.springframework.cglib.beans.BeanMap;

/**
 * @author duanhaoran
 * @since 2019/4/25 2:24 PM
 */
public enum SubType {

    //名人大乱斗合场
    melee2("melee", 1000, 2),
    //一起怼法官
    ball0("ball", 1001, 0),
    //一起怼法官cp赛
    ball1("ball", 1001, 1),
    //德州新手场
    texas1("texas", 1003, 1),
    //德州进阶场
    texas2("texas", 1003, 2),
    //德州精英场
    texas3("texas", 1003, 3),
    //狼人杀新手场
    wolf0("wolf", 1004, 0),
    //狼人杀欢乐场
    wolf1("wolf", 1004, 1),
    //狼人杀标准场
    wolf2("wolf", 1004, 2),
    //狼人杀高级场
    wolf3("wolf", 1004, 3),
    //狼人杀活动场
    wolf5("wolf", 1004, 5),
    //狼人杀试炼场
    wolf6("wolf", 1004, 6),
    //狼人杀匹配场
    wolf8("wolf", 1004, 8),
    //阿瓦隆新手场
    avalon0("avalon", 1100, 0),
    //阿瓦隆初级场
    avalon1("avalon", 1100, 1),
    //阿瓦隆进阶场
    avalon2("avalon", 1100, 2),
    //单卧底语音场
    spynew1("spynew", 1150, 1),
    //双卧底语音场
    spynew2("spynew", 1150, 2),
    //单卧底文字场
    spynew3("spynew", 1150, 3),
    //双卧底文字场
    spynew4("spynew", 1150, 4),
    //扫雷大作战2单人
    minenew0("minenew", 1160, 0),
    //扫雷大作战2v2组队
    minenew1("minenew", 1160, 1),
    //扫雷大作战2v2组队赛
    minenew2("minenew", 1160, 2),
    //血战新手场
    mahjong0("mahjong", 1161, 0),
    //血战进阶场
    mahjong1("mahjong", 1161, 1),
    //血战好友场
    mahjong2("mahjong", 1161, 2),
    //血战高级场
    mahjong3("mahjong", 1161, 3),
    //血战初级场
    mahjong4("mahjong", 1161, 4),
    //血流好友场
    mahjong10("mahjong", 1161, 10),
    //血流初级场
    mahjong11("mahjong", 1161, 11),
    //血流进阶场
    mahjong12("mahjong", 1161, 12),
    //血流高级场
    mahjong13("mahjong", 1161, 13),
    //大众初级场
    mahjong20("mahjong", 1161, 20),
    //大众进阶场
    mahjong21("mahjong", 1161, 21),
    //大众逗豆场
    mahjong22("mahjong", 1161, 22),
    //吹牛金币场
    bragnew0("bragnew", 1163, 0),
    //吹牛逗豆场
    bragnew1("bragnew", 1163, 1),
    //剧本杀
    wim1("wim", 1171, 1);

    private String gameName;

    private int gameType;

    private int subType;

    SubType(String gameName, int gameType, int subType) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.subType = subType;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public static SubType from(Integer gameType, Integer subType) {
        for (SubType type : SubType.values()) {
            if (type.getGameType() == gameType && type.getSubType() == subType) {
                return type;
            }
        }
        return null;
    }

    public static boolean checkSubTypeIsMatch(int subType) {
        if (SubType.wolf8.getSubType() == subType) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsMatch(Integer gameType, Integer subType){
        if (GameType.checkGameIsWolf(GameType.fromGameType(gameType)) && checkSubTypeIsMatch(subType)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        checkGameIsMatch(1004, 7);
    }

    public static Integer getMatchRoundSubType(Integer gameType) {
        if (GameType.checkGameIsWolf(GameType.fromGameType(gameType))) {
            return wolf8.getSubType();
        }
        return null;
    }
}
