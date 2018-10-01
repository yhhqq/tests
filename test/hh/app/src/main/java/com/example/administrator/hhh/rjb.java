package com.example.administrator.hhh;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/3/003.
 */

public class rjb implements Serializable{
    String title,time,id,content;
    public String getContent(){return content;}
    public void setContent(String content){this.content=content;}
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }

}


