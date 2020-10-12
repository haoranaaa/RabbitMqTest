package com.dhr.file;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

/**
 * @author duanhaoran
 * @since 2020/1/7 11:48 AM
 */
public abstract class AbstractFileTransform<T> {


    abstract T resolve2Model(String line);

    private List<T> getList(File file) {
        try (InputStreamReader is = new InputStreamReader(new FileInputStream(file))) {
            BufferedReader br = new BufferedReader(is);
            List<T> list = Lists.newArrayList();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                list.add(resolve2Model(line));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
