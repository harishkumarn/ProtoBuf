package com.example;
import com.harish.proto.*;
import com.harish.proto.QA.Type;

import java.util.ArrayList;

import org.json.JSONObject;

public class App 
{
    public static void main( String[] args )
    {


	    Employee dev = Employee.newBuilder()
            .setName("Harish Kumar")
            .setAge(25)
            .setDob("12-01-1998")
            .setRole("MTS")
            .setDev(Developer.newBuilder().addAllTechSkills(
                new ArrayList<String>(){{
                    add("Java");
                    add("JS");
                    add("Python");
                    add("C");
                    add("C++");
                    add("Scala");
                }})
            )
            .build();


        Employee pm = Employee.newBuilder()
            .setName("Nithya")
            .setAge(25)
            .setDob("28-10-1997")
            .setRole("PM")
            .setPm(PM.newBuilder().addSkills("Product management"))
            .build();
        
        Employee qa = Employee.newBuilder()
            .setName("Dave")
            .setAge(25)
            .setDob("21-11-1997")
            .setRole("QA")
            .setQa(QA.newBuilder().setSkill(Type.MANUAL))
            .build();

        System.out.println( dev);
        System.out.println( pm);
        System.out.println( qa);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", "Harish Kumar");
        jsonObject.put("age", 25);
        jsonObject.put("dob", "12-01-1998");
        jsonObject.put("role", "MTS");

        System.out.println( jsonObject);
        
    }
}
