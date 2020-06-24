package com.company;

public class Main {

    public static void main(String[] args) {
        poker a =new poker();
        if(a.Straight_white())
        {
            System.out.println("right");
        }
        else
        {
            System.out.println("none");
        }
        a.print();

    }
}
