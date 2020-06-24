
    package com.company;
import java.util.Scanner;
    public class poker {
        char[][] str= new char[10][2];

        public poker(){
            Scanner input = new Scanner(System.in);
            String temp;
            for(int i=0;i<10;i++)
            {
                temp = input.next();
                if(temp.equals("Black:")||temp.equals("White:"))
                {
                    temp = input.next();
                }
                if(temp.charAt(0)>'9')
                {
                    switch(temp.charAt(0))
                    {
                        case 'T':{
                            str[i][0]='A';
                            break;
                        }
                        case 'J':{
                            str[i][0]='B';
                            break;
                        }
                        case 'Q':{
                            str[i][0]='C';
                            break;
                        }
                        case 'K':{
                            str[i][0]='D';
                            break;
                        }
                        case 'A':{
                            str[i][0]='E';
                            break;
                        }
                    }
                }
                else {
                    str[i][0]=temp.charAt(0);
                }
                str[i][1]=temp.charAt(1);
            }
            sorting_black();
            sorting_white();
        }

        public void print()
        {
            for(int i=0;i<10;i++)
            {
                System.out.print(str[i][0]);
                System.out.print(str[i][1]+" ");
            }
        }

        public boolean Same_color_black()
        {
            if(str[0][1]==str[1][1]&&str[0][1]==str[2][1]&&str[0][1]==str[3][1]&&str[0][1]==str[4][1])
                return true;
            else
                return false;
        }

        public boolean Same_color_white()
        {
            if(str[5][1]==str[6][1]&&str[5][1]==str[7][1]&&str[5][1]==str[8][1]&&str[5][1]==str[9][1])
                return true;
            else
                return false;
        }

        private void sorting_black()
        {
            char[] temp = new char[2];
            for(int i=0;i<4;i++) {
                for (int j = 0; j<4-i; j++) {
                    if(str[j][0]>str[j+1][0]) {
                        temp[0] = str[j][0];
                        temp[1] = str[j][1];
                        str[j][0]=str[j+1][0];
                        str[j][1]=str[j+1][1];
                        str[j+1][0]=temp[0];
                        str[j+1][1]=temp[1];
                    }
                }
            }
        }

        private void sorting_white()
        {
            char[] temp = new char[2];
            for(int i=0;i<4;i++) {
                for (int j = 0; j<4-i; j++) {
                    if(str[j+5][0]>str[j+6][0]) {
                        temp[0] = str[j+5][0];
                        temp[1] = str[j+5][1];
                        str[j+5][0]=str[j+6][0];
                        str[j+5][1]=str[j+6][1];
                        str[j+6][0]=temp[0];
                        str[j+6][1]=temp[1];
                    }
                }
            }
        }

        public boolean Straight_black()
        {
            if(str[0][0]==str[1][0]-1&&str[0][0]==str[2][0]-2&&str[0][0]==str[3][0]-3&&str[0][0]==str[4][0]-4)
                return true;
            else
                return false;
        }

        public boolean Straight_white()
        {
            if(str[5][0]==str[6][0]-1&&str[5][0]==str[7][0]-2&&str[5][0]==str[8][0]-3&&str[5][0]==str[9][0]-4)
                return true;
            else
                return false;
        }

    }

