package com.goodlogic.a19_recyclerview;

public class User {
    public static final String KEY_ID = "key_id";
    public static final String KEY_NAME = "key_name";
    public static final String KEY_PROF = "key_profess";

    public long id;
    public String name;
    public String profession;

    public User(long id,String name,String profession){
        this.id = id;
        this.name = name;
        this.profession = profession;
    }
}
