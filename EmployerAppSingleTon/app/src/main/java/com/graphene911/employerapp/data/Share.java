package com.graphene911.employerapp.data;

import com.graphene911.employerapp.model.Employee;

import java.util.ArrayList;

public class Share {

    public ArrayList<Employee> employeeList;

    private static Share share = null;

    public static Share getInstance(){
        if(share == null){
            share = new Share();
        }
        return share;
    }

    private Share(){
        employeeList = new ArrayList<>();
    }
}
