package com.learning.pentaQ.sevice;

import com.learning.pentaQ.data.Puzzle;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class PuzzleService {

    public String runPuzzle(Puzzle p) {
        return p.run();
    }

    public Puzzle getPuzzle(String name)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        String namePrefix = "com.learning.pentaQ.data.impl.";
        return (Puzzle) Class.forName(namePrefix + name).getDeclaredConstructor().newInstance();
    }
}
