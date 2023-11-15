package com.problem02;

import static com.problem02.ISBN.readFile;
import static com.problem02.ISBN.number_of_records;
import static com.problem02.ISBN.valid;
import static com.problem02.ISBN.invalid;


import java.util.List;

public class Main {

    public static void main(String[] args){
        String path = "com/problem02/input.txt";
        List<String> isbn = readFile(path);
        System.out.println("Summary Report");
        System.out.println("Valid \t\t: " + valid);
        System.out.println("Invalid \t: "+ invalid);
        System.out.println("Records \t: "+ number_of_records);

    }
}
