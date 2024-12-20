package com.example.orderservice.controller;

import com.example.orderservice.service.ExcelProcessorOriginalService;
import com.example.orderservice.service.PrimeSumCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/excelProcess")
public class ExcelProcessorOriginalController {

    @Autowired
    ExcelProcessorOriginalService excelProcessorOriginalService;
    @Autowired
    com.example.orderservice.service.WordFrequencyOriginalService WordFrequencyOriginalService;

    @Autowired
    PrimeSumCalculatorService primeSumCalculator;
    @PostMapping("/word")
    public String readAndWrite() throws IOException {
        WordFrequencyOriginalService.readAndWrite();
        return "Words";
    }
    @PostMapping("/calculate")
    public String primeCalculation() throws ExecutionException, InterruptedException {
        primeSumCalculator.primeCalculation();
        return primeSumCalculator.primeCalculation();
    }
    @PostMapping("/")
    public String excelProcessData(){
        return ExcelProcessorOriginalService.excelProcessData();
    }

}
