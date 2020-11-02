package com.databases;

import com.java.common.GlobalParams;
import com.databases.DatasetsUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class DatasetsTest {
    public static void main(String[] args) {
        HashMap<String, String> ec = new HashMap<>();
        ec.put("id","int");
        ec.put("name", "str");
        ec.put("chnname", "int");
        ec.put("cast", "str");
        ec.put("year", "int");
        ec.put("region", "str");
        ec.put("type", "str");
        ec.put("viewed", "str");
        ec.put("want_to_review", "str");

        DatasetsUtils.readExcel(GlobalParams.excelDatasets, "movie", true, ec);
//        readExcel(GlobalParams.excelDatasets, "movie", false);
    }

}
