package com.thecop.rpgtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        defenceCurveTest();

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

    private static void defenceCurveTest(){
        double defConst=0.04;
        for (int i=-10;i<=1000;){
            double def = (i*defConst)/(1+i*defConst);
            System.out.println("Def="+i+"\t "+def);
            if(i<20) {
                i = i + 5;
            }
            else if(i<100){
                i=i+10;
            }
            else{
                i=i+100;
            }
        }
    }
}
