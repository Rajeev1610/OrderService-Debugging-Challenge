
package com.example.inventoryservice.model;

import java.util.List;

public class GraphRequest {
    private List<String> nodes;  // road junction point
    private List<int[]> edges;  //connection between two nodes  nodes and edges should not be same
    private String start;
    private String end;

    public List<String> getNodes() { return nodes; }
    public void setNodes(List<String> nodes) { this.nodes = nodes; }

    public List<int[]> getEdges() { return edges; }
    public void setEdges(List<int[]> edges) { this.edges = edges; }

    public String getStart() { return start; }
    public void setStart(String start) { this.start = start; }

    public String getEnd() { return end; }
    public void setEnd(String end) { this.end = end; }
}
