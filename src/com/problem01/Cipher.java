package com.problem01;

import java.lang.StringBuilder;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Cipher {

    /**
     * Getting the Alphabets
     * @return Alphabets Lowercase and Uppercase
     */
    public static String getCharacters(){
        StringBuilder sb = new StringBuilder();
        // All Lowercase letters
        for(char ascii = 97; ascii <= 122; ascii++) sb.append(ascii);
        // All Uppercase letters
        for(char ascii = 65; ascii <= 90; ascii++) sb.append(ascii);

        return sb.toString();
    }

    /**
     * Encryption
     * @param shift Number of shift to the right
     */
    public static void encrypt(int shift){

        String characters = getCharacters();

        try{
            File f = new File("com/problem01/input.txt");
            FileWriter fw = new FileWriter("com/problem01/cipher.txt");
            Scanner sc = new Scanner(f);

            while(sc.hasNextLine()){
                StringBuilder sb = new StringBuilder();
                String data = sc.nextLine();
                String filtered_data = letterOnlyText(data);

                // Encryption
                for(int i = 0; i < filtered_data.length(); i++){
                    String c = String.valueOf(filtered_data.charAt(i));
                    int index = characters.indexOf(c);
                    int shift_index = ((index + shift) < characters.length()) ? (index + shift) : (index + shift) - characters.length();
                    if(shift_index < 10){
                        String enc = "0" + shift_index;
                        sb.append(enc);
                    }else{
                        sb.append(shift_index);
                    }
                }

                // Writing to cipher.txt
                if(!sb.isEmpty()) fw.write(sb + "\n");

            }
            // Closing the FileWriter
            fw.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Decryption
     * @param shift Number of shift to the left
     */
    public static void decrypt(int shift){
        String characters = getCharacters();
        try{
            File file = new File("com/problem01/cipher.txt");
            FileWriter fw = new FileWriter("com/problem01/output.txt");
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                StringBuilder sb = new StringBuilder();
                String data = sc.nextLine();
                String filtered_data = numberOnlyText(data);
                for(int i = 0; i < filtered_data.length(); i=i+2) {
                    String x = String.valueOf(filtered_data.charAt(i));
                    String y = String.valueOf(filtered_data.charAt(i+1));
                    int index = Integer.parseInt(x + y);
                    int shift_index = ((index - shift) < 0) ? characters.length() + (index - shift) : index - shift;
                    sb.append(characters.charAt(shift_index));
                }
                //System.out.println(sb);
                if(!sb.isEmpty()) fw.write(sb + "\n");
            }
            fw.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Filtering the text
     * @param txt Text in the text file
     * @return Filtered Text
     */
    public static String numberOnlyText(String txt){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < txt.length(); i++){
            String c = String.valueOf(txt.charAt(i));
            byte[] ascii_value = c.getBytes(StandardCharsets.US_ASCII);
            for(byte b:ascii_value){
                if(b >= 48 && b<=57){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Filtering the text
     * @param txt Text in the text file
     * @return Filtered text
     */
    public static String letterOnlyText(String txt){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < txt.length(); i++){
            String c = String.valueOf(txt.charAt(i));
            byte[] ascii_value = c.getBytes(StandardCharsets.US_ASCII);
            for(byte b: ascii_value){
                if((b >= 65 && b <= 90) || (b >= 97 && b <= 122)){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
