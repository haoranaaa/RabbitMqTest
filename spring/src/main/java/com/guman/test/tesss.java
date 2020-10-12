package com.guman.test;


import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Splitter;
import com.guman.common.io.FileReader;
import com.guman.common.io.FileWriter;
import com.guman.common.json.JSON;
import lombok.Data;
import okhttp3.*;
import org.apache.commons.collections.MapUtils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/5/28 1:23 PM
 */
public class tesss {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private static Splitter on = Splitter.on(",");


    public static void main(String[] args) throws IOException, InterruptedException {
        tesss tesss = new tesss();
//        tesss.castMap();
//        System.out.println(tesss.senMsg("94013539"));
        //tesss.readFile();
        String json = "[{\"gameType\":1004,\"subType\":0,\"roomIds\":[\"1303600\",\"1234242\",\"1350759\",\"1398645\",\"1172108\",\"1203762\",\"1128247\",\"1129741\",\"1210379\",\"1227550\",\"1289799\",\"1119188\",\"1336516\",\"1114330\",\"1099483\",\"1341684\",\"1101752\",\"1299250\",\"1333655\",\"1287106\",\"1029506\",\"1204024\",\"1318862\",\"1330058\",\"1159443\",\"1340029\",\"1130258\",\"1386687\",\"1155690\",\"1331885\",\"1133548\",\"1398431\",\"1306352\",\"1181121\",\"1080370\",\"1320014\",\"1335235\",\"1124580\",\"1244150\",\"1358936\",\"1255502\",\"1171894\",\"1158475\",\"1135012\",\"1107095\",\"1168128\",\"1355818\",\"1297278\",\"1164352\",\"1260827\",\"1209608\",\"1204894\",\"1170682\",\"1167520\",\"1378703\",\"1288642\",\"1147443\",\"1270654\",\"1285356\",\"1140269\",\"1209087\",\"1133191\",\"1208942\",\"1168750\",\"1121908\",\"1153172\",\"1303112\",\"1221797\",\"1243272\",\"1394624\",\"1267906\",\"1342170\",\"1260917\",\"1009242\",\"1169068\",\"1283831\",\"1058480\",\"1337269\",\"1271618\",\"1203438\",\"1189456\",\"1317808\",\"1391373\",\"1284121\",\"1111250\",\"1246575\",\"1249072\",\"1324882\",\"1223842\",\"1332125\",\"1375385\",\"1305963\",\"1117090\",\"1349904\",\"1150322\",\"1248524\",\"1126175\",\"1237177\",\"1329281\",\"1161550\",\"1332493\",\"1165100\",\"1118470\",\"1310608\",\"1197927\",\"1306343\",\"1042770\",\"1099780\",\"1108238\",\"1355437\",\"1130611\",\"1183848\",\"1121061\",\"1181587\",\"1212985\",\"1305055\",\"1195952\",\"1347338\",\"1210413\",\"1308709\",\"1162332\",\"1353347\",\"1206511\",\"1151156\",\"1393236\",\"1315753\",\"1129722\",\"1244876\",\"1321476\",\"1282815\",\"1264570\",\"1377646\",\"1361511\",\"1364078\",\"1205340\",\"1109144\",\"1371958\",\"1156409\",\"1344062\",\"1164899\",\"1397859\",\"1248922\",\"1326925\",\"1161582\",\"1223599\",\"1388356\",\"1128993\",\"1239515\",\"1397028\",\"1170688\",\"1333671\",\"1242791\",\"1306097\",\"1360544\",\"1379209\",\"1104006\",\"1103894\",\"1252214\",\"1112226\",\"1180832\",\"1167325\",\"1327332\",\"1243032\",\"1095911\",\"1323926\",\"1313707\",\"1358375\",\"1295949\",\"1153592\",\"1194789\",\"1221324\",\"1040086\",\"1156357\",\"1174721\",\"1286443\",\"1173423\",\"1160103\",\"1147966\",\"1106533\",\"1394567\",\"1239584\",\"1283372\",\"1194745\",\"1199244\",\"1138495\",\"1158259\",\"1022231\",\"1198399\",\"1344209\",\"1085187\",\"1312216\",\"1290824\",\"1184168\",\"1032315\",\"1165193\",\"1286915\",\"1228719\",\"1190372\",\"1203842\",\"1295988\",\"1352278\",\"1128714\",\"1132787\",\"1261899\",\"1340454\",\"1137681\",\"1352790\",\"1197920\",\"1248112\",\"1292347\",\"1165770\",\"1389920\",\"1124876\",\"1303773\",\"1325515\",\"1270559\",\"1369059\",\"1151501\",\"1280473\",\"1197326\",\"1012004\",\"1276400\",\"1122112\",\"1291676\",\"1341056\",\"1253768\",\"1183321\",\"1246864\",\"1165677\",\"1166147\",\"1171340\",\"1145109\",\"1337911\",\"1375202\",\"1222872\",\"1274555\",\"1032511\",\"1172728\",\"1245087\",\"1248239\",\"1309776\",\"1193097\",\"1031687\",\"1296539\",\"1210636\",\"1111802\",\"1249366\",\"1162054\",\"1169059\",\"1350800\",\"1197801\",\"1263854\",\"1176081\",\"1150053\",\"1121284\",\"1264180\",\"1303800\",\"1365170\",\"1354905\",\"1101641\",\"1212850\",\"1167092\",\"1314209\",\"1043978\",\"1182023\",\"1006380\",\"1193171\",\"1246231\",\"1131830\",\"1298387\",\"1376679\",\"1191007\",\"1257289\",\"1251741\",\"1321512\",\"1212210\",\"1349724\",\"1265562\",\"1328585\",\"1155582\"]},{\"gameType\":1004,\"subType\":1,\"roomIds\":[\"30042\",\"363055\",\"375892\",\"376654\",\"411293\",\"427818\",\"439699\",\"541349\",\"546109\",\"553319\",\"555191\",\"555613\",\"577944\",\"579665\",\"580447\",\"598843\",\"1006872\",\"1007617\",\"1011911\",\"1018712\",\"1027100\",\"1029295\",\"1037702\",\"1040419\",\"1055554\",\"1086194\",\"1094786\",\"1099845\",\"1100262\",\"1106097\",\"1126333\",\"1127021\",\"1133510\",\"1135205\",\"1140045\",\"1141748\",\"1143707\",\"1143906\",\"1153631\",\"1155106\",\"1157110\",\"1157526\",\"1158164\",\"1162097\",\"1164497\",\"1165156\",\"1167396\",\"1171157\",\"1173706\",\"1176553\",\"1180656\",\"1181922\",\"1183216\",\"1184779\",\"1188235\",\"1188244\",\"1188605\",\"1193207\",\"1194884\",\"1197922\",\"1199432\",\"1209093\",\"1210177\",\"1211662\",\"1214361\",\"1215283\",\"1216442\",\"1218112\",\"1219154\",\"1220502\",\"1223098\",\"1228344\",\"1229846\",\"1234032\",\"1240383\",\"1241649\",\"1241700\",\"1241829\",\"1243200\",\"1245280\",\"1251429\",\"1252913\",\"1253700\",\"1256317\",\"1261773\",\"1265141\",\"1266345\",\"1269202\",\"1273654\",\"1274294\",\"1274456\",\"1275915\",\"1280771\",\"1287010\",\"1295154\",\"1299265\",\"1302655\",\"1313361\",\"1322488\",\"1329752\",\"1333204\",\"1336174\",\"1338601\",\"1350441\",\"1351522\",\"1362613\",\"1364446\",\"1365776\",\"1368989\",\"1372851\",\"1374451\",\"1375388\",\"1375766\",\"1378175\",\"1380114\",\"1385543\",\"1385936\",\"1388374\",\"1388595\",\"1399131\",\"1399843\"]},{\"gameType\":1004,\"subType\":2,\"roomIds\":[\"265878\",\"357577\",\"402408\",\"432621\",\"508811\",\"542326\",\"555646\",\"556567\",\"561119\",\"568520\",\"590277\",\"1000430\",\"1014251\",\"1019058\",\"1019220\",\"1026363\",\"1027481\",\"1047502\",\"1048290\",\"1055353\",\"1055448\",\"1061399\",\"1062190\",\"1073194\",\"1084534\",\"1094838\",\"1107703\",\"1107795\",\"1108869\",\"1112604\",\"1114357\",\"1122298\",\"1124653\",\"1130270\",\"1135944\",\"1139885\",\"1155537\",\"1158594\",\"1161902\",\"1167160\",\"1168877\",\"1172869\",\"1177592\",\"1179098\",\"1187246\",\"1187815\",\"1196369\",\"1200954\",\"1202213\",\"1204513\",\"1208083\",\"1221535\",\"1226949\",\"1235087\",\"1236633\",\"1237789\",\"1238034\",\"1238236\",\"1243735\",\"1256182\",\"1262735\",\"1268313\",\"1273339\",\"1277110\",\"1294248\",\"1308393\",\"1312536\",\"1313240\",\"1323166\",\"1323913\",\"1335390\",\"1340300\",\"1344350\",\"1350003\",\"1358131\",\"1369427\",\"1395646\"]},{\"gameType\":1004,\"subType\":3,\"roomIds\":[\"116688\",\"217099\",\"226360\",\"287513\",\"305303\",\"306619\",\"332470\",\"332762\",\"380667\",\"385607\",\"390102\",\"486880\",\"513753\",\"549150\",\"551952\",\"563150\",\"566353\",\"568083\",\"571112\",\"1002044\",\"1012995\",\"1016359\",\"1032090\",\"1037511\",\"1051010\",\"1055540\",\"1056910\",\"1060765\",\"1066477\",\"1089585\",\"1096531\",\"1110223\",\"1111521\",\"1113235\",\"1115961\",\"1117009\",\"1117388\",\"1117451\",\"1118660\",\"1120227\",\"1121005\",\"1124214\",\"1124444\",\"1126312\",\"1133965\",\"1134795\",\"1141799\",\"1145843\",\"1152392\",\"1157989\",\"1160635\",\"1161171\",\"1166830\",\"1170041\",\"1172518\",\"1188211\",\"1190179\",\"1194327\",\"1194508\",\"1197621\",\"1200630\",\"1201298\",\"1201586\",\"1208661\",\"1208762\",\"1216798\",\"1217266\",\"1220816\",\"1222339\",\"1222788\",\"1226506\",\"1226825\",\"1230111\",\"1233122\",\"1233345\",\"1234611\",\"1270122\",\"1274272\",\"1277435\",\"1277753\",\"1280909\",\"1281829\",\"1282762\",\"1285133\",\"1289947\",\"1294321\",\"1295178\",\"1305948\",\"1306520\",\"1312116\",\"1315999\",\"1320903\",\"1321828\",\"1325509\",\"1326017\",\"1326490\",\"1327334\",\"1331356\",\"1335456\",\"1344418\",\"1346662\",\"1351564\",\"1351853\",\"1356632\",\"1357081\",\"1375220\",\"1399158\"]},{\"gameType\":1004,\"subType\":6,\"roomIds\":[\"1010736\",\"1023082\",\"1044548\",\"1058461\",\"1118217\",\"1120435\",\"1125428\",\"1127162\",\"1128057\",\"1139899\",\"1142714\",\"1146992\",\"1156165\",\"1156555\",\"1157125\",\"1158675\",\"1160791\",\"1162053\",\"1162538\",\"1168449\",\"1172678\",\"1178580\",\"1187194\",\"1190153\",\"1193021\",\"1196684\",\"1200051\",\"1204861\",\"1205502\",\"1211979\",\"1214859\",\"1215612\",\"1218333\",\"1225205\",\"1225975\",\"1228641\",\"1234365\",\"1236242\",\"1237436\",\"1245442\",\"1252681\",\"1259028\",\"1259823\",\"1263096\",\"1278438\",\"1278456\",\"1281632\",\"1281716\",\"1283628\",\"1285273\",\"1287358\",\"1291522\",\"1292954\",\"1302329\",\"1303295\",\"1306290\",\"1306324\",\"1313285\",\"1317465\",\"1322891\",\"1328643\",\"1330003\",\"1330794\",\"1333340\",\"1333986\",\"1334582\",\"1336922\",\"1340256\",\"1341095\",\"1346240\",\"1364950\",\"1367301\",\"1369346\",\"1370898\",\"1375498\",\"1376105\",\"1377883\",\"1393595\",\"1393686\",\"1394753\"]}]";
        List<Param> params = JSON.readValue(json, new TypeReference<List<Param>>() {
        });
        params.forEach(i->{
            System.out.println("subType:"+ i.getSubType() + " count:"+ i.getRoomIds().size());
            int vip = 0;
            int official = 0;
            for (String id : i.getRoomIds()) {
                try {
                    if (tesss.isVipRoom(id)) {
                        vip++;
                    }
                    if (tesss.isOfficialRoom(id)) {
                        official++;
                    }
                } catch (IOException e) {

                }
            }
            System.out.println("vip :"+ vip + ",official :" + official);
        });
    }
    @Data
    public static class Param{
        private Integer gameType;
        private Integer subType;
        private List<String> roomIds;
    }

