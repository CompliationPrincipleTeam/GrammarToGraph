package com.nuaa.compliation.bean;


import com.nuaa.compliation.enums.NodeType;
import com.nuaa.compliation.inter.IGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmrfcoder
 */
public class Graph implements IGraph {

    private List<Edge> edges;

    private List<Node> nodes;


    public Graph() {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }


    /**
     * 添加边，参数是Edge（边）
     *
     * @param edge
     */
    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);

    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }


    /**
     * 根据起点、终点、边值添加边
     *
     * @param startNode
     * @param endNode
     * @param edgeValue
     */
    @Override
    public void addEdge(Object startNode, Object endNode, String edgeValue) {

        boolean startNodeflag = false;
        boolean endNodeflag = false;

        Node start = null, end = null;

        start = getNode(startNode);


        end = getNode(endNode);


        Edge edge = new Edge(start, end, edgeValue);
        edges.add(edge);
    }

    private Node getNode(Object node) {
        boolean startNodeflag = false;
        Node returnNode = null;
        if (node instanceof String) {
            for (Node itemNode : nodes) {
                if (itemNode.getValue().equals(node)) {
                    startNodeflag = true;
                    returnNode = itemNode;
                    return returnNode;
                }


            }
            if (!startNodeflag) {
                returnNode = new Node((String) node);
                nodes.add(returnNode);
            }
        } else if (node instanceof Node) {
            for (Node itemNode : nodes) {
                if (itemNode.equals(node)) {
                    startNodeflag = true;
                    returnNode = itemNode;
                    return returnNode;
                }
            }

            if (!startNodeflag) {
                returnNode = new Node(((Node) node).getValue(), ((Node) node).getNodeType());
                nodes.add(returnNode);
            }
        }
        return returnNode;
    }


    @Override
    public String toString() {
        String strGraph = "";
        for (Edge edge : edges) {
            strGraph = strGraph + edge.getStartNode() + "-(" + edge.getEdgeValue() + ")->" + edge.getEndNode() + "\n";
        }
        return strGraph;
    }


    @Override
    public String[][] getStrEdges() {
        String[][] tempEdges = new String[edges.size()][3];

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            tempEdges[i][0] = edge.getStartNode().getValue();
            tempEdges[i][1] = edge.getEdgeValue();
            tempEdges[i][2] = edge.getEndNode().getValue();
        }


        return tempEdges;


    }

    @Override
    public void clearGraph() {
        edges.clear();
        nodes.clear();
    }

    @Override
    public void updateChuTai(String[] chuTai) {
        for (Node node : nodes) {
            for (String itemChuTai : chuTai) {
                if (node.getValue().equals(itemChuTai)) {
                    node.setNodeType(NodeType.startNode);
                }
            }
        }
    }

    @Override
    public void updateZhongTai(String[] zhongTai) {
        for (Node node : nodes) {
            for (String itemChuTai : zhongTai) {
                if (node.getValue().equals(itemChuTai)) {
                    node.setNodeType(NodeType.endNode);
                }
            }
        }
    }
}
