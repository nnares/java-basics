package com.nishchay.ds.string;

import java.util.Arrays;

/*
* Reverse a String word wise - reverse the string without reversing its individual words
*       str = i like this program very much
*       Output: much very program this like i
*
* */
public class StringArrayReverse {

    public static void main(String[] args) {

        String str = "i like this program very much";
//      Output: much very program this like i

        String revStr = strArrayReverse(str);
        System.out.println("Original String : " + str);
        System.out.println("Reverse String  : " + revStr);

    }

    private static String strArrayReverse(String str) {

        String strArr[] = str.split(" ");

        String t;
        int i, j;
        i = 0;
        j = strArr.length - 1;
        while (i < j) {
            // swap ith & jth element
            t = strArr[i];
            strArr[i] = strArr[j];
            strArr[j] = t;
            i++;
            j--;
        }

        // Convert array of strings into a string in Java 8
        String revStr = String.join(" ", strArr);
        return revStr;
    }

}