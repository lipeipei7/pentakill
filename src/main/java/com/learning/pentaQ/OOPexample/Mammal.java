package com.learning.pentaQ.OOPexample;

public class Mammal implements Animal {

    public void hunt() {
        System.out.println("I Can hunt👍");
    }

    private void born() {
        System.out.println("Born a baby👶🏻");
    }

    @Override
    public void sound() {

    }

    @Override
    public void eat() {
        System.out.println("牙齿咬🦷");
    }

    @Override
    public void sleep() {
        System.out.println("躺着睡😴");
    }

    @Override
    public void reproduce() {
        born();
    }
}
