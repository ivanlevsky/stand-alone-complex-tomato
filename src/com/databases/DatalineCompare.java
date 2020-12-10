package com.databases;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class DatalineCompare {
    public static void main(String[] args) throws IOException {
        File tf1 = new File("C:\\Users\\ivanovsky\\Desktop\\nds list1.txt");
        File tf2 = new File("C:\\Users\\ivanovsky\\Desktop\\nds list2.txt");
        ArrayList<String> text1 = (ArrayList<String>) Files.readLines(tf1, Charset.forName("GBK"));
        ArrayList<String> text2 = (ArrayList<String>) Files.readLines(tf2, Charset.forName("GBK"));

        diffFunc1((ArrayList<String>) text1.clone(), (ArrayList<String>) text2.clone());
        diffFunc2((ArrayList<String>) text1.clone(), (ArrayList<String>) text2.clone());
    }
    private static void diffFunc2(ArrayList<String> list1, ArrayList<String> list2){
        Long startTime = System.currentTimeMillis();
        ArrayList<String> error = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {
            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(j).equals(list2.get(i))) {
                    break;
                }else {
                    if((j == list2.size() -1)){
                        error.add("line: " + i + " , " + list2.get(i));
                    }
                }
            }
        }
        for (String s : error) {
            System.out.println(s);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("func2 calculate time:" + (endTime - startTime));
    }

    private static void diffFunc1(ArrayList<String> list1, ArrayList<String> list2) {
        Long startTime = System.currentTimeMillis();
        ArrayList<String> error = (ArrayList<String>) list2.clone();
        for (int i = list1.size() - 1; i >= 0; i--) {
            for (int j = list2.size() - 1; j >= 0; j--) {
                if (list2.get(j).equals(list1.get(i))) {
                    list2.remove(j);
                    list1.remove(i);
                    break;
                }
            }
        }
        for (String s : list2) {
            System.out.println("line: " + error.indexOf(s) + " , " + s);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("func1 calculate time:" + (endTime - startTime));
    }
}
