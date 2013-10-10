package com.example.BasicUI.model;

import java.io.Serializable;

/**
 * User: anhnt
 * Date: 10/10/13
 * Time: 11:43 AM
 */
public class Contact implements Serializable
{
    private String name;
    private String phone;


    public Contact()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Name:" + name + "\n" + "Phone: " + phone;
    }
}
