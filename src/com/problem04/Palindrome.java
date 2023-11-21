package com.problem04;

import java.io.File;
import java.io.IOException;

import java.util.Scanner;

import java.lang.StringBuilder;

public class Palindrome {

    public static boolean isPalindrome(String txt){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < txt.length(); i++){
            sb.insert(0, txt.charAt(i));
        }

        return (txt.toLowerCase()).contentEquals(sb.toString().toLowerCase());
    }
}
