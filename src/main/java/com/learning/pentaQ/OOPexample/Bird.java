package com.learning.pentaQ.OOPexample;

public class Bird implements Animal {
    public void fly() {

    }

    private void hatch() {
        System.out.println("Hatch an eggð¥");
    }

    @Override
    public void sound() {
        System.out.println("å½å½å³å³ð¦");
    }

    @Override
    public void eat() {
        System.out.println("åç±³ð¤");
    }

    @Override
    public void sleep() {
        System.out.println("ç«çç¡ð¦");
    }

    @Override
    public void reproduce() {
        hatch();
    }
}
