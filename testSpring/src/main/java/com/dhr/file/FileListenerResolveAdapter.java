package com.dhr.file;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author duanhaoran
 * @since 2020/3/11 1:49 PM
 */
public class FileListenerResolveAdapter extends FileAlterationListenerAdaptor {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

    @Override
    public void onDirectoryCreate(File directory) {
        System.out.println(MessageFormat.format("目录[{0}]被创建",directory.getAbsolutePath()));
    }

    @Override
    public void onDirectoryChange(File directory) {
        System.out.println(MessageFormat.format("目录[{0}]被修改",directory.getAbsolutePath()));
    }

    @Override
    public void onDirectoryDelete(File directory) {
        System.out.println(MessageFormat.format("目录[{0}]被删除",directory.getAbsolutePath()));
    }

    @Override
    public void onFileCreate(File file) {
        System.out.println(MessageFormat.format("文件[{0}]被创建",file.getAbsolutePath()));
    }

    @Override
    public void onFileChange(File file) {
        System.out.println(MessageFormat.format("文件[{0}]被修改",file.getAbsolutePath()));
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println(MessageFormat.format("文件[{0}]被删除",file.getAbsolutePath()));
    }
}
