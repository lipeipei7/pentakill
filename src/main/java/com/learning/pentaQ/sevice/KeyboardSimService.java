package com.learning.pentaQ.sevice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Random;

public class KeyboardSimService {

    private Robot robot;
    private Random random;

    public void init() {
        try {
            robot = new Robot();
            random = new Random();
        } catch (AWTException ignored) {}
    }

    public void press() {
        robot.delay(random.nextInt(30000, 45000));
        keyType(KeyEvent.VK_SPACE);
        keyType(KeyEvent.VK_SPACE);
        keyType(KeyEvent.VK_SPACE);
    }

    private void keyType(int input) {
        robot.keyPress(input);
        robot.keyRelease(input);
    }

    private static void printDuration(ZonedDateTime start) {
        System.out.println(ZonedDateTime.now().toString() + Duration.between(start, ZonedDateTime.now()));
    }

    public static void main(String[] args) {
        KeyboardSimService s = new KeyboardSimService();
        s.init();
        ZonedDateTime d = ZonedDateTime.now();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            printDuration(d);
        }));

        for (int i = 1; i < 1024; i++) {
            s.press();
            if (i % 120 == 0) {
                printDuration(d);
            }
        }
    }
}
