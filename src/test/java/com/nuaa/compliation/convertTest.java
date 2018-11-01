package com.nuaa.compliation;

import com.nuaa.compliation.bean.Grammar;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Convert Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十月 30, 2018</pre>
 */
public class convertTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: leftGrammarToGraph(LeftGrammar leftGrammar)
     */
    @Test
    public void testLeftGrammarToGraph() throws Exception {
//TODO: Test goes here...

        Grammar leftGrammar = new Grammar();


        leftGrammar.setvT(Arrays.asList("0", "1"));
        leftGrammar.setvN(Arrays.asList("B", "C", "D", "f"));
        leftGrammar.setS("f");


        List<String> fai = new ArrayList<>();

        fai.add("f,0,C0");
        fai.add("C,B1");
        fai.add("B,0,C0");
        fai.add("D,1,C1,D0,D1,B0");

        leftGrammar.setP(fai);






    }

    /**
     * Method: rightGrammarToGraph()
     */
    @Test
    public void testRightGrammarToGraph() throws Exception {
        System.out.println("testRightGrammarToGraph");
        //TODO: Test goes here...
        //产生式：start[i] -> expr[i]
    	//VT = {0,1,....}
    	//VN = {A,B,C,D,...}
    	//输入：两个字符数组
    	//开始符号默认是start[0]
    	//终结符默认 "F"
        String[] start = {"A", 		 "B", 	  "C",		 "D"};
        String[] expr  = {"0|0B|1D", "0D|1C", "0|0B|1D", "0D|1D"};
        //GrammarToGraph g = new GrammarToGraph();
        //g.rightGrammarToGraph(start, expr);
    }


} 
