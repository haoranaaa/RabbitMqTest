package com.dhr.test;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 29 九月 2018
 */
public class ATest {

    void Hello(){
        System.out.println("hello!");
    }

    public static void main(String[] args) {
        String format = "哈哈哈";
        String str = String.format(format, "a");
        System.out.println(format);
        System.out.println(str);
        User build = User.builder().userId(1).userName("1").build();
        User build1 = User.builder().userId(2).userName("1").build();
        Map<String, List<Integer>> collect = Lists.newArrayList(build, build1).stream().collect(Collectors.groupingBy(User::getUserName, Collectors.mapping(User::getUserId, Collectors.toList())));
        collect.forEach((i,j)->{
            System.out.println(i+":"+j);
        });
    }


}
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class User{
    int userId;
    String userName;
}
