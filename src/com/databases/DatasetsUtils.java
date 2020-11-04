package com.databases;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class DatasetsUtils {
    public static ArrayList<String> readExcel(String excelFile, String sheetName, boolean removeHeader
            , HashMap<String,String> excelConverter
    ){
        String splitText = "S_P_L_I_T";
        ArrayList<String> result = new ArrayList<>();
        try {
            InputStream inp = new FileInputStream(excelFile);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheet(sheetName);
            int rows = sheet.getLastRowNum();
            int startRow = removeHeader?1:0;
            int cols = sheet.getRow(0).getLastCellNum();
            HashMap<Integer, String> colType = new HashMap<>();
            if(excelConverter != null){
                for (int i = 0; i < cols; i++) {
                    colType.put(i,
                            excelConverter.get(sheet.getRow(0).getCell(i).toString()));
                }
            }
            StringBuilder temp;
            DataFormatter dataFormatter = new DataFormatter();

            for (int i = startRow; i <= rows; i++) {
                temp = new StringBuilder();
                for (int j = 0; j < cols; j++) {
                    if(colType.size()>0) {
                        if (colType.get(j).equals("str")) {
                            temp.append(dataFormatter.formatCellValue(sheet.getRow(i).getCell(j))).append(splitText);
                        } else {
                            temp.append(sheet.getRow(i).getCell(j)).append(splitText);
                        }
                    }else {
                        temp.append(dataFormatter.formatCellValue(sheet.getRow(i).getCell(j))).append(splitText);
                    }
                }
                result.add(temp.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
