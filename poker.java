
    package com.company;
    import java.util.Scanner;
    public class poker {
        char[][] str= new char[10][2];
        char Three_identical_poker_black,Three_identical_poker_white,Pair_black,Pair_white;
        char Double_pair_black_max,Double_pair_black_min,Double_pair_white_max,Double_pair_white_min;

        public poker()           //构造函数，将输入的字符串中的牌类型读出来，按照非递减顺序排列
        {
            Scanner input = new Scanner(System.in);
            String temp;
            for(int i=0;i<10;i++)
            {
                temp = input.next();
                if(temp.equals("Black:")||temp.equals("White:"))        //跳过“Black：”和“White：”
                {
                    temp = input.next();
                }
                if(temp.charAt(0)>'9')                          //将T,J,Q,K,A替换为A,B,C,D,E便于排序
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
            sorting_black();            //排序
            sorting_white();
            Poker_types_black();        //判断black方的牌型
            Poker_types_white();        //判断white方牌型
        }

        public boolean Same_color_black()       //判断black方是否为同花
        {
            if(str[0][1]==str[1][1]&&str[0][1]==str[2][1]&&str[0][1]==str[3][1]&&str[0][1]==str[4][1])
                return true;
            else
                return false;
        }

        public boolean Same_color_white()       //判断white方是否为同花
        {
            if(str[5][1]==str[6][1]&&str[5][1]==str[7][1]&&str[5][1]==str[8][1]&&str[5][1]==str[9][1])
                return true;
            else
                return false;
        }

        private void sorting_black()            //将black方的手牌排序
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

        private void sorting_white()            //将white方的手牌排序
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

        public boolean Straight_black()         //判断black方是否为顺子
        {
            if(str[0][0]==str[1][0]-1&&str[0][0]==str[2][0]-2&&str[0][0]==str[3][0]-3&&str[0][0]==str[4][0]-4)
                return true;
            else
                return false;
        }

        public boolean Straight_white()         //判断white方是否为顺子
        {
            if(str[5][0]==str[6][0]-1&&str[5][0]==str[7][0]-2&&str[5][0]==str[8][0]-3&&str[5][0]==str[9][0]-4)
                return true;
            else
                return false;
        }

        public int Poker_types_black()          //判断black方手牌的牌型
        {

            if(Same_color_black()&&Straight_black())    //同花顺，返回数字99
                return 99;
            else if(Same_color_black())                 //同花，返回数字88
               return 88;
            else if(Straight_black())                   //顺子，返回数字77
                return 77;

            int[] num = new int[5];                     //不是同花也不是顺子的情况，用数组统计某大小的牌出现次数
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
                case 5:                             //总数为5，散牌
                {
                    return 5;
                }
                case 7:                             //总数为7，一幅对子，三张散牌
                {
                    int i;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==2)
                            break;
                    }
                    Pair_black=str[i][0];           //记录对子的大小
                    return 7;
                }
                case 9:                             //总数为9，双对
                {
                    int i;
                    char temp;
                    for(i=0;i<5;i++)
                    {
                        if(num[i]==2)
                            break;
                    }
                    Double_pair_black_min=str[i][0];    //记录双对中较小的对子
                    for(;i<5;i++)
                    {
                        if(num[i]==2&&str[i][0]!=Double_pair_black_min)
                            break;
                    }
                    temp=str[i][0];                     //记录双对中较大的对子
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
                case 11:                                //11，一幅三条，两张散牌
                case 13:                                //13，一幅三条，一幅对子
                case 17:                                //17，四张大小一样的牌，一张单牌（一幅三条，两张散牌）
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

        public int Comparators()                //判断胜负
        {
            if(Poker_types_black()>Poker_types_white())     //当black和white牌型不同时直接判断胜负
                return 1;
            else if(Poker_types_black()<Poker_types_white())
                return -1;
            else
            {
                switch(Poker_types_black())                 //散牌时从大到小比较
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

