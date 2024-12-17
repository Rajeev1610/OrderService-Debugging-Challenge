
package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.GraphRequest;
import com.example.inventoryservice.service.DijkstraAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    DijkstraAlgorithmService dijkstraAlgorithmService;


    @PostMapping("/shortest-path")
    public Map<String, Object> findShortestPath(@RequestBody GraphRequest request) {
        return dijkstraAlgorithmService.findShortestPath(request.getNodes(),request.getEdges(),request.getStart(),request.getEnd());
    }
}
