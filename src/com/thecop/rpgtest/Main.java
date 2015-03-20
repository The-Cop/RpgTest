package com.thecop.rpgtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Msven test!");
        System.out.print("Enter something:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int c;
            while((c = br.read()) != -1) {
                char character = (char) c;
                System.out.println("Entered: "+character);
                if(character=='q'){
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
