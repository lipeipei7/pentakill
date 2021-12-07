package com.learning.pentaQ.OOPexample;

public class Whale extends Mammal {
    public int length;
    public int age;
    private static final Whale instance = new Whale(0, 0);

    public Whale(int length, int age) {
        this.length = length;
        this.age = age;
    }

    public static Whale getInstance() {
        return instance;
    }

    public void swim() {

    }

    @Override
    public void sound() {
        System.out.println("鸣叫🐳");
    }
}
