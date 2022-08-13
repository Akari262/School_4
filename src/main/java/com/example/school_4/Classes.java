package com.example.school_4;

public class Classes {
    private int num;
    private String classes;
    private int num_d;
    private int num_s;

    public Classes(int num, String classes, int num_d, int num_s){
        this.num = num;
        this.classes = classes;
        this.num_d = num_d;
        this.num_s = num_s;
    }

    public int getNum(){return num;}

    public void setNum(int num){this.num = num;}

    public String getClasses(){return classes;}

    public void setClasses(String classes){this.classes = classes;}

    public int getNum_d(){return num_d;}

    public void setNum_d(int num_d){this.num_d = num_d;}

    public int getNum_s(){return num_s;}

    public void setNum_s(int num_s){this.num_s = num_s;}

}
