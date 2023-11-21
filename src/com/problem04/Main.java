package com.problem04;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

import static com.problem04.Palindrome.isPalindrome;
public class Main {
    public static String data;
    public static void main(String[] args){
        try{
            String path = "com/problem04/input.txt";
            File f = new File(path);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                data = sc.nextLine();
                System.out.println("Text \""+data+"\" is palindrome word? "+isPalindrome(data));
            }
        }catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
        }


    }
}
