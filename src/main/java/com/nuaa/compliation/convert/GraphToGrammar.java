package com.nuaa.compliation.convert;

import com.nuaa.compliation.bean.Edge;
import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Graph;

import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class GraphToGrammar {


    /**
     * @param graph
     *
     *  Graph graph1=new Graph();
     *
     *  graph1.addEdge("A","C","0");
     */
    private void graphToLeftGrammar(Graph graph) {

        List<Edge> edges = graph.getEdges();


        Grammar grammar=new Grammar();


        Graph graph1=new Graph();

       graph1.addEdge("A","C","0");
        for (Edge edge:edges){
            //edge-->expression

        }


    }

    private void graphToRightGrammar(Graph graph) {

    }



}

