package com.example.orderservice.controller;

import com.example.orderservice.service.ExcelProcessorOriginalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excelProcess")
public class ExcelProcessorOriginalController {

    @Autowired
    ExcelProcessorOriginalService excelProcessorOriginalService;



    @PostMapping("/")
    public String excelProcessData(){
        return ExcelProcessorOriginalService.excelProcessData();
    }

}
