package com.example;
import com.harish.proto.Employee; 

import org.json.JSONObject;

public class App 
{
    public static void main( String[] args )
    {



	    Employee emp = Employee.newBuilder()
            .setName("Harish Kumar")
            .setAge(25)
            .setDob("12-01-1998")
            .setRole("MTS")
            .build();
        System.out.println( emp);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", "Harish Kumar");
        jsonObject.put("age", 25);
        jsonObject.put("dob", "12-01-1998");
        jsonObject.put("role", "MTS");

        System.out.println( jsonObject);
        
    }
}
