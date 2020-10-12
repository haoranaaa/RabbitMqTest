package com.dhr.file;

import com.dhr.file.model.ClubOperateLogModel;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author duanhaoran
 * @since 2020/1/7 11:51 AM
 */
public class ClubOperateLogTransform extends AbstractFileTransform<ClubOperateLogModel> {

    private static Splitter splitter = Splitter.on(",");

    @Override
    ClubOperateLogModel resolve2Model(String line) {
        if (line.contains("设置为") || line.contains("编辑公告") || line.contains("加入方式")
                || line.contains("将圈子加入方式修改为") || line.contains("编辑简介") || line.contains("圈子名称修改为") || line.contains("修改了圈子头像")) {
            return null;
        }
        List<String> list = splitter.splitToList(line);


        return null;
    }
}
