
    package com.company;
    import java.util.Scanner;
    public class poker {
        char[][] str= new char[10][2];
        char Three_identical_poker_black,Three_identical_poker_white,Pair_black,Pair_white;
        char Double_pair_black_max,Double_pair_black_min,Double_pair_white_max,Double_pair_white_min;

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
            Poker_types_black();
            Poker_types_white();
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

        public int Poker_types_black()
        {

            if(Same_color_black()&&Straight_black())
                return 99;
            else if(Same_color_black())
               return 88;
            else if(Straight_black())
                return 77;

            int[] num = new int[5];
            for(int i=0;i<5;i++)
            {
                for(int j=0;j<5;j++) {
                    if (str[i][0] == str[j][0])
                    {
                        num[i]++;
                    }

                }
            }
            switch(num[0]+num[1]+num[2]+num[3]+num[4])
            {
                case 5:
                {
                    return 5;
                }
                case 7:
                {
                    int i;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==2)
                            break;
                    }
                    Pair_black=str[i][0];
                    return 7;
                }
                case 9:
                {
                    int i;
                    char temp;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==2)
                            break;
                    }
                    Double_pair_black_min=str[i][0];
                    for(;i<5;i++)
                    {
                        if(num[i]==2&&str[i][0]!=Double_pair_black_min)
                            break;
                    }
                    temp=str[i][0];
                    if(Double_pair_black_min>temp) {
                        Double_pair_black_max = Double_pair_black_min;
                        Double_pair_black_min = temp;
                    }
                    else
                    {
                        Double_pair_black_max=temp;
                    }
                    return 9;
                }
                case 11:
                case 13:
                case 17:
                {
                    int i;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==3)
                            break;
                    }
                    Three_identical_poker_black=str[i][0];
                    return 17;
                }

            }
            return 0;
        }

        public int Poker_types_white()
        {
            int[] num = new int[5];
            if(Same_color_white()&&Straight_white())
                return 99;
            else if(Same_color_white())
                return 88;
            else if(Straight_white())
                return 77;

            for(int i=0;i<5;i++)
            {
                for(int j=0;j<5;j++) {
                    if (str[i+5][0] == str[j+5][0])
                    {
                        num[i]++;
                    }

                }
            }
            switch(num[0]+num[1]+num[2]+num[3]+num[4])
            {
                case 5:
                {
                    return 5;
                }
                case 7:
                {
                    int i;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==2)
                            break;
                    }
                    Pair_white=str[i+5][0];
                    return 7;
                }
                case 9:
                {
                    int i;
                    char temp;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==2)
                            break;
                    }
                    Double_pair_white_min=str[i+5][0];
                    for(;i<5;i++)
                    {
                        if(num[i]==2&&str[i+5][0]!=Double_pair_white_min)
                            break;
                    }
                    temp=str[i+5][0];
                    if(Double_pair_white_min>temp) {
                        Double_pair_white_max = Double_pair_white_min;
                        Double_pair_white_min = temp;
                    }
                    else
                    {
                        Double_pair_white_max=temp;
                    }
                    return 9;
                }
                case 11:
                case 13:
                case 17:
                {
                    int i;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==3)
                            break;
                    }
                    Three_identical_poker_white=str[i+5][0];
                    return 17;
                }

            }
            return 0;
        }

        public int Comparators()
        {
            if(Poker_types_black()>Poker_types_white())
                return 1;
            else if(Poker_types_black()<Poker_types_white())
                return -1;
            else
            {
                switch(Poker_types_black())
                {
                    case 5:{
                        for(int i=4;i>-1;i--)
                        {
                            if(str[i][0]>str[i+5][0])
                                return 1;
                            else if(str[i][0]<str[i+5][0])
                                return -1;
                        }
                        return 0;
                    }
                    case 7:{
                        if(Pair_black>Pair_white)
                            return 1;
                        if(Pair_black<Pair_white)
                            return -1;
                        for(int i=4,j=9;i>-1;i--,j--)
                        {
                            if(str[i][0]==Pair_black)
                            {
                                i=i-2;
                            }
                            if(str[j][0]==Pair_white)
                            {
                                j=j-2;
                            }
                            if(str[i][0]>str[j][0])
                                return 1;
                            else if(str[i][0]<str[j][0])
                                return -1;
                        }
                        return 0;
                    }
                    case 9:{
                        if(Double_pair_black_max>Double_pair_white_max)
                            return 1;
                        else if(Double_pair_black_max<Double_pair_white_max)
                            return -1;
                        else {
                            if(Double_pair_black_min>Double_pair_white_min)
                                return 1;
                            else if(Double_pair_black_min<Double_pair_white_min)
                                return -1;
                            else
                            {
                                for(int i=4,j=9;i>-1;i--,j--)
                                {
                                    if(str[i][0]==Double_pair_black_max||str[i][0]==Double_pair_black_min)
                                    {
                                        i=i-3;
                                    }
                                    if(str[i][0]==Double_pair_white_max||str[i][0]==Double_pair_white_min)
                                    {
                                        j=j-3;
                                    }
                                    if(str[i][0]>str[j][0])
                                        return 1;
                                    else if(str[i][0]<str[j][0])
                                        return -1;
                                }
                                return 0;
                            }
                        }
                    }
                    case 17:{
                        if(Three_identical_poker_black>Three_identical_poker_white)
                            return 1;
                        if(Three_identical_poker_black<Three_identical_poker_white)
                            return -1;
                        for(int i=4,j=9;i>-1;i--,j--)
                        {
                            if(str[i][0]==Three_identical_poker_black)
                            {
                                i=i-3;
                            }
                            if(str[j][0]==Three_identical_poker_white)
                            {
                                j=j-3;
                            }
                            if(str[i][0]>str[j][0])
                                return 1;
                            else if(str[i][0]<str[j][0])
                                return -1;
                        }
                        return 0;
                    }
                }
            }
            return 0;
        }
    }

