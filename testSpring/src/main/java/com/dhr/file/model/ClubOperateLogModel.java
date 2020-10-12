package com.dhr.file.model;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author duanhaoran
 * @since 2020/1/7 11:52 AM
 */
@Data
public class ClubOperateLogModel {

    private String id;

    private String clubId;

    private String desc;

    private Integer state = 0;

    private String uid;

    private Date createTime;

    public ClubOperateLogModel(String[] strings) {
        id = strings[0];
        clubId = strings[1];
        desc = strings[2].replace("\"","");
        uid = strings[3];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {
            createTime = sdf.parse(strings[4]);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public ClubOperateLogModel() {
    }


}
