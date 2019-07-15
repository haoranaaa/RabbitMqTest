package com.guman.test;

import com.dhr.bean.Team;
import com.guman.annotation.Wconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author duanhaoran
 * @since 2019/3/25 4:17 PM
 */
@Component("abcTest")
public abstract class ABCTest {

    @Wconfig("x")
    private String x;

    private String y;
    @Autowired
    public HandlerTest test;


    public String getY() {
        return y;
    }
    @Wconfig("y")
    public void setY(String y) {
        this.y = y;
    }

    public void print(){
        Team team = new Team();
        team.getAbs();
        test.print();
    }

    public static void main(String[] args) {
        System.out.println("0".equals("1") || 1 >= 1);
    }
}
