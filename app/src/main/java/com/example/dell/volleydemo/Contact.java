package com.example.dell.volleydemo;

/**
 * Created by DELL on 12/28/2016.
 */

public class Contact
{
    private String user_pass;

    private String user_name;

    private String name;

    public Contact(String name,String user_name,String user_pass)
    {
        this.setName(name);
        this.setUser_name(user_name);
        this.setUser_pass(user_pass);
    }
    public String getUser_pass ()
    {
        return user_pass;
    }

    public void setUser_pass (String user_pass)
    {
        this.user_pass = user_pass;
    }

    public String getUser_name ()
    {
        return user_name;
    }

    public void setUser_name (String user_name)
    {
        this.user_name = user_name;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user_pass = "+user_pass+", user_name = "+user_name+", name = "+name+"]";
    }
}