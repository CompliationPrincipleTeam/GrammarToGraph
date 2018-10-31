package com.nuaa.compliation.convert;

import com.nuaa.compliation.base.BaseConvertGrammarToGraph;
import com.nuaa.compliation.bean.Constant;
import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Node;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.enums.NodeType;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class GrammarToGraph extends BaseConvertGrammarToGraph {


    @Override
    public Graph GrammarToGraph(Grammar leftGrammar) {
        return null;
    }


    @Override
    public void addEdges(String node, String expression, ModelType modelType) {
        String[] split = expression.split("\\|");

        switch (modelType) {
            case LeftToNf:
                leftGrammarAddEdges(node, split);
                break;
            case RightToNf:
                rightGrammarAddEdges(node, split);
            default:
                break;

        }


    }


    /**
     * 将左正规文法对应的表达式解析为若干条边
     * <p>
     * 通过操作leftGraph来添加边
     *
     * @param endNode
     * @param split
     */
    private void leftGrammarAddEdges(String endNode, String[] split) {

        for (String item : split) {
            if (item.length() == 1) {
                leftGraph.addEdge(new Node(Constant.startNodeName, NodeType.startNode), endNode, String.valueOf(item.charAt(0)));


            } else if (item.length() == 2) {

                leftGraph.addEdge(String.valueOf(item.charAt(0)), endNode, String.valueOf(item.charAt(1)));
            }
        }


    }

    /**
     * 将右正规文法对应的表达式解析为若干条边
     * 通过操作rightGraph来添加边
     *
     * @param node
     * @param split
     */
    private void rightGrammarAddEdges(String node, String[] split) {

    }

}
