package com.nuaa.compliation.bean;

/**
 * @author dmrfcoder
 * <p>
 * 边的javebean类，默认边为有向，startNode表示起点，endNode表示终点，edgeValue表示边的值
 * 即：startNode经过edgeValue到达endNode
 */
public class Edge {
    private Node startNode;
    private Node endNode;
    private String edgeValue;

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public String getEdgeValue() {
        return edgeValue;
    }

    public void setEdgeValue(String edgeValue) {
        this.edgeValue = edgeValue;
    }

    public Edge(Node startNode, Node endNode, String edgeValue) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.edgeValue = edgeValue;
    }



}
