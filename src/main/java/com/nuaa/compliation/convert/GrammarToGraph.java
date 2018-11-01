package com.nuaa.compliation.convert;

import com.nuaa.compliation.base.BaseConvertGrammarToGraph;
import com.nuaa.compliation.bean.*;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.enums.NodeType;
import com.nuaa.compliation.exception.GrammarPhaseException;

import java.util.ArrayList;
import java.util.List;

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
    public void addEdges(String node, String expression, ModelType modelType) throws GrammarPhaseException {
        String[] split = expression.split("\\|");

        switch (modelType) {
            case LeftToNf:
                try {
                    leftGrammarAddEdges(node, split);
                } catch (GrammarPhaseException e) {
                    throw e;
                }

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
    private void leftGrammarAddEdges(String endNode, String[] split) throws GrammarPhaseException {

        List<Edge> edges = new ArrayList<>();

        for (String item : split) {
            if (item.length() == 1) {
                char itemZero = item.charAt(0);
                if (isInt(itemZero)) {

                    leftGraph.addEdge(new Node(Constant.startNodeName, NodeType.startNode), endNode, String.valueOf(itemZero));
                } else {

                    throw new GrammarPhaseException(item + "格式非法！");
                }


            } else if (item.length() == 2) {
                char itemZero = item.charAt(0);
                char itemFirst = item.charAt(1);

                if (isChar(itemZero) && isInt(itemFirst)) {
                    leftGraph.addEdge(String.valueOf(itemZero), endNode, String.valueOf(itemFirst));
                } else {
                    throw new GrammarPhaseException(item + "不是合法的左文法表达式！");

                }


            }
        }


    }


    /**
     * 将右正规文法对应的表达式解析为若干条边
     * 通过操作rightGraph来添加边
     *
     * @param node
     * @param split
     * example: 某产生式为  D-> 0A|0B|0C|0
     * node  = D
     * split = {"0","B0","C0","0"}
     * 
     */
    private void rightGrammarAddEdges(String node, String[] split) {
    	//public Node(String value, NodeType nodeType)
    	//public void addEdge(Object startNode, Object endNode, String edgeValue) 
    	Node endNode = new Node(Constant.endNodeName,NodeType.endNode);
    	for(String s:split) {
    		if(s.length()==1)
    		{
    			rightGraph.addEdge(node, endNode, String.valueOf(s.charAt(0)));
    		}
    		else
    		{	int len = s.length();
    			rightGraph.addEdge(node, String.valueOf(s.charAt(len-1)), s.substring(0, len-1));
    		}
    			
    			
    	}
    }

}
