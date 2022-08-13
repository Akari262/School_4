package com.example.school_4;

public class Children {
    private int num;
    private String name;
    private String clas;
    private String adres;
    private String harch;
    private String parents_name;
    private String phone_num;
    private String doc;
    private String category;

    public Children(int num, String name, String clas, String adres, String harch, String parents_name,String phone_num, String doc, String category){
        this.num = num;
        this.name = name;
        this.harch = harch;
        this.clas = clas;
        this.adres = adres;
        this.parents_name = parents_name;
        this.phone_num = phone_num;
        this.doc = doc;
        this.category = category;
    }


    public int getNum(){return num;}

    public void setNum(int num){this.num = num;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getHarch(){return harch;}

    public void setHarch(String harch){this.harch = harch;}

    public String getClas(){return clas;}

    public void setClas(String clas){this.clas = clas;}

    public String getAdres(){return adres;}

    public void setAdres(String adres){this.adres = adres;}

    public String getParents_name(){return parents_name;}

    public void setParents_name(String parents_name){this.parents_name = parents_name;}

    public String getPhone_num(){return phone_num;}

    public void setPhone_num(String phone_num){this.phone_num = phone_num;}

    public String getDoc(){return doc;}

    public void setDoc(String doc){this.doc = doc;}

    public String getCategory(){return category;}

    public void setCategory(String category){this.category = category;}

}
