package com.example.orderservice.controller;

import com.example.orderservice.service.ExcelProcessorOriginalService;
import com.example.orderservice.service.WordFrequencyOriginalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/excelProcess")
public class ExcelProcessorOriginalController {

    @Autowired
    ExcelProcessorOriginalService excelProcessorOriginalService;
    @Autowired
    com.example.orderservice.service.WordFrequencyOriginalService WordFrequencyOriginalService;


    @PostMapping("/word")
    public String readAndWrite() throws IOException {
        WordFrequencyOriginalService.readAndWrite();
        return "Words";
    }

    @PostMapping("/")
    public String excelProcessData(){
        return ExcelProcessorOriginalService.excelProcessData();
    }

}
