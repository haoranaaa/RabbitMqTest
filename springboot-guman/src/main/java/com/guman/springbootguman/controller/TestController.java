package com.guman.springbootguman.controller;

import com.guman.springbootguman.aspect.annotation.MarkGetRedisInfoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2020/4/7 5:58 PM
 */
@RestController
@MarkGetRedisInfoKey
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/a")
    @Controller
    public String testForward(HttpServletRequest request, HttpServletResponse response) {
        try {
            Scanner sc = new Scanner(System.in);
            request.setAttribute("b", "b");
            request.getRequestDispatcher("/test/b").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("error" + e.getMessage());
        }
        return "a";
    }


    @GetMapping("/b")
    public String testB(@RequestBody String b) {
        System.out.println(b);
        return "b";
    }

    @PostMapping("/c")
    public String testC(@RequestBody String b) throws InterruptedException, IOException {
        System.out.println("factory"+ stringRedisTemplate.getConnectionFactory().toString());
        String[] is = IntStream.range(0, 2010).mapToObj(String::valueOf).toArray(String[]::new);
        stringRedisTemplate.opsForSet().add("JobTagSet", is);
        int i = 0;
        while (i++ <= 100) {
            //scan获取jobSet
            Cursor<String> cursor = stringRedisTemplate.opsForSet().scan("JobTagSet",
                    ScanOptions.scanOptions().count(1000).build());
            while (cursor.hasNext()) {
                String jobTag = cursor.next();
            }
//            try {
//                cursor.close();
//            } catch (IOException e) {
//
//            }
            cursor.close();
            Thread.sleep(1000);
            stringRedisTemplate.getClientList().stream().map(k->k.getAddressPort()).forEach(System.out::println);
        }
        System.out.println(b);
        return "b";
    }
}                                  m