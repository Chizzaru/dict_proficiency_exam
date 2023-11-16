package com.problem03;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;


public class Krissy {

    public static void compute(String path){

        // To use a lambda expression in a method, the method should have a parameter with a single-method interface as its type.
        SortedOctal octal = (s) -> {
            ArrayList<Integer> o = new ArrayList<>();
            for(int i=0; i<s.length();i++){
                if(s.charAt(i) != '-') o.add(Integer.parseInt(String.valueOf(s.charAt(i))));
            }

            Collections.sort(o);
            StringBuilder sb = new StringBuilder();
            for(Integer integer:o) sb.append(integer);

            return Integer.parseInt(sb.toString());
        };

        Checker checker = (o) -> {
            String oct = Integer.toString(o).replace("-","");
            for(int i=0; i < oct.length(); i++){
                if((i + 1) < oct.length()){
                    if(Integer.parseInt(String.valueOf(oct.charAt(i))) > Integer.parseInt(String.valueOf(oct.charAt(i + 1)))){
                        return false;
                    }
                }
            }
            return true;
        };

        Octal getOctal = (d) ->{
            String s = "";
            int init = d;
            while(!(init < 8)){
                s = String.valueOf((init % 8)).concat(s);
                init /= 8;
            }
            s = String.valueOf(init).concat(s);
            return new BigInteger(s).intValue();
        };

        try{
            File f = new File(path);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                int dec = Integer.parseInt(sc.nextLine());

                boolean sorted = false;
                while(!sorted){
                    //int oct = Integer.parseInt(Integer.toOctalString(dec));
                    int oct = getOctal.convert(dec);
                    int sorted_oct = octal.sort(String.valueOf(oct));
                    int diff = dec - sorted_oct;
                    //int diff_oct = Integer.parseInt(Integer.toOctalString(diff));
                    int diff_oct = getOctal.convert(diff);
                    if(checker.is_sorted(diff_oct)){
                        System.out.println("Octal : "+ diff_oct+ "\t\tDecimal : "+diff);
                        sorted = true;
                    }else{
                        dec = diff_oct;
                    }
                }
            }
        }catch(Exception ex){
            Logger.getLogger(Krissy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
