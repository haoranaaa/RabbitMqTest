package com.guman.gumanrouter.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author duanhaoran
 * @since 2019/7/15 2:00 PM
 */
@RestController
public class RouterController {

    @RequestMapping("/router/{message}")
    public void commonRequest(HttpServletRequest request, @PathVariable String message){

    }

}
