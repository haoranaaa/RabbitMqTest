package com.dhr.designPattern;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * 场景：根据用户的手机壳变换app主题的颜色
 *
 * @author duanhaoran
 * @since 2019/12/1 10:07 PM
 */
public class DesignTest {

    private PhoneShellColorRemote remote =
            new UploadPhoneShellColor(
                    new SetterPhoneShellColor(
                            new HistoryPhoneShellColor(null)));
    private Map<String, AppConfig> appConfigMap;

    private Function<String, AppConfig> appConfigFun = (color) -> {
        if (appConfigMap == null) {
            appConfigMap = Maps.newConcurrentMap();
            AppConfig whiteColorAppConfig = new WhiteColorAppConfig();
            AppConfig blackColorAppConfig = new BlackColorAppConfig();
            whiteColorAppConfig.phoneColorList().forEach(i->appConfigMap.put(i, whiteColorAppConfig));
            blackColorAppConfig.phoneColorList().forEach(i->appConfigMap.put(i, blackColorAppConfig));
        }
        return appConfigMap.get(color);
    };


    public static void main(String[] args) {
        DesignTest designTest = new DesignTest();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String uid = scanner.next();
            String a = designTest.remote.getColor(uid);
            if (a == null) {
                continue;
            }
            AppConfig appConfig = designTest.appConfigFun.apply(a);
            String adapter = AppConfigAdapter.adapter(uid, appConfig);
            System.out.println(adapter);
        }

    }

    /**
     * app颜色适配器
     */
    static class AppConfigAdapter{

        public static String adapter(String uid, AppConfig appConfig) {
            if (isAndroid(uid)) {
                return toAndroid(appConfig);
            } else {
                return toIos(appConfig);
            }
        }
        private static boolean isAndroid(String uid) {
            return uid.length() > 100;
        }


        private static String toAndroid(AppConfig appConfig) {
            return appConfig.getConfig() + " channel:Android";
        }

        private static String toIos(AppConfig appConfig) {
            return appConfig.getConfig() + " channel:Ios";
        }
    }

    /**
     * app主题配置
     */
    interface AppConfig {

        String getConfig();

        List<String> phoneColorList();
    }

    abstract class AbstractAppConfig implements AppConfig {

        abstract String getColorConfig();

        @Override
        public String getConfig() {
            StringBuilder sb = new StringBuilder();
            sb.append("title:玩吧App ");
            sb.append("color:");
            sb.append(getColorConfig());
            sb.append(" since1998-2019");
            return sb.toString();
        }
    }

    /**
     * 白色主题颜色
     */
    class WhiteColorAppConfig extends AbstractAppConfig {

        @Override
        String getColorConfig() {
            return "white";
        }

        @Override
        public List<String> phoneColorList() {
            return Lists.newArrayList("black", "red", "yellow");
        }
    }

    /**
     * 黑色主题颜色
     */
    class BlackColorAppConfig extends AbstractAppConfig {

        @Override
        String getColorConfig() {
            return "black";
        }

        @Override
        public List<String> phoneColorList() {
            return Lists.newArrayList("blue", "pink", "purple");
        }
    }

    /**
     * 获取手机壳颜色接口
     */
    interface PhoneShellColorRemote {

        String getColor(String uid);

    }

    /**
     * 抽象接口
     */
    abstract class AbstractPhoneShellColorRemote implements PhoneShellColorRemote {

        private PhoneShellColorRemote next;

        public AbstractPhoneShellColorRemote(PhoneShellColorRemote phoneShellColorRemote) {
            this.next = phoneShellColorRemote;
        }

        @Override
        public String getColor(String uid) {
            if (next == null) {
                return getColor(uid);
            }
            if (getColor(uid) == null) {
                return next.getColor(uid);
            }
            return null;
        }

        abstract String color(String uid);
    }


    /**
     * 用户历史手机壳颜色
     */
    class HistoryPhoneShellColor extends AbstractPhoneShellColorRemote {

        public HistoryPhoneShellColor(PhoneShellColorRemote phoneShellColorRemote) {
            super(phoneShellColorRemote);
        }

        @Override
        String color(String uid) {
            return null;
        }
    }

    /**
     * 用户设置的手机壳颜色
     */
    class SetterPhoneShellColor extends AbstractPhoneShellColorRemote {

        public SetterPhoneShellColor(PhoneShellColorRemote phoneShellColorRemote) {
            super(phoneShellColorRemote);
        }

        @Override
        String color(String uid) {
            return null;
        }
    }

    /**
     * 根据用户的手机壳自动上传的颜色
     */
    class UploadPhoneShellColor extends AbstractPhoneShellColorRemote {


        public UploadPhoneShellColor(PhoneShellColorRemote phoneShellColorRemote) {
            super(phoneShellColorRemote);
        }

        @Override
        String color(String uid) {
            return null;
        }
    }

    interface Observer{

        void update();
    }

    class PhoneShellHistoryObserver implements Observer{

        @Override
        public void update() {

        }
    }
}