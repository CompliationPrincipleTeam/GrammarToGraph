package com.nuaa.compliation.convert;

import java.util.ArrayList;
import java.util.List;

import com.nuaa.compliation.bean.Edge;
import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.bean.Node;
import com.nuaa.compliation.enums.NodeType;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class GraphToGrammar {
	
	private Grammar grammer;
	
	public GraphToGrammar() {
		// TODO Auto-generated constructor stub
		grammer=new Grammar();
	}
	
	public void graphToLeftGrammer(Graph graph){
		List<Edge> edges=graph.getEdges();
		List<Node> nodes=graph.getNodes();
		//获取非终结符集
		List<String> vN=new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			vN.add(nodes.get(i).getValue());
		}
		grammer.setvN(vN);
		
		//获取终结符集
		List<String> vT=new ArrayList<>();
		for (int i = 0; i < edges.size(); i++) {
			if (!vT.contains(edges.get(i).getEdgeValue())) {
				vT.add(edges.get(i).getEdgeValue());
			}
		}
		grammer.setvT(vT);
		
		//获取终态集合
		List<String> f=new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getNodeType()==NodeType.endNode) {
				f.add(nodes.get(i).getValue());
			}
		}
		
		
		//获取左文法推导式集合
		List<String> p=new ArrayList<>();
		List<String> s1=new ArrayList<>();  //只有出没有入的非终结符集合
		boolean flag=false;
		for (int i = 0; i < vN.size(); i++) {
			flag=false;
			for (int j = 0; j < edges.size(); j++) {
				if (edges.get(j).getEndNode().getValue().equals(vN.get(i))) {
					p.add(vN.get(i)+"->");
					flag=true;
					break;
				} 
			}
			if (!flag) {
				s1.add(vN.get(i));
			}
		}
		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < edges.size(); j++) {
				if (edges.get(j).getEndNode().equals(p.get(i).charAt(0))) {
					if (p.get(i).length()<=3) {
						if(s1.contains(edges.get(j).getStartNode())){
							p.set(i, p.get(i)+edges.get(j).getEdgeValue()+'|'+edges.get(j).getStartNode().getValue()+edges.get(j).getEdgeValue());
						}else
						p.set(i, p.get(i)+edges.get(j).getStartNode().getValue()+edges.get(j).getEdgeValue());
					} else {
						if(s1.contains(edges.get(j).getStartNode())){
							p.set(i, p.get(i)+'|'+edges.get(j).getEdgeValue()+'|'+edges.get(j).getStartNode().getValue()+edges.get(j).getEdgeValue());
						}else
							p.set(i, p.get(i)+'|'+edges.get(j).getStartNode().getValue()+edges.get(j).getEdgeValue());
					}
				}
			}
		}
		
		//获取开始符
		if (f.size()>1) {
			grammer.setS("f");
			String farrive="f->";
//			for (int i = 0; i < nodes.size(); i++) {
//				if (nodes.get(i).getNodeType()==NodeType.endNode) {
//					farrive+=nodes.get(i).getValue()+"|";
//				}
//			}
			for (String string : f) {
				farrive=farrive+string+"|";
			}
			farrive.substring(0, farrive.length()-2);
			p.add(farrive);
		}else {
			grammer.setS(f.get(0));
		}
		
		grammer.setP(p);
	}
	
	public Grammar getGrammer(){
		return this.grammer;
	}
}
