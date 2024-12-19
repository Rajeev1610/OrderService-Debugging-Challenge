package com.example.orderservice.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import java.io.*;


@Service
public class ExcelProcessorOriginalService {

    public static String excelProcessData() {
        String inputFile = "C:\\Users\\POReddy\\sample_data_200k.xlsx";
        String outputFile = "C:\\Users\\POReddy\\sample_data_200k_output18.xlsx";

        try (FileInputStream fis = new FileInputStream(inputFile);
            Workbook input = new XSSFWorkbook(fis);
            SXSSFWorkbook output = new SXSSFWorkbook(100)){
            Sheet inputSheet = input.getSheetAt(0);
            Sheet outputSheet = output.createSheet("Processed Data");
            /*Iterator<Row> rowIterator = inputSheet.iterator();*/
            int rowIndex = 0;
            for(Row inputRow:inputSheet){

                Row outputRow = outputSheet.createRow(rowIndex++);
                int lastColumn = inputRow.getLastCellNum();
                for(int i =0;i<lastColumn;i++){
                    Cell inputCell = inputRow.getCell(i,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    Cell outputCell = outputRow.createCell(i);
                    outputCell.setCellValue(inputCell.toString());
                }
                Cell firstCell = inputRow.getCell(0,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String reversedValue = new StringBuilder(firstCell.toString()).reverse().toString();
                outputRow.createCell(lastColumn).setCellValue(reversedValue);
            }
            try(FileOutputStream fos = new FileOutputStream(outputFile)){
                output.write(fos);
            }
            output.dispose();
            return "Processing done!";
        } catch (IOException e){
            e.printStackTrace();
            return "Error while processing the file: " + e.getMessage();
        }

    }

  /*  private static List<String[]> readExcelFile(String filePath) throws IOException {
        *//*BufferedReader br = new BufferedReader(new FileReader(filePath));
        List<String[]> data = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            data.add(line.split(","));
        }
        return data;*//*
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)){
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet){
               int lastColumn = row.getLastCellNum();
               String[] rowData = new String[lastColumn];
               for(int i = 0; i<lastColumn;i++){
                  Cell cell = row.getCell(i,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                  rowData[i] = cell.toString();
               }
               data.add(rowData);
            }
        }
        return data;
    }

    private static List<String[]> processData(List<String[]> data) {
        List<String[]> processedData = new ArrayList<>();
        for (String[] row : data) {
            String[] newRow = new String[row.length+1];
            System.arraycopy(row,0,newRow,0,row.length);
            if(row.length > 0 && row[0] != null) {
                newRow[row.length] = new StringBuilder(row[0]).reverse().toString();
            }else{

                newRow[row.length] = "";
            }
            processedData.add(newRow);
        }
        return processedData;
       *//* return data.parallelStream().map(row->{
            String[] newRow = new String[row.length+1];
            System.arraycopy(row,0,newRow,0,row.length);
            newRow[row.length] =(row.length>0 && row[0] != null) ? new StringBuilder(row[0]).reverse().toString():"";
            return newRow;
        }).collect(Collectors.toList());*//*
    }

    private static void writeExcelFile(String filePath, List<String[]> data) throws IOException {
        try(Workbook workbook = new XSSFWorkbook();
        FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("Processed Data");
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i);
                String[] rowData = data.get(i);
                for (int j = 0; j < rowData.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(rowData[j]);
                }
            }
            workbook.write(fos);
        }

    }*/

}
