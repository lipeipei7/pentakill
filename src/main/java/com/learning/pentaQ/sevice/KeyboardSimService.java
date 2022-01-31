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
        robot.delay(random.nextInt(5000, 15000));
        keyType(KeyEvent.VK_SPACE);
        keyType(KeyEvent.VK_SPACE);
        keyType(KeyEvent.VK_SPACE);
    }

    private void keyType(int input) {
        robot.keyPress(input);
        robot.keyRelease(input);
    }


    public static void main(String[] args) {
        KeyboardSimService s = new KeyboardSimService();
        s.init();
        ZonedDateTime d = ZonedDateTime.now();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(ZonedDateTime.now().toString() + Duration.between(d, ZonedDateTime.now()));
        }));

        for (int i = 0; i < 720; i++) {
            s.press();
        }
    }
}