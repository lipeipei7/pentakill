package com.learning.pentaQ.controller;

import com.learning.pentaQ.data.Puzzle;
import com.learning.pentaQ.data.impl.ThreeSum;
import com.learning.pentaQ.data.impl.TwoSum;
import com.learning.pentaQ.sevice.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/q")
public class PuzzleController {

    @Autowired
    private PuzzleService puzzleService;

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public ResponseEntity<String> runQuestion(@RequestParam String name) {
        Puzzle q;
        try {
            q = puzzleService.getPuzzle(name);
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException |
                ClassNotFoundException | NoClassDefFoundError e) {
            return new ResponseEntity<>("Puzzle Not Found!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(puzzleService.runPuzzle(q), HttpStatus.OK);
    }
}
