package com.company;

public class Main {

    public static void main(String[] args) {
        poker a =new poker();
        if(a.Comparators()==1)
        {
            System.out.println("Black Wins!");
        }
        else if(a.Comparators()==-1)
        {
            System.out.println("White Wins!");
        }
        else
        {
            System.out.println("Tie");
        }

    }
}
