package com.develop.bank.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */


@Controller
@RequestMapping("/")
public class RestService {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "Called the get Rest Service";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
//    public String post(@RequestBody String s) {
    public String post(@RequestHeader(value = "Authorization") String s) {
        return "yoyo " + s;
    }
}

