package com.nuaa.compliation.bean;

import com.nuaa.compliation.enums.NodeType;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class Node {
    private String value;
    private NodeType nodeType;



    public String getValue() {
        return value;
    }

    public Node(String value, NodeType nodeType) {
        this.value = value;
        this.nodeType = nodeType;
    }

    public Node(String value) {
        this.value = value;
        this.nodeType = NodeType.normalNode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }


    @Override
    public boolean equals(Object obj) {
        Node node = (Node) obj;
        return this.value.equals(node.getValue());
    }
}
