package com.dhr.file;

import java.io.File;

/**
 * @author duanhaoran
 * @since 2019/4/19 8:18 PM
 */
public class FileTest {

    public static void main(String[] args) {
        File file = new File("");
        String absolutePath = file.getAbsolutePath();
        File file1 = new File(absolutePath, "home");
        if (!file1.exists()) {
            System.out.println("我建了一个文件夹 ");
            boolean mkdirs = file1.mkdirs();
            System.out.println(mkdirs);
        }
        if (file1.isDirectory()) {
            System.out.println("我是文件夹！");
        }

        System.out.println(file);
    }
}
