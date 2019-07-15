package com.dhr.compute;

/**
 * @user xulei
 * @date 16/6/29 下午4:35
 * @desc
 */
public enum GameType {

    RANDOM(0, "random"),

    SPY(1, "spy"),

    DRAW(2, "draw"),

    BRAG(3, "brag"),

    BRAG_PRACTICE(4, "bragPractice"),

    DIXIT(5, "dixit"),

    GUESS(6, "guess"),

    MINE(7, "mine"),

    GUESS_VOICE(8, "guessVoice"),

    GUESS_NEW(100, "guessnew"),

    DRAW_NEW(101, "drawnew"),

    STORYCHAIN(102, "storychain"),
    //同一类型情况下使用相同的gameTypeId 需要注意Cluster.getClusterByGameType(GameType.from(gameType))  Cluster cluster = Cluster.from(gameType, clusterName); 导致的cluster不对。主要发生在微服务中clusterName没传的情况。一半不会有
    //如果不同类型使用这个方法时，GameUtil需要修改。
    STORYCHAIN1(102, "storychain1"),

    STORYCHAINV2(102, "storychainv2"),

    //MINA_DRAW(1300, "minadraw"),

    MELEE(1000, "melee"),

    BALL(1001, "ball"),

    PACMAN(1002, "pacman"),

    //德州初级场
    TEXAS(1003, "texas"),
    //德州标准场
    TEXAS1(10031, "texas1"),
    //德州高级场
    TEXAS2(10032, "texas2"),

    WOLF(1004, "wolf"),

    WOLF1(1005, "wolf1"),

    WOLF2(1006, "wolf2"),

    WOLF3(1007, "wolf3"),

    WOLF4(1008, "wolf4"),

    WOLF5(1009, "wolf5"),

    WOLF6(1010, "wolf6"),

    WOLF7(1011, "wolf7"),

    WOLF8(1012, "wolf8"),

    WOLF9(1013, "wolf9"),

    WOLF10(1014, "wolf10"),

    WOLF11(1015, "wolf11"),

    WOLF12(1016, "wolf12"),

    WOLF13(1017, "wolf13"),

    WOLF14(1018, "wolf14"),

    WOLF15(1019, "wolf15"),

    WOLF16(1020, "wolf16"),

    WOLF17(1021, "wolf17"),

    WOLF18(1022, "wolf18"),

    WOLF19(1023, "wolf19"),

    WOLF20(1024, "wolf20"),

    WOLF21(1025, "wolf21"),
    WOLF22(1026, "wolf22"),
    WOLF23(1027, "wolf23"),
    WOLF24(1028, "wolf24"),
    WOLF25(1029, "wolf25"),
    WOLF26(1030, "wolf26"),
    WOLF27(1031, "wolf27"),
    WOLF28(1032, "wolf28"),
    WOLF29(1033, "wolf29"),
    WOLF30(1034, "wolf30"),
    WOLF31(1035, "wolf31"),
    WOLF32(1036, "wolf32"),
    WOLF33(1037, "wolf33"),
    WOLF34(1038, "wolf34"),
    WOLF35(1039, "wolf35"),
    WOLF36(1040, "wolf36"),
    WOLF37(1041, "wolf37"),
    WOLF38(1042, "wolf38"),
    WOLF39(1043, "wolf39"),
    WOLF40(1044, "wolf40"),
    WOLF41(1045, "wolf41"),
    WOLF42(1046, "wolf42"),
    WOLF43(1047, "wolf43"),
    WOLF44(1048, "wolf44"),
    WOLF45(1049, "wolf45"),
    WOLF46(1050, "wolf46"),

    //初级场
    AVALON(1100, "avalon"),
    //五人场
    AVALON1(1101, "avalon1"),
    // 新场次
    AVALON2(1100, "avalon2"),

    SPY_NEW(1150, "spynew"),
    SPY_NEW1(1151, "spynew1"),
    SPY_NEW2(1152, "spynew2"),
    SPY_NEW3(1153, "spynew3"),

    MINE_NEW(1160, "minenew"),
    MAHJONG(1161, "mahjong"),

    BRAG_NEW(1163, "bragnew"),

    WIM(1170, "wim"),
    WIMX(1171,"wim"),

    MINAWIM(1306, "minawim"),

