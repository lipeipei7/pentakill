package com.learning.pentaQ.OOPexample;

public class Bird implements Animal {
    public void fly() {

    }

    private void hatch() {
        System.out.println("Hatch an egg🥚");
    }

    @Override
    public void sound() {
        System.out.println("叽叽喳喳🐦");
    }

    @Override
    public void eat() {
        System.out.println("啄米🐤");
    }

    @Override
    public void sleep() {
        System.out.println("站着睡🐦");
    }

    @Override
    public void reproduce() {
        hatch();
    }
}
