package com.dhr.file;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author duanhaoran
 * @since 2020/3/11 1:48 PM
 */
public class FileListenerTest {
    String monitorDir = "/Users/duanhaoran/Projects/study";

    private static long interval = TimeUnit.SECONDS.toMillis(1);


    public static void main(String[] args) throws Exception {
        FileListenerTest fileListenerTest = new FileListenerTest();
        ArrayList<String> fileNames = Lists.newArrayList();
        findFileList(new File(fileListenerTest.monitorDir), fileNames);
        System.out.println(fileNames.size());
//        fileListenerTest.fileAltrationListenerTest();
        fileListenerTest.watchServiceListenerTest();

    }

    private void testCrudFile() throws InterruptedException, IOException {
        //测试监听效果
        //创建子目录
        Thread.sleep(2000);
        File subDir = new File("/Users/duanhaoran/Projects/study/test/1");
        System.out.println(subDir.getName() + "被创建!time:" + System.currentTimeMillis());
        subDir.mkdirs();
        Thread.sleep(interval);

        //创建文件
        File txtFile1 = new File("/Users/duanhaoran/Projects/study/test/uid_0_.txt");
        txtFile1.createNewFile();
        System.out.println(txtFile1.getName() + "被创建!time:" + System.currentTimeMillis());
        File txtFile2 = new File("/Users/duanhaoran/Projects/study/test/1/uid_0_22.txt");
        txtFile2.createNewFile();
        System.out.println(txtFile2.getName() + "被创建!time:" + System.currentTimeMillis());
        Thread.sleep(interval);

        //删除文件
        FileUtils.deleteQuietly(txtFile2);
        FileUtils.deleteQuietly(txtFile1);
        Thread.sleep(interval);

        //删除子目录
        FileUtils.deleteDirectory(subDir);
        Thread.sleep(interval);
    }

    private void watchServiceListenerTest() throws IOException, InterruptedException {
        FileWatchServiceListener listener = new FileWatchServiceListener(monitorDir);
        new Thread(()->listener.start()).start();
//        new Thread(()-> {
//            try {
//                testCrudFile();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();

        Thread.sleep(1000000);
    }

    private void fileAltrationListenerTest() throws Exception {
        //监控目录
        //轮询间隔时间（1000毫秒）
        //创建文件观察器
//        FileAlterationObserver observer = new FileAlterationObserver(
//                monitorDir, FileFilterUtils.and(
//                        FileFilterUtils.fileFileFilter(),
//                        FileFilterUtils.suffixFileFilter(".txt"))
//        );
        FileAlterationObserver observer = new FileAlterationObserver(monitorDir, (file)->{
            if (file.isDirectory()) {
                return true;
            }else {
                return StringUtils.startsWith(file.getName(), "uid_") && StringUtils.endsWith(file.getName(), ".txt");
            }
        });//!StringUtils.endsWith(file.getName(), ".txt")

        observer.addListener(new FileListenerResolveAdapter());

        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        //开始监听
        monitor.start();

        //测试监听效果
        //创建子目录
        testCrudFile();

        //停止监控
        monitor.stop();
    }

    /**
     * 读取目录下的所有文件
     *
     * @param dir
     *            目录
     * @param fileNames
     *            保存文件名的集合
     * @return
     */
    public static void findFileList(File dir, List<String> fileNames) {
        if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录
            return;
        }
        String[] files = dir.list();// 读取目录下的所有目录文件信息
        for (int i = 0; i < files.length; i++) {// 循环，添加文件名或回调自身
            File file = new File(dir, files[i]);
            if (file.isFile()) {// 如果文件
                fileNames.add(dir + "\\" + file.getName());// 添加文件全路径名
            } else {// 如果是目录
                findFileList(file, fileNames);// 回调自身继续查询
            }
        }
    }

    public class FileWatchServiceListener {
        private WatchService watchService;
        private boolean notDone = true;

        public FileWatchServiceListener(String dirPath){
            init(dirPath);
        }

        private void init(String dirPath) {
            Path path = Paths.get(dirPath);
            try {
                watchService = FileSystems.getDefault().newWatchService(); //创建watchService
                path.register(watchService,
                        StandardWatchEventKinds.ENTRY_CREATE,
                        StandardWatchEventKinds.ENTRY_MODIFY,
                        StandardWatchEventKinds.ENTRY_DELETE); //注册需要监控的事件,ENTRY_CREATE 文件创建,ENTRY_MODIFY 文件修改,ENTRY_MODIFY 文件删除
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void start() {
            int count = 0;
            System.out.print("watch...");
            while(true) {
                WatchKey key = watchService.poll();
                if (key == null) {
                    continue;
                }
                for(WatchEvent<?> event: key.pollEvents()) {
                    switch(event.kind().name()) {
                        case "OVERFLOW":
                            System.out.println(++count + ": OVERFLOW");
                            break;
                        case "ENTRY_MODIFY":
                            System.out.println(++count + ": File " + event.context() + " is changed! time :" + System.currentTimeMillis());
                            break;
                        case "ENTRY_CREATE":
                            System.out.println(++count + ": File " + event.context() + " is created! time :" + System.currentTimeMillis());
                            break;
                        case "ENTRY_DELETE":
                            System.out.println(++count + ": File " + event.context() + " is deleted! time :" + System.currentTimeMillis());
                            break;
                        default:
                            System.out.println(++count + ": UNKNOWN EVENT!");
                    }
                }

                key.reset();
            }
        }
    }

}
