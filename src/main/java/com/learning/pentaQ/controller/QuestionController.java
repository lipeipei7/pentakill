package com.learning.pentaQ.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/q")
public class QuestionController {

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public String runQuestion() {
        return "你好 培培";
    }
}
