package com.nuaa.compliation.inter;


import com.nuaa.compliation.bean.Edge;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 * <p>
 * Graph的接口
 */
public interface IGraph {


    void addEdge(Edge edge);


    void addEdge(Object startNode, Object endNode, String edgeValue);

    String[][] getStrEdges();


    void clearGraph();

}
