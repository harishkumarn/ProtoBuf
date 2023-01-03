package com.example;
import com.harish.proto.Employee; 

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
    }
}
