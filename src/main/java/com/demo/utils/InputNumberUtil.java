package com.demo.utils;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class InputNumberUtil {
	
	static Logger logger = Logger.getLogger(InputNumberUtil.class);
    public static int returnInt() {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                int num = sc.nextInt();
                if (num > 0) {
                    return num;
                } else {
                    System.out.println("Number must be larger than 0");
                }
            } catch (Exception e) {
            	logger.error(e);
                System.out.println("invalid number");
            }

        } while (true);
    }
    public static float returnFloatVar() {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                float num = sc.nextFloat();
                if (num >= 0) {
                    return num;
                } else {
                    System.out.println("Number must be in range 0<=num<10");
                }
            } catch (Exception e) {
            	logger.error(e);
                System.out.println("invalid number");
            }

        } while (true);
    }
}
