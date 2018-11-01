package com.nuaa.compliation.convert;

import java.util.ArrayList;
import java.util.List;

import com.nuaa.compliation.base.BaseConvertGraphToGrammar;
import com.nuaa.compliation.bean.Edge;
import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.bean.Node;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.enums.NodeType;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class GraphToGrammar extends BaseConvertGraphToGrammar {


    public Grammar graphToGrammar(String[] chutai, String[] zhongtai, ArrayList<String[]> graphList, ModelType modelType) {

        switch (modelType) {
            case NfToRight:
                graphToRightGrammer(chutai, zhongtai, graphList);
                return rightGrammar;

            case NfToLeft:
                graphToLeftGrammer(chutai, zhongtai, graphList);
                return leftGrammar;
            default:
                break;
        }

        return null;
    }

    private void graphToRightGrammer(String[] chutai, String[] zhongtai, ArrayList<String[]> graphList) {

        rightGraph.clearGraph();

        rightGrammar.clearGrammar();

        for (String[] edge : graphList) {
            rightGraph.addEdge(edge[0], edge[2], edge[1]);
        }
        rightGraph.updateChuTai(chutai);
        rightGraph.updateZhongTai(zhongtai);


        List<Edge> edges = rightGraph.getEdges();
        List<Node> nodes = rightGraph.getNodes();

        //获取非终结符集
        List<String> vN = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            vN.add(nodes.get(i).getValue());
        }

        leftGrammar.setvN(vN);

        //获取终结符集
        List<String> vT = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            if (!vT.contains(edges.get(i).getEdgeValue())) {
                vT.add(edges.get(i).getEdgeValue());
            }
        }

        leftGrammar.setvT(vT);

        //获取开始符
        for (Node node : nodes) {
            if (node.getNodeType() == NodeType.startNode) {
                leftGrammar.setS(node.getValue());
                break;
            }
        }

        //获取右文法推导式集合
        List<String> p = new ArrayList<>();
        for (Node node : nodes) {
            p.add(node.getValue() + "->");
        }
        for (int i = 0; i < p.size(); i++) {
            for (Edge edge : edges) {
                if (edge.getStartNode().getValue().charAt(0) == p.get(i).charAt(0)) {
                    if (edge.getEndNode().getNodeType() == NodeType.endNode) {
                        p.set(i, p.get(i) + edge.getEdgeValue() + "|" + edge.getEdgeValue() + edge.getEndNode().getValue() + "|");
                    } else {
                        p.set(i, p.get(i) + edge.getEdgeValue() + edge.getEndNode().getValue() + "|");
                    }
                }
            }
            p.set(i, p.get(i).substring(0, p.get(i).length() - 1));
        }
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).length() <= 3) {
                p.remove(i);
            }
        }
        rightGrammar.setP(p);


    }


    private void graphToLeftGrammer(String[] chutai, String[] zhongtai, ArrayList<String[]> graphList) {
        leftGraph.clearGraph();

        leftGrammar.clearGrammar();

        if (graphList.size() == 0) {
            return;
        }

        for (String[] edge : graphList) {

            leftGraph.addEdge(edge[0], edge[2], edge[1]);
        }

        leftGraph.updateChuTai(chutai);
        leftGraph.updateZhongTai(zhongtai);


        List<Edge> edges = leftGraph.getEdges();
        List<Node> nodes = leftGraph.getNodes();
        //获取非终结符集
        List<String> vN = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            vN.add(nodes.get(i).getValue());
        }

        leftGrammar.setvN(vN);

        //获取终结符集
        List<String> vT = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            if (!vT.contains(edges.get(i).getEdgeValue())) {
                vT.add(edges.get(i).getEdgeValue());
            }
        }

        leftGrammar.setvT(vT);

        //获取终态集合
        List<String> f = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getNodeType() == NodeType.endNode) {
                f.add(nodes.get(i).getValue());
            }
        }


        //获取左文法推导式集合
        List<String> p = new ArrayList<>();
        //只有出没有入的非终结符集合
        List<String> s1 = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < vN.size(); i++) {
            flag = false;
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).getEndNode().getValue().equals(vN.get(i))) {
                    p.add(vN.get(i) + "->");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                s1.add(vN.get(i));
            }
        }
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).getEndNode().getValue().charAt(0) == p.get(i).charAt(0)) {
                    if (p.get(i).length() <= 3) {
                        if (s1.contains(edges.get(j).getStartNode().getValue())) {
                            p.set(i, p.get(i) + edges.get(j).getEdgeValue() + '|' + edges.get(j).getStartNode().getValue() + edges.get(j).getEdgeValue());
                        } else {
                            p.set(i, p.get(i) + edges.get(j).getStartNode().getValue() + edges.get(j).getEdgeValue());
                        }

                    } else {
                        if (s1.contains(edges.get(j).getStartNode().getValue())) {
                            p.set(i, p.get(i) + '|' + edges.get(j).getEdgeValue() + '|' + edges.get(j).getStartNode().getValue() + edges.get(j).getEdgeValue());
                        } else {
                            p.set(i, p.get(i) + '|' + edges.get(j).getStartNode().getValue() + edges.get(j).getEdgeValue());
                        }

                    }
                }
            }
        }

        //获取开始符
        if (f.size() > 1) {
            leftGrammar.setS("f");
            String farrive = "f->";
            for (String string : f) {
                farrive = farrive + string + "|";
            }
            farrive = farrive.substring(0, farrive.length() - 1);
            p.add(farrive);
        } else {
            leftGrammar.setS(f.get(0));
        }

        leftGrammar.setP(p);


    }


}
