package com.nuaa.compliation.base;

import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.enums.ModelType;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public abstract class BaseConvertGrammarToGraph {
    public Graph leftGraph;
    public Graph rightGraph;

    public BaseConvertGrammarToGraph() {
        leftGraph = new Graph();
        rightGraph = new Graph();
    }

    public abstract Graph GrammarToGraph(Grammar leftGrammar);

    public abstract void addEdges(String startNode, String expression, ModelType modelType);

    public String[][] getStrEdges(ModelType modelType) {
        if (modelType == ModelType.LeftToNf) {
            return leftGraph.getStrEdges();
        } else if (modelType == ModelType.RightToNf) {
            return rightGraph.getStrEdges();
        }
        return null;
    }
}
