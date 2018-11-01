package com.nuaa.compliation.base;

import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.exception.GrammarPhaseException;

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


    public String[][] getStrEdges(ModelType modelType) {
        if (modelType == ModelType.LeftToNf) {
            return leftGraph.getStrEdges();
        } else if (modelType == ModelType.RightToNf) {
            return rightGraph.getStrEdges();
        }
        return null;
    }


    public boolean isChar(char c) {
        return !Character.isDigit(c);
    }

    public boolean isInt(char c) {
        return Character.isDigit(c);
    }

    public void clearGraph(ModelType modelType) {
        if (modelType == ModelType.LeftToNf) {
            leftGraph.clearGraph();
        } else if (modelType == ModelType.RightToNf) {
            rightGraph.clearGraph();
        }
    }


    public abstract void addEdges(String startNode, String expression, ModelType modelType) throws GrammarPhaseException;


}
