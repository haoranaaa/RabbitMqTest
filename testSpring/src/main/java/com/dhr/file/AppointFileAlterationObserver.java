package com.dhr.file;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.FileFilter;

/**
 * @author duanhaoran
 * @since 2020/3/13 3:59 PM
 */
public class AppointFileAlterationObserver extends FileAlterationObserver {
    public AppointFileAlterationObserver(File directory) {
        super(directory);
    }

    @Override
    public File getDirectory() {
        return super.getDirectory();
    }

    @Override
    public FileFilter getFileFilter() {
        return super.getFileFilter();
    }

    @Override
    public void addListener(FileAlterationListener listener) {
        super.addListener(listener);
    }

    @Override
    public void removeListener(FileAlterationListener listener) {
        super.removeListener(listener);
    }

    @Override
    public Iterable<FileAlterationListener> getListeners() {
        return super.getListeners();
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();
    }

    @Override
    public void destroy() throws Exception {
        super.destroy();
    }

    @Override
    public void checkAndNotify() {
        super.checkAndNotify();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
