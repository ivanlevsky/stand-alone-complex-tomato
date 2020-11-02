package com.databases;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class DatasetsUtils {
    public static void readExcel(String excelFile, String sheetName, boolean removeHeader
            , HashMap<String,String> excelConverter
    ){
        try {
            InputStream inp = new FileInputStream(excelFile);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheet(sheetName);
            int rows = sheet.getLastRowNum();
            int startRow = removeHeader?1:0;
            int cols = sheet.getRow(0).getLastCellNum();
            HashMap<Integer, String> colType = new HashMap<>();
            if(excelConverter.size() > 0){
                for (int i = 0; i < cols; i++) {
                    colType.put(i,
                            excelConverter.get(sheet.getRow(0).getCell(i).toString()));
                }
            }
            excelConverter.clear();
            StringBuilder temp;
            DataFormatter dataFormatter = new DataFormatter();

            for (int i = startRow; i <= rows; i++) {
                temp = new StringBuilder();
                for (int j = 0; j < cols; j++) {
                    if(colType.get(j).equals("str")){
                        temp.append(dataFormatter.formatCellValue(sheet.getRow(i).getCell(j))).append(";");
                    }else {
                        temp.append(sheet.getRow(i).getCell(j)).append(";");
                    }
                }
                System.out.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
