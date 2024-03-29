package com.databases;

import com.google.common.io.Files;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatasetsUtils {
    private final static String splitText = "S_P_L_I_T";
	private final static String sheetRowSplit = "R_S_P_L_I_T";
	private final static String sheetColumnSplit = "C_S_P_L_I_T";
	private final static String lineSplit = "L_S_P_L_I_T";

    public static void writeCSV(String csvFilePath, String data, boolean appendWrite){
        File csvFile = new File(csvFilePath);
        try {
            if(!csvFile.exists()){
                csvFile.createNewFile();
            }

            Files.write(data.replace(splitText,", ").getBytes("GBK"), csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeExcel(String excelFile, String sheetName, String data, boolean appendWrite){
        try {
            SXSSFWorkbook wb;
            if(appendWrite){
                XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(excelFile));
                wb = new SXSSFWorkbook(xwb, 100);
            }else {
                wb = new SXSSFWorkbook(100);
            }
            Sheet sh = wb.createSheet(sheetName);
            String[] rowDatas =data.split(lineSplit);
            int rowNum = rowDatas.length;
            int colNum = rowDatas[0].split(splitText).length;
            for(int i = 0; i < rowNum; i++){
                Row row = sh.createRow(i);
                for(int j = 0; j < colNum; j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowDatas[i].split(splitText, -1)[j]);
                }
            }
            FileOutputStream out = new FileOutputStream(excelFile);
            wb.write(out);
            out.close();
            wb.dispose();
			wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readExcel(String excelFile, String sheetName, boolean removeHeader
            , HashMap<String,String> excelConverter
    ){
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
            inp.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

	public static void mergeMultiSheetsToOne(String oriExcelFile, String newExcelFile){
		try {
			InputStream inp = new FileInputStream(oriExcelFile);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet tempSheet;
			StringBuilder allSheetString = new StringBuilder();
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				tempSheet = wb.getSheetAt(i);
				for (int j = 0; j <= tempSheet.getLastRowNum(); j++) {
					allSheetString.append(tempSheet.getRow(j).getCell(0) + sheetRowSplit);
				}
				allSheetString.append(sheetColumnSplit);
			}
			inp.close();
			wb.close();

			SXSSFWorkbook swb = new SXSSFWorkbook();
			SXSSFSheet sh = swb.createSheet("Sheet1");
			sh.setRandomAccessWindowSize(-1);
			String[] columnSheetStrings = allSheetString.toString().split(sheetColumnSplit);
			int tempRowNum = 0;
			Row tempRow;
			for (int i = 0; i < columnSheetStrings.length; i++) {
				tempRowNum = columnSheetStrings[i].split(sheetRowSplit).length;
				for (int j = 0; j < tempRowNum; j++) {
					if(sh.getRow(j) == null) {
						sh.createRow(j).createCell(i).setCellValue(columnSheetStrings[i].split(sheetRowSplit)[j]);
					}else {
						sh.getRow(j).createCell(i).setCellValue(columnSheetStrings[i].split(sheetRowSplit)[j]);
					}
				}
			}

			FileOutputStream out = new FileOutputStream(newExcelFile);
			swb.write(out);
			out.close();
			swb.dispose();
			swb.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static int[] getMergedCellRegionIndex(String excelFile, String sheetName, String cellContent){
		int[] cellRegionIndex = new int[4];
		try{
			InputStream inp = new FileInputStream(excelFile);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheet(sheetName);
			int rows = sheet.getLastRowNum();
			int startRow = 0;
			int cols = sheet.getRow(0).getLastCellNum();
			DataFormatter dataFormatter = new DataFormatter();
			for (int i = startRow; i <= rows; i++){
				for (int j = 0; j <= cols; j++){
					if(dataFormatter.formatCellValue(sheet.getRow(i).getCell(j)).equals(cellContent)){
						for(CellRangeAddress region : sheet.getMergedRegions()){
							if(region.isInRange(sheet.getRow(i).getCell(j))){
								cellRegionIndex[0] = region.getFirstColumn();
								cellRegionIndex[1] = region.getLastColumn();
								cellRegionIndex[2] = region.getFirstRow();
								cellRegionIndex[3] = region.getLastRow();
							}
						}
					}
				}
			}
			inp.close();
			wb.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		return cellRegionIndex;
	}
}
