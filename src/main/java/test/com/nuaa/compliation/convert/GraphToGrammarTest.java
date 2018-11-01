package test.com.nuaa.compliation.convert;

import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Graph;
import com.nuaa.compliation.convert.GraphToGrammar;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * GraphToGrammar Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 1, 2018</pre>
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


        Graph graph = new Graph();
        graph.addEdge("A", "0", "B");
        graph.addEdge("A", "1", "C");
        graph.addEdge("B", "0", "C");
        graph.addEdge("C", "0", "D");


        graph.updateChuTai(split);
        graph.updateZhongTai(split1);

        GraphToGrammar graphToGrammar = new GraphToGrammar();
        Grammar grammar = graphToGrammar.graphToLeftGrammer(graph);

        List<String> p = grammar.getP();

        for (String str : p) {
            System.out.println(str);
        }


    }


} 
