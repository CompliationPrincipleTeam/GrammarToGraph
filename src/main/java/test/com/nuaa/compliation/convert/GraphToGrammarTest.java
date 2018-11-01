package test.com.nuaa.compliation.convert;

import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.convert.GraphToGrammar;
import com.nuaa.compliation.enums.ModelType;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/**
 * GraphToGrammar Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since
 * 
 *        <pre>
 * 十一月 1, 2018
 *        </pre>
 */
public class GraphToGrammarTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
     * Method: graphToLeftGrammer(Graph graph)
     */
    @Test
    public void testGraphToLeftGrammer() throws Exception {


//TODO: Test goes here...
        String[] split = {"A"};
        String[] split1 = {"B", "D"};


        ArrayList<String[]> graphList = new ArrayList<>();

        String[] a = {"A", "0", "B"};
        String[] b = {"A", "1", "C"};
        String[] c = {"B", "0", "C"};
        String[] v = {"C", "0", "D"};

        graphList.add(a);
        graphList.add(b);
        graphList.add(c);
        graphList.add(v);


        GraphToGrammar graphToGrammar = new GraphToGrammar();
        
        //自动机转左文法测试
//        graphToGrammar.graphToGrammar(split, split1, graphList, ModelType.NfToLeft);
//        List<String> p = graphToGrammar.leftGrammar.getP();

        //自动机转右文法测试
        graphToGrammar.graphToGrammar(split, split1, graphList, ModelType.NfToRight);
        List<String> p_right = graphToGrammar.rightGrammar.getP();
        

/**
 Graph graph = new Graph();

 graph.addEdge("A", "0", "B");
 graph.addEdge("A", "1", "C");
 graph.addEdge("B", "0", "C");
 graph.addEdge("C", "0", "D");

 graph.updateZhongTai(split1);
 graph.updateChuTai(split);


 GraphToGrammar graphToGrammar = new GraphToGrammar();
 graphToGrammar.graphToLeftGrammer(graph);
 Grammar grammar = graphToGrammar.getGrammer()
 */


        for (String str : p_right) {
            System.out.println(str);
        }


    }

}
