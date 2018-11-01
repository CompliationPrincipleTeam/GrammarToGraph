package com.nuaa.compliation.view;

import com.nuaa.compliation.bean.*;
import com.nuaa.compliation.convert.GrammarToGraph;
import com.nuaa.compliation.convert.GraphToGrammar;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.enums.NodeType;
import com.nuaa.compliation.exception.GrammarPhaseException;
import com.nuaa.compliation.inter.IMainView;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class MainView extends JFrame implements IMainView {


    private ModelType modelType;

    private JButton addButton;
    private JButton drawButton;
    private JButton clearButton;
    private GraphView graphPoetView;

    private ButtonGroup buttonGroup;
    private JRadioButton randioButton1;
    private JRadioButton randioButton2;
    private JRadioButton randioButton3;
    private JRadioButton randioButton4;


    private JLabel graphTitle;
    private JLabel grammarTitle;
    private JTable jTableGraph;
    private JTable jTableGrammar;

    private DefaultTableModel tabelModelGraph;
    private DefaultTableModel tabelModelGrammar;

    private JTextField startNodeText;
    private JTextField expressionText;


    private JTextField startText;
    private JTextField valueText;
    private JTextField endText;
    private JTextField chuTaiText;
    private JTextField zhongTaiText;


    private JPopupMenu grammarJPopupMenu;
    private JPopupMenu graphJPopupMenu;

    private JMenuItem grammarDelMenItem;
    private JMenuItem graphDelMenItem;

    private JLabel chutaiLabel;
    private JLabel zhongtaiLabel;


    private GrammarToGraph grammarToGraph;
    private GraphToGrammar graphToGrammar;


    private String[] titles = {"起点", "状态", "终点"};
    private String[] titles2 = {"结点", "表达式"};

    private BasicVisualizationServer<Node, Edge> nodeEdgeBasicVisualizationServer;


    private int deleGrammerIndex = -1;
    private int deleGraphIndex = -1;

    int operateBoardX = 0;


    private ArrayList<String[]> expressionList;
    private ArrayList<String[]> graphList;

    private ArrayList<String[]> leftExpressionList;
    private ArrayList<String[]> leftGraphList;

    private ArrayList<String[]> rightExpressionList;
    private ArrayList<String[]> rightGraphList;


    private ArrayList<String[]> nfLeftExpressionList;
    private ArrayList<String[]> nfLeftGraphList;

    private ArrayList<String[]> nfRightExpressionList;
    private ArrayList<String[]> nfRightGraphList;


    private String chuTaiLeft;
    private String chuTaiRight;

    private String zhongTaiLeft;
    private String zhongTaiRight;

    private String chuTai;
    private String zhongTai;


    private ArrayList<String[]> graphListLeft;
    private ArrayList<String[]> graphListRight;


    public MainView() throws HeadlessException {
        super();


        initData();
        initView();
        initListener();
        setVisible(true);

    }

    @Override
    public void initData() {

        chuTai = "";
        chuTaiLeft = "";
        chuTaiRight = "";
        zhongTai = "";
        zhongTaiLeft = "";
        zhongTaiRight = "";


        expressionList = new ArrayList<>();
        graphList = new ArrayList<>();

        leftExpressionList = new ArrayList<>();
        leftGraphList = new ArrayList<>();

        rightExpressionList = new ArrayList<>();
        rightGraphList = new ArrayList<>();

        nfLeftExpressionList = new ArrayList<>();
        nfLeftGraphList = new ArrayList<>();

        nfRightExpressionList = new ArrayList<>();
        nfRightGraphList = new ArrayList<>();

        graphListLeft = new ArrayList<>();
        graphListRight = new ArrayList<>();


        grammarJPopupMenu = new JPopupMenu();
        graphJPopupMenu = new JPopupMenu();


        modelType = ModelType.LeftToNf;
        grammarToGraph = new GrammarToGraph();
        graphToGrammar = new GraphToGrammar();


        grammarDelMenItem = new JMenuItem();
        grammarDelMenItem.setText("  删除  ");

        grammarJPopupMenu.add(grammarDelMenItem);


        graphDelMenItem = new JMenuItem();
        graphDelMenItem.setText("  删除  ");

        graphJPopupMenu.add(graphDelMenItem);


        chutaiLabel = new JLabel("初态集：");
        chutaiLabel.setBounds(operateBoardX + (Constant.RightWidth - 420) / 2, 550, 200, 30);
        add(chutaiLabel);

        zhongtaiLabel = new JLabel("终态集：");
        zhongtaiLabel.setBounds(operateBoardX + (Constant.RightWidth - 420) / 2 + 220, 550, 200, 30);
        add(zhongtaiLabel);


        startText = new JTextField();
        startText.setText("起点");
        startText.addFocusListener(new TextFiledFocusListener("起点", startText));
        startText.setBounds(operateBoardX + (Constant.RightWidth - 350) / 2, 580, 100, 30);


        valueText = new JTextField();
        valueText.setText("经过");
        valueText.addFocusListener(new TextFiledFocusListener("经过", valueText));
        valueText.setBounds(125 + operateBoardX + (Constant.RightWidth - 350) / 2, 580, 100, 30);


        endText = new JTextField();
        endText.setText("终点");
        endText.addFocusListener(new TextFiledFocusListener("终点", endText));
        endText.setBounds(250 + operateBoardX + (Constant.RightWidth - 350) / 2, 580, 100, 30);


        chuTaiText = new JTextField();
        chuTaiText.setText("初态集");
        chuTaiText.addFocusListener(new TextFiledFocusListener("初态集", chuTaiText));
        chuTaiText.setBounds(operateBoardX + (Constant.RightWidth - 220) / 2, 620, 100, 30);


        zhongTaiText = new JTextField();
        zhongTaiText.setText("终态集");
        zhongTaiText.addFocusListener(new TextFiledFocusListener("终态集", zhongTaiText));
        zhongTaiText.setBounds(120 + operateBoardX + (Constant.RightWidth - 220) / 2, 620, 100, 30);


        startNodeText = new JTextField();
        startNodeText.setText("起点");
        startNodeText.addFocusListener(new TextFiledFocusListener("起点", startNodeText));
        startNodeText.setBounds(operateBoardX + (Constant.RightWidth - 300) / 2, 580, 80, 30);


        expressionText = new JTextField();
        expressionText.setText("表达式");
        expressionText.addFocusListener(new TextFiledFocusListener("表达式", expressionText));
        expressionText.setBounds(100 + operateBoardX + (Constant.RightWidth - 300) / 2, 580, 200, 30);


    }

    private void updateChuTaiAndZhongTaiLabel() {
        chutaiLabel.setText("初态集：{" + chuTai.replace('|', ',') + "}");
        zhongtaiLabel.setText("终态集：{" + zhongTai.replace('|', ',') + "}");

    }

    private void updateChuTaiAndZhongTaiLabel(Graph graph) {

        chuTai = "";
        zhongTai = "";

        for (Node node : graph.getNodes()) {
            if (node.getNodeType() == NodeType.endNode) {
                zhongTai = zhongTai + node.getValue() + "|";
            } else if (node.getNodeType() == NodeType.startNode) {
                chuTai = chuTai + node.getValue() + "|";
            }
        }
        chutaiLabel.setText("初态集：{" + chuTai.replace('|', ',') + "}");
        zhongtaiLabel.setText("终态集：{" + zhongTai.replace('|', ',') + "}");


    }

    @Override
    public void initView() {


        UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo tem : info) {
            System.out.println(tem.getClassName());
        }

        try {
            UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        setTitle("词法分析");
        setSize(Constant.WindowWidth, Constant.WindowHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        int frm_Height = this.getHeight();
        int frm_width = this.getWidth();
        setLocation((screenWidth - frm_width) / 2,
                (screenHeight - frm_Height) / 2);


        getContentPane().setLayout(null);


        randioButton1 = new JRadioButton("Left->NF", true);
        randioButton1.setBounds(operateBoardX, 0, 100, 30);
        randioButton2 = new JRadioButton("Right->NF");
        randioButton2.setBounds(operateBoardX + 100, 0, 100, 30);
        randioButton3 = new JRadioButton("NF->Left");
        randioButton3.setBounds(operateBoardX + 200, 0, 100, 30);
        randioButton4 = new JRadioButton("NF->Right");
        randioButton4.setBounds(operateBoardX + 300, 0, 100, 30);


        add(randioButton1);
        add(randioButton2);
        add(randioButton3);
        add(randioButton4);


        buttonGroup = new ButtonGroup();
        buttonGroup.add(randioButton1);
        buttonGroup.add(randioButton2);
        buttonGroup.add(randioButton3);
        buttonGroup.add(randioButton4);


        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);


        graphTitle = new JLabel("转换关系");
        graphTitle.setBounds(operateBoardX + (Constant.WindowWidth - Constant.GraphWidth - 80) / 2, 30, 80, 30);
        add(graphTitle);

        String[][] datas = {};

        tabelModelGraph = new DefaultTableModel(datas, titles);
        jTableGraph = new JTable(tabelModelGraph);
        jTableGraph.setRowHeight(20);
        jTableGraph.setDefaultRenderer(Object.class, tcr);
        jTableGraph.getTableHeader().setDefaultRenderer(tcr);
        JScrollPane scrollPane = new JScrollPane(jTableGraph);

        scrollPane.setBounds(operateBoardX, 60, Constant.RightWidth, 300);
        add(scrollPane);


        grammarTitle = new JLabel("文法");
        grammarTitle.setBounds(operateBoardX + (Constant.WindowWidth - Constant.GraphWidth - 80) / 2, 360, 80, 30);
        add(grammarTitle);

        String[][] datas2 = {};

        tabelModelGrammar = new DefaultTableModel(datas2, titles2);
        jTableGrammar = new JTable(tabelModelGrammar);
        jTableGrammar.setRowHeight(20);

        jTableGrammar.setDefaultRenderer(Object.class, tcr);
        jTableGrammar.getTableHeader().setDefaultRenderer(tcr);

        JScrollPane scrollPane2 = new JScrollPane(jTableGrammar);

        scrollPane2.setBounds(operateBoardX, 400, Constant.RightWidth, 150);
        add(scrollPane2);


        add(startNodeText);


        add(expressionText);


        addButton = new JButton("添加");
        addButton.setBounds(operateBoardX + (Constant.RightWidth - 100) / 2, 660, 100, 30);
        add(addButton);


        drawButton = new JButton("绘制");
        drawButton.setBounds(operateBoardX + (Constant.RightWidth - 100) / 2, 700, 100, 30);
        add(drawButton);

        clearButton = new JButton("清屏");
        clearButton.setBounds(operateBoardX + (Constant.RightWidth - 100) / 2, 740, 100, 30);
        add(clearButton);


        getRootPane().setDefaultButton(addButton);

    }


    private void initGrammerToGraph() {
        remove(startText);
        remove(valueText);
        remove(endText);
        remove(chuTaiText);
        remove(zhongTaiText);

        add(startNodeText);


        add(expressionText);

        repaint();


    }

    private void initGraphToGrammer() {
        remove(startNodeText);

        remove(expressionText);


        add(zhongTaiText);
        add(chuTaiText);
        add(endText);
        add(valueText);
        add(startText);


        repaint();


    }


    private void coculateGrammerToGraph() {
        for (String[] grammar : expressionList) {
            try {
                grammarToGraph.addEdges(grammar[0], grammar[1], modelType);
            } catch (GrammarPhaseException e) {
                e.printStackTrace();
            }

        }

        String[][] strEdges = grammarToGraph.getStrEdges(modelType);

        graphList.clear();
        for (String[] edge : strEdges) {
            graphList.add(edge);
        }

        updateTable();
    }

    private void coculateGraphToGrammar(String chuTai, String zhongTai) {

        String[] splitChutai = chuTai.split("\\|");
        String[] splitZhongtai = zhongTai.split("\\|");


        Grammar grammar = graphToGrammar.graphToGrammar(splitChutai, splitZhongtai, graphList, modelType);


        List<String> p = grammar.getP();

        expressionList.clear();

        for (String itemP : p) {
            String[] split = itemP.split("->");
            expressionList.add(split);
        }

        updateTable();


        drawGraph();

        updateChuTaiAndZhongTaiLabel();


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);


        int x = Constant.RightWidth + 500;
        int y = 20;


        g.setColor(Constant.startNodeColor);
        g.fillOval(x, 30, 10, 10);

        g.drawString("初态结点", x + 30, 40);


        g.setColor(Constant.endNodeColor);
        g.fillOval(x, 50, 10, 10);
        g.drawString("终态结点", x + 30, 60);

        g.setColor(Constant.normalNodeColor);
        g.fillOval(x, 70, 10, 10);
        g.drawString("常规结点", x + 30, 80);


    }

    private void drawGraph() {


        graphPoetView = new GraphView();
        switch (modelType) {
            case LeftToNf:


                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(grammarToGraph.leftGraph);
                updateChuTaiAndZhongTaiLabel(grammarToGraph.leftGraph);
                break;
            case RightToNf:

                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(grammarToGraph.rightGraph);
                updateChuTaiAndZhongTaiLabel(grammarToGraph.rightGraph);
                break;
            case NfToLeft:
                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(graphToGrammar.leftGraph);
                break;
            case NfToRight:
                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(graphToGrammar.rightGraph);
                break;
            default:
                break;
        }


        nodeEdgeBasicVisualizationServer.setBounds(Constant.RightWidth, 100, 650, 650);
        add(nodeEdgeBasicVisualizationServer);
    }

    @Override
    public void initListener() {


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expressionList.clear();
                graphList.clear();

                if (modelType == ModelType.RightToNf || modelType == ModelType.LeftToNf) {
                    grammarToGraph.clearGraph(modelType);
                    drawGraph();
                    coculateGrammerToGraph();
                }


            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (modelType == ModelType.LeftToNf || modelType == ModelType.RightToNf) {
                    String start = startNodeText.getText().trim();
                    String expression = expressionText.getText().trim();

                    if (start.equals("起点") || expression.equals("表达式")) {
                        JOptionPane.showMessageDialog(null, "填写内容不可为空！");

                    } else {
                        String[] tablecontent = {start, expression};


                        try {
                            grammarToGraph.addEdges(start, expression, modelType);
                            expressionList.add(tablecontent);
                            updateGrammarTable(false);
                            startNodeText.setText("起点");
                            expressionText.setText("表达式");
                        } catch (GrammarPhaseException e1) {
                            JOptionPane.showMessageDialog(null, e1.getExceptionInfo());
                            updateGrammarTable(true);
                        }


                    }
                } else {

                    String start = startText.getText().trim();
                    String end = endText.getText().trim();
                    String vaule = valueText.getText().trim();

                    if ("起点".equals(start) || "经过".equals(vaule) || "终点".equals(end)) {
                        JOptionPane.showMessageDialog(null, "请填写完整的关系！");
                    } else {


                        if (!vaule.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, start + "格式非法！");
                            return;
                        }

                        String[] graphItem = {start, vaule, end};
                        graphList.add(graphItem);

                        updateTable();

                        startText.setText("起点");
                        valueText.setText("经过");
                        endText.setText("终点");


                    }


                }

            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modelType == ModelType.RightToNf || modelType == ModelType.LeftToNf) {
                    drawGraph();
                } else {
                    chuTai = chuTaiText.getText().trim();
                    zhongTai = zhongTaiText.getText().trim();

                    if ("初态集".equals(chuTai) || "终态集".equals(zhongTai)) {
                        JOptionPane.showMessageDialog(null, "请输入完整的初态集和终态集！");
                    } else {


                        coculateGraphToGrammar(chuTai, zhongTai);
                    }


                }

            }
        });

        randioButton1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton1.isSelected() && modelType != ModelType.LeftToNf) {

                    if (modelType != ModelType.RightToNf) {
                        initGrammerToGraph();
                    }
                    ModelType priorModelType = modelType;

                    modelType = ModelType.LeftToNf;
                    restoreGrammarToList(priorModelType);

                }
            }
        });


        randioButton2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton2.isSelected() && modelType != ModelType.RightToNf) {

                    if (modelType != ModelType.LeftToNf) {
                        initGrammerToGraph();
                    }

                    ModelType priorModelType = modelType;

                    modelType = ModelType.RightToNf;
                    restoreGrammarToList(priorModelType);

                }
            }
        });

        randioButton3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton3.isSelected() && modelType != ModelType.NfToLeft) {

                    if (modelType != ModelType.NfToRight) {
                        initGraphToGrammer();
                    }
                    ModelType priorModelType = modelType;
                    modelType = ModelType.NfToLeft;
                    restoreGrammarToList(priorModelType);
                }
            }
        });

        randioButton4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton4.isSelected() && modelType != ModelType.NfToRight) {

                    if (modelType != ModelType.NfToLeft) {
                        initGraphToGrammer();
                    }
                    ModelType priorModelType = modelType;
                    modelType = ModelType.NfToRight;
                    restoreGrammarToList(priorModelType);
                }
            }
        });


        jTableGrammar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    deleGrammerIndex = jTableGrammar.rowAtPoint(e.getPoint());
                    if (deleGrammerIndex == -1) {
                        return;
                    }


                    if (modelType == ModelType.LeftToNf || modelType == ModelType.RightToNf) {
                        grammarJPopupMenu.show(jTableGrammar, e.getX(), e.getY());
                    }

                }
            }
        });


        jTableGraph.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    deleGraphIndex = jTableGraph.rowAtPoint(e.getPoint());
                    if (deleGraphIndex == -1) {
                        return;
                    }


                    if (modelType == ModelType.NfToLeft || modelType == ModelType.NfToRight) {
                        graphJPopupMenu.show(jTableGraph, e.getX(), e.getY());
                    }


                }
            }
        });


        grammarDelMenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("delete:" + deleGrammerIndex);
                expressionList.remove(deleGrammerIndex);
                updateGrammarTable(true);
            }
        });


        graphDelMenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("delete:" + deleGraphIndex);
                if (deleGraphIndex >= 0) {
                    graphList.remove(deleGraphIndex);
                    updateTable();
                }
            }
        });
    }

    private void restoreGrammarToList(ModelType priorModeleType) {

        switch (priorModeleType) {
            case LeftToNf:
                leftExpressionList = expressionList;
                leftGraphList = graphList;
                break;
            case RightToNf:
                rightExpressionList = expressionList;
                rightGraphList = graphList;
                break;
            case NfToRight:
                chuTaiRight = chuTai;
                zhongTaiRight = zhongTai;
                graphListRight = graphList;


                break;
            case NfToLeft:
                chuTaiLeft = chuTai;
                zhongTaiLeft = zhongTai;
                graphListLeft = graphList;
                break;
            default:

                break;
        }

        switch (modelType) {
            case LeftToNf:
                expressionList = leftExpressionList;
                graphList = leftGraphList;
                showGrammarRestore();
                break;
            case RightToNf:
                expressionList = rightExpressionList;
                graphList = rightGraphList;
                showGrammarRestore();
                break;
            case NfToRight:
                chuTai = chuTaiRight;
                zhongTai = zhongTaiRight;
                graphList = graphListRight;
                showGraphRestore();
                break;
            case NfToLeft:
                chuTai = chuTaiLeft;
                zhongTai = zhongTaiLeft;
                graphList = graphListLeft;
                showGraphRestore();
                break;
            default:
                break;
        }


    }

    private void showGraphRestore() {


        if (!"".equals(chuTai)) {
            chuTaiText.setText(chuTai);
        }
        if (!"".equals(zhongTai)) {
            zhongTaiText.setText(zhongTaiLeft);
        }


        coculateGraphToGrammar(chuTai, zhongTai);
    }

    private void showGrammarRestore() {
        int count = tabelModelGrammar.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModelGrammar.removeRow(0);
        }

        for (String[] grammar : expressionList) {
            tabelModelGrammar.addRow(grammar);
        }

        int count1 = tabelModelGraph.getRowCount();
        for (int i = 0; i < count1; i++) {
            tabelModelGraph.removeRow(0);
        }

        for (String[] graph : graphList) {
            tabelModelGraph.addRow(graph);
        }
        drawGraph();
    }


    private void updateGrammarTable(boolean catchException) {


        int count = tabelModelGrammar.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModelGrammar.removeRow(0);
        }


        for (String[] edge : expressionList) {
            tabelModelGrammar.addRow(edge);
        }


        String[][] edges;

        if (catchException) {
            grammarToGraph.clearGraph(modelType);
            coculateGrammerToGraph();
        }
        edges = grammarToGraph.getStrEdges(modelType);

        int count1 = tabelModelGraph.getRowCount();
        for (int i = 0; i < count1; i++) {
            tabelModelGraph.removeRow(0);
        }


        graphList.clear();
        for (String[] edge : edges) {
            tabelModelGraph.addRow(edge);
            graphList.add(edge);
        }


    }

    private void updateTable() {
        int count = tabelModelGraph.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModelGraph.removeRow(0);
        }


        for (String[] edge : graphList) {
            tabelModelGraph.addRow(edge);
        }

        int count1 = tabelModelGrammar.getRowCount();

        for (int i = 0; i < count1; i++) {
            tabelModelGrammar.removeRow(0);
        }


        for (String[] expression : expressionList) {
            tabelModelGrammar.addRow(expression);
        }


    }


    class TextFiledFocusListener implements FocusListener {
        String info;
        JTextField jtf;

        public TextFiledFocusListener(String info, JTextField jtf) {
            this.info = info;
            this.jtf = jtf;
        }

        @Override
        public void focusGained(FocusEvent e) {
            String temp = jtf.getText();
            if (temp.equals(info)) {
                jtf.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            String temp = jtf.getText();
            if (temp.equals("")) {
                jtf.setText(info);
            }
        }
    }


}
