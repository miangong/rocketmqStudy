package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bootMq.BootMqTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Description： TODO
 * <p>
 * Author: miangong
 * <p>
 * Date: Created in 2020/12/24 17:46
 */
@Controller
public class BootMqController {

    @Autowired
    private BootMqTest bootMqTest;

    @RequestMapping("test")
    @ResponseBody
    public JSONObject getInfo() {
        bootMqTest.send();
        return null;
    }
}
