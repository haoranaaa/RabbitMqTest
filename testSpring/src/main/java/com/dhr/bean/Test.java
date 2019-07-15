package com.dhr.bean;

import com.guman.common.json.JSON;
import org.joda.time.LocalTime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author duanhaoran
 * @since 2019/5/28 2:14 PM
 */
public class Test {
    public static void main(String[] args) {
        String t="{'id':1190729,'topic':'wolfv3','gameType':1004,'subType':1,'status':'PLAYING','matchId':275741180,'master':'38421703','chatType':0,'chId':1288615498,'title':'xe6xacxa2xe4xb9x90xe5x9cxba','source':0,'pubInviteMsg':0,'voiceType':1,'camps':[{'id':0,'positions':[{'sid':0,'status':1,'ban':0,'user':{'uid':'38421703','name':'Z.z','avatarUrl':'https://qiniustatic.wodidashi.com/head/GxHTR0qv8KQmM?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/head/GxHTR0qv8KQmM','sex':0,'vipLevel':0,'level':25,'money':3802,'chip':900,'location':'','gameLevel':18,'gameLevelScore':561,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':1,'status':1,'ban':0,'user':{'uid':'40787669','name':'xe4xb8x8dxe5x92xb8','avatarUrl':'https://qiniustatic.wodidashi.com/picture/jZzRCdzy5fto9?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/picture/jZzRCdzy5fto9','sex':1,'vipLevel':0,'level':27,'money':1226,'chip':700,'location':'','gameLevel':16,'gameLevelScore':485,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':2,'status':1,'ban':0,'user':{'uid':'98761421','name':'xe5xa4xa9xe5xa4xa9','avatarUrl':'http://qiniu.wodidashi.com/picture/52GPGfqGvO0vo?imageView/2/w/200/h/200','iconImgLarge':'http://qiniu.wodidashi.com/picture/52GPGfqGvO0vo','sex':1,'vipLevel':0,'level':22,'money':5770,'chip':300,'location':'','gameLevel':17,'gameLevelScore':87,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':3,'status':1,'ban':0,'user':{'uid':'46553443','name':'Bat','avatarUrl':'https://qiniustatic.wodidashi.com/picture/DuVn5KqQjHERD?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/picture/DuVn5KqQjHERD','sex':1,'vipLevel':0,'level':21,'money':19870,'chip':0,'location':'xe9xbbx91xe9xbex99xe6xb1x9fxe7x9cx81xe5x93x88xe5xb0x94xe6xbbxa8xe5xb8x82','gameLevel':16,'gameLevelScore':345,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':4,'status':1,'ban':0,'user':{'uid':'42467209','name':'xe7x88x86xe7x88x86','avatarUrl':'https://qiniustatic.wodidashi.com/head/jSHD26WODzDvn?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/head/jSHD26WODzDvn','sex':0,'vipLevel':0,'level':15,'money':28405,'chip':0,'location':'xe6xb9x96xe5x8dx97xe7x9cx81xe8xa1xa1xe9x98xb3xe5xb8x82','gameLevel':14,'gameLevelScore':74,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':5,'status':1,'ban':0,'user':{'uid':'82790772','name':'Sky','avatarUrl':'https://qiniustatic.wodidashi.com/head/gjIGIVQDHi00U?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/head/gjIGIVQDHi00U','sex':1,'vipLevel':0,'level':17,'money':28542,'chip':11840,'location':'','gameLevel':17,'gameLevelScore':823,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':6,'status':1,'ban':0,'user':{'uid':'21919805','name':'xe5xa7x8bxe4xbax8exe5x88x9dxe8xa7x81','avatarUrl':'https://qiniustatic.wodidashi.com/FkzSXlrOrp97g4Sk5R0_TCOjJ6oQ?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/FkzSXlrOrp97g4Sk5R0_TCOjJ6oQ','sex':0,'vipLevel':0,'level':35,'money':458,'chip':15908,'location':'xe6xb5x99xe6xb1x9fxe5x8fxb0xe5xb7x9e','gameLevel':19,'gameLevelScore':221,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':7,'status':1,'ban':0,'user':{'uid':'79226377','name':'F      C','avatarUrl':'https://qiniustatic.wodidashi.com/picture/N2IQQLCq4HFeg?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/picture/N2IQQLCq4HFeg','sex':1,'vipLevel':0,'level':15,'money':20157,'chip':0,'location':'xe6xb1x9fxe8xa5xbfxe5x8dx97xe6x98x8c','gameLevel':7,'gameLevelScore':30,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':8,'status':1,'ban':0,'user':{'uid':'19710522','name':'xe7xa9xbaxe6xb0x94','avatarUrl':'https://qiniustatic.wodidashi.com/picture/0jPJF3fESK4xg?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/picture/0jPJF3fESK4xg','sex':1,'vipLevel':0,'level':22,'money':1409,'chip':825,'location':'','gameLevel':15,'gameLevelScore':474,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':9,'status':1,'ban':0,'user':{'uid':'26440558','name':'xeaxa7x81xe5x90x9bxefxbcx8cxe4xb8xbbxeaxa7x82','avatarUrl':'https://qiniustatic.wodidashi.com/picture/IapsYsdmrLkoz?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/picture/IapsYsdmrLkoz','sex':1,'vipLevel':0,'level':17,'money':9744,'chip':455,'location':'xe5x9bx9bxe5xb7x9dxe7x9cx81xe6x88x90xe9x83xbdxe5xb8x82','gameLevel':15,'gameLevelScore':235,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':10,'status':1,'ban':0,'user':{'uid':'70672660','name':'xe8x93x9dxe5xbfx98xe6x9cxba','avatarUrl':'https://qiniustatic.wodidashi.com/picture/B4w5aCUOcauHp?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/picture/B4w5aCUOcauHp','sex':1,'vipLevel':0,'level':23,'money':6858,'chip':400,'location':'','gameLevel':17,'gameLevelScore':198,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}},{'sid':11,'status':1,'ban':0,'user':{'uid':'42954055','name':'xe5xbfxa7xe4xbcxa4xe7x9ax84xe5x96xb5xe5x96xb5','avatarUrl':'https://qiniustatic.wodidashi.com/head/NrommfAgMFD4r?imageView/2/w/200/h/200','iconImgLarge':'https://qiniustatic.wodidashi.com/head/NrommfAgMFD4r','sex':1,'vipLevel':0,'level':21,'money':125961,'chip':0,'location':'xe8xa5xbfxe8x97x8fxe8x87xaaxe6xb2xbbxe5x8cxbaxe6x8bx89xe8x90xa8xe5xb8x82','gameLevel':18,'gameLevelScore':547,'rank':0,'userState':'OFFLINE_QUIT_SELF','prepare':1,'limitTime':-1,'enterTime':0,'micState':0,'speakerState':0,'gameSoundState':0,'longitude':0.0,'latitude':0.0,'escapeTimeOut':0}}]}],'viewerCount':30,'viewers':[],'viceType':0,'uidViceMap':{},'readyLimitTime':0,'masterStartLimitTime':1559017334831,'beMasterTimestamp':1559016734831,'lowerPersonTimestamp':0,'room':{'isPrivate':0,'callUp':0,'comeOn':0,'vipRoom':{'owner':'38421703','passwordEnable':false,'vipLevel':8},'isTempRoom':0,'roomLock':0},'inGroup':0,'createTime':1559016266810,'updateTime':1559023136732}";
        Team team = JSON.readValue(t, Team.class);
        System.out.println(team);
        Date startDate = new GregorianCalendar(2019, 4, 29, 23, 59, 59).getTime();
        Date endDate = new GregorianCalendar(2019, 5, 2, 23, 59, 59).getTime();
        boolean result = isBetweenDate(new Date(), startDate, endDate);
        System.out.println(result);
    }
    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean isBetweenDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
