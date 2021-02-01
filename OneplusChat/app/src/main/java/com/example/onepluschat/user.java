package com.example.onepluschat;

public class user {
   String id,name,pic,num;

    public user()
    {
        
    }

    public user(String id, String name, String num, String pic) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
