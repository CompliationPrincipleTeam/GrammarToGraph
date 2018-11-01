package com.nuaa.compliation.base;

import com.nuaa.compliation.bean.Grammar;
import com.nuaa.compliation.bean.Graph;

/**
 * @author dmrfcoder
 * @date 2018/11/1
 */
public class BaseConvertGraphToGrammar {

    public Graph leftGraph;
    public Graph rightGraph;

    public Grammar leftGrammar;
    public Grammar rightGrammar;

    public BaseConvertGraphToGrammar() {
        leftGrammar = new Grammar();
        rightGrammar = new Grammar();
        leftGraph = new Graph();
        rightGraph = new Graph();

    }
}