    private void readFile() throws IOException, InterruptedException {
        RandomAccessFile file = new RandomAccessFile("/Users/duanhaoran/Desktop/uids", "r");
        FileChannel fileChannel = file.getChannel();

        FileReader fileReader = new FileReader(fileChannel, 1024, "utf-8");
        String line;

        RandomAccessFile file2 = new RandomAccessFile("/Users/duanhaoran/Desktop/uid2", "rw");
        FileWriter fileWriter = new FileWriter(file2.getChannel());
        int i = 0;
        while ((line = fileReader.readline()) != null) {
            i++;
            Integer count = genUserFriendCount(line);
            Integer integer = genUserLevelParam(line);
            fileWriter.writeLine(line.concat("," + count + "," + integer));
            if (i % 100 == 0) {
                Thread.sleep(100);
            }
        }

        fileReader.close();
        file.close();
        file2.close();
        fileWriter.close();
    }

    private Integer genUserLevelParam(String uid) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://apitest.wb-intra.com/ms/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "user.getValue");
        builder1.add("host", "user.wb-ms.com");
        builder1.add("params", "{\"uid\":" + uid + ",\"field\":\"level\"}");
        builder.post(builder1.build());
        Call call = okHttpClient.newCall(builder.build());
        try (Response execute = call.execute()) {
            String string = execute.body().string();
            Map<String, Object> map = JSON.readValue(string, new TypeReference<Map<String, Object>>() {
            });
            Map data = MapUtils.getMap(map, "data");
            if (MapUtils.isEmpty(data)) {
                return -1;
            }
            return MapUtils.getInteger(data, "value", -1);
        }
    }

    private Integer genUserFriendCount(String uid) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://apitest.wb-intra.com/ms/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "friend.getFriendCount");
        builder1.add("host", "friend.wb-ms.com");
        builder1.add("params", "{\"uid\":" + uid + "}");
        builder.post(builder1.build());
        Call call = okHttpClient.newCall(builder.build());
        try (Response execute = call.execute()) {
            String string = execute.body().string();
            Map<String, Object> map = JSON.readValue(string, new TypeReference<Map<String, Object>>() {
            });
            Map data = MapUtils.getMap(map, "data");
            if (MapUtils.isEmpty(data)) {
                return 0;
            }
            return MapUtils.getInteger(data, "count", 0);
        }
    }

    private void castMap() throws IOException, InterruptedException {
        File file = new File("/Users/duanhaoran/Desktop/uids");
        FileInputStream input = null;
        input = new FileInputStream(file);
        FileChannel fin = null;
        fin = input.getChannel();
        MappedByteBuffer mbb = null;
        mbb = fin.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        byte data[] = new byte[(int) file.length()];
        int foot = 0;
        while (mbb.hasRemaining()) {
            data[foot++] = mbb.get();
        }
        int k = 0;
        int count = 0;
        String x = new String(data);
        String[] split = x.split("\n");

        for (String s : split) {
            int i = senMsg(s);
            if (i >= 18) {
                k++;
            }
            System.out.println(s + ": " + i);
            count++;
            if (count % 20 == 0) {
                Thread.sleep(100);
            }
        }
        System.out.println("小于18 :" + k);
        System.out.println("总数:" + count);
        fin.close();
        input.close();

    }

    private int senMsg(String uid) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://apitest.wb-intra.com/ms/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "gameserver.getGameUserLevelNew");
        builder1.add("host", "gameserver.wb-ms.com");
        builder1.add("params", "{\"uid\":" + uid + ",\"gameType\":1004}");
        builder.post(builder1.build());
        Call call = okHttpClient.newCall(builder.build());
        try (Response execute = call.execute()) {
            String string = execute.body().string();
            Map<String, Object> map = JSON.readValue(string, new TypeReference<Map<String, Object>>() {
            });
            Map data = MapUtils.getMap(map, "data");
            if (MapUtils.isEmpty(data)) {
                return -1;
            }
            return MapUtils.getInteger(data, "level", -1);
        }
    }

    private boolean isVipRoom(String roomId) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://apitest.wb-intra.com/ms/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "room.isVipRoom");
        builder1.add("host", "room.wb-ms.com");
        builder1.add("params", "{\"roomId\":\"" + roomId + "\"}");
        builder.post(builder1.build());
        Call call = okHttpClient.newCall(builder.build());
        try (Response execute = call.execute()) {
            String string = execute.body().string();
            return string.contains("true");
        }
    }

    private boolean isOfficialRoom(String roomId) throws IOException {
        Request.Builder builder = new Request.Builder();
        builder.url("http://apitest.wb-intra.com/rpc/test");
        FormBody.Builder builder1 = new FormBody.Builder();
        builder1.add("url", "/official/room/isOfficial");
        builder1.add("host", "official-server");
        builder1.add("params", "{\"teamId\":\"" + roomId + "\"}\n");
        builder.post(builder1.build());
        Call call = okHttpClient.newCall(builder.build());
        try (Response execute = call.execute()) {
            String string = execute.body().string();
            return string.contains("true");
        }
    }

}
