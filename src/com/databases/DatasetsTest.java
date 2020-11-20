package com.databases;

import com.java.common.GlobalParams;
import java.util.ArrayList;
import java.util.HashMap;

public class DatasetsTest {
    public static void main(String[] args) {
        readExcelWithConverter();
    }
    private static void readExcelWithConverter(){
        HashMap<String, String> ec = new HashMap<>();
        ec.put("id","int");
        ec.put("name", "str");
        ec.put("chnname", "str");
        ec.put("cast", "str");
        ec.put("year", "str");
        ec.put("region", "str");
        ec.put("type", "str");
        ec.put("viewed", "str");
        ec.put("want_to_review", "str");
        ArrayList<String> data;
        data = DatasetsUtils.readExcel(GlobalParams.excelDatasets, "movie", true, ec);
        for (String s: data) {
            System.out.println(s);
        }
        ec.put("year","int");
        data = DatasetsUtils.readExcel(GlobalParams.excelDatasets, "movie", false, ec);
        for (String s: data) {
            System.out.println(s);
        }
    }

}
