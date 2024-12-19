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

}
