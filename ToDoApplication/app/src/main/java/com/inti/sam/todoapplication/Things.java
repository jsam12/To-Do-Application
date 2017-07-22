package com.inti.sam.todoapplication;

/**
 * Created by User on 22/7/2017.
 */

public class Things {

    private int _id;  //primary key is reference first "_" in java
    private String _thingsName;

    public Things(){  // an empty constructor for when to create an object if needed
    }

    public Things(String thingsName){ //whatever isinput into _thingsname will be privated
        this._thingsName = thingsName;
    }

    public void set_id(int _id) { //setter is generated for both _id and _thingsName
        this._id = _id;
    }

    public void set_thingsName(String _thingsName) {
        this._thingsName = _thingsName;
    }

    public int get_id() {  //getter is generated for when to retrieve the property
        return _id;
    }

    public String get_thingsName() {
        return _thingsName;
    }

}
