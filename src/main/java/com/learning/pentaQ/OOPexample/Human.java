package com.learning.pentaQ.OOPexample;

public class Human extends Mammal {

    public void code() {

    }

    @Override
    public void sound() {
        System.out.println("说话📢");
    }

    public static void main(String[] args) {
        Animal a = Whale.getInstance();

        a.sound();

        Whale w = Whale.getInstance();

        System.out.println(w.equals(a));



    }
}
