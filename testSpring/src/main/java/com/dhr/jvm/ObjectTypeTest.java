package com.dhr.jvm;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * @author duanhaoran
 * @since 2019/8/30 2:21 PM
 */
public class ObjectTypeTest {

    private static final String FILE_NAME = "11123.csv";


    private static final String FIND_SHELL_CMD = "find /Users/duanhaoran/Desktop -name %s 2>/dev/null";


    public static void main(String[] args) throws IOException, InterruptedException {
        String exec = String.format(FIND_SHELL_CMD, FILE_NAME);
        String[] strings = {"/bin/sh", "-c", exec};
        ProcessBuilder pb = new ProcessBuilder(strings);
        Process process = pb.start();
        System.out.println(exec);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String path = null;
            while ((path = br.readLine()) != null) {
                if (path.endsWith(FILE_NAME)) {
                    String dirPath = path.substring(0, path.length() - FILE_NAME.length());
                    System.out.println(dirPath);
                    break;
                }
            }
            throw new IllegalArgumentException("初始化失败！ 查询不到机器上的RecordingSample.java 文件！ ");
        }
    }




}