    BOW(1200, "bow"),
    CLIMB(1201, "climb"),
    LINK(1202, "link"),
    ROAR(1203, "roar"),
    GOMOKU(1206, "gomoku"), //1204、1205 被统计那边占用了
    ANIMALCHESS(1207, "animalchess"),
    COOKY(1208, "cooky"),
    CLASSFIGHT(1209, "classfight"),
    FIND(1210, "find"),
    HALMA(1211, "halma"),
    BLOCK(1212, "block"),
    QUIZ(1214, "quiz"),
    FIND2V2(1213, "find2v2"),
    TESTLADDER(1297, "testladder"),
    TEST_2V2(1298, "test_2v2"),
    MINADRAW(1300, "minadraw"),
    MINAANIMALCHESS(1301, "minaanimalchess"),
    QUIZ1V1(1215, "quiz1v1"),
    REVERSI(1216, "reversi"),
    NEEDLE(1217, "needle"),
    COOKYLADDER(1218, "cookyladder"),
    CHINESECHESS(1219, "chinesechess"),
    STAR(1220, "star"),
    LINKLADDER(1221, "linkladder"),
    QUIZ1V1LADDER(1222, "quiz1v1ladder"),
    FINDLADDER(1223, "findladder"),
    JUMP(1224, "jump"),
    NURTURE(1400, "nurture"),
    VOICE_ROOM(2000, "voiceRoom"),
    BILLIARD(1225, "billiard"),
    BILLIARD2V2(1226, "billiard"),
    FISH(1600, "fish"),
    ;

    private int value;

    private String name;

    private GameType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 只能根据value来获取
     *
     * @param value
     * @return
     */
    public static GameType from(int value) {
        for (GameType gameType : GameType.values()) {
            if (gameType.getValue() == value) {
                return gameType;
            }
        }
        throw new IllegalArgumentException("no gameType from value:" + value);
    }

    public static GameType fromName(String name) {
        for (GameType gameType : GameType.values()) {
            if (gameType.getName().equals(name)) {
                return gameType;
            }
        }
        throw new IllegalArgumentException("no gameType from value:" + name);
    }

    public static boolean checkGameIsWolf(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() >= 1004 && gameType.getValue() <= 1050) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsBall(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() >= 1001 && gameType.getValue() <= 1002) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsTexas(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType == GameType.TEXAS || gameType == GameType.TEXAS1) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsAvalon(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == AVALON.getValue() || gameType.getValue() == AVALON1.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsSpynew(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() >= 1150 && gameType.getValue() <= 1153) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsGuessNew(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == GUESS_NEW.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsDrawNew(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == DRAW_NEW.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsMinenew(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == MINE_NEW.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsMinaDraw(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == MINADRAW.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsBow(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == BOW.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsClimb(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == CLIMB.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsLink(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == LINK.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsRoar(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == ROAR.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsGomoku(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == GOMOKU.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsStorychain(GameType gameType) {
        if (gameType == null) {
            return false;
        }
        if (gameType.getValue() == STORYCHAIN.getValue()) {
            return true;
        }
        return false;
    }

    public static boolean checkGameIsMahjong(GameType gameType){
        if(gameType == null){
            return false;
        }
        if(gameType.getValue() == MAHJONG.getValue()){
            return true;
        }
        return false;
    }

    public static GameType getRealGameType(GameType gameType) {
        if (checkGameIsWolf(gameType)) {
            return GameType.WOLF;
        }
        if (checkGameIsAvalon(gameType)) {
            return GameType.AVALON;
        }
        if (checkGameIsTexas(gameType)) {
            return GameType.TEXAS;
        }
        if (checkGameIsBall(gameType)) {
            return GameType.BALL;
        }
        if (checkGameIsSpynew(gameType)) {
            return GameType.SPY_NEW;
        }
        if (checkGameIsDrawNew(gameType)) {
            return GameType.DRAW_NEW;
        }
        if (checkGameIsStorychain(gameType)) {
            return GameType.STORYCHAIN;
        }
        if (checkGameIsMinaDraw(gameType)) {
            return GameType.MINADRAW;
        }
        return gameType;
    }

    public static boolean checkGameIsVoiceRoom(int gameType) {
        return GameType.VOICE_ROOM.getValue() == gameType;
    }

    public static GameType fromGameType(int gameType) {
        for (GameType type : GameType.values()) {
            if (type.getValue()== gameType) {
                return type;
            }
        }
        return null;
    }

    /**
     * 判断是否是天梯
     * @param gameType
     * @return
     */
    public static boolean isLadder(int gameType) {
        GameType type = fromGameType(gameType);
        if (type == null)
            return false;
        switch (type) {
            case TESTLADDER:
            case COOKYLADDER:
            case LINKLADDER:
            case QUIZ1V1LADDER:
            case FINDLADDER:
                return true;
        }
        return false;
    }

}
