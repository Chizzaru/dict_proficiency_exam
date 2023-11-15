package com.problem02;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ISBN {

    public static int number_of_records, valid, invalid;

    public static List<String> readFile(String path){
        List<String> isbn = new ArrayList<>();
        try{
            File f = new File(path);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                number_of_records += 1;
                String data = sc.nextLine();
                String filtered_data = filter(data);
                if(!filtered_data.isEmpty()){
                    int len = filtered_data.length();
                    if(len == 10){
                        // Check if divisible by 11
                        int multiplier = 10;
                        int sum = 0;
                        for(int i = 0; i < filtered_data.length(); i++){
                            String number = (filtered_data.charAt(i) == 'X') ? "10" : String.valueOf(filtered_data.charAt(i));
                            sum += multiplier * Integer.parseInt(number);
                            multiplier -= 1;
                        }

                        if((sum % 11) == 0){
                            isbn.add(filtered_data);
                        }else{
                            invalid += 1;
                        }
                    }else{
                        invalid += 1;
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        valid += isbn.size();
        return isbn;
    }

    public static String filter(String txt){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < txt.length();i++){
            String c = String.valueOf(txt.charAt(i));
            byte[] ascii = c.getBytes(StandardCharsets.US_ASCII);
            for(byte b:ascii) {
                if ((b >= 48 && b <= 57) || b == 88 || b == 120) sb.append(c);
            }
        }
        if(sb.isEmpty()) invalid += 1;
        return sb.toString();
    }

}
