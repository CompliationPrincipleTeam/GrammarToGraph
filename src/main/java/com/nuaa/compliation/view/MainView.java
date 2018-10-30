package com.nuaa.compliation.view;

import com.nuaa.compliation.bean.*;
import com.nuaa.compliation.convert.GrammarToGraph;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.inter.IMainView;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * @author dmrfcoder
 * @date 2018/10/30
 */
public class MainView extends JFrame implements IMainView {


    private ModelType modelType;

    private JButton addButton;
    private JButton drawButton;
    private GraphView graphPoetView;

    private ButtonGroup buttonGroup;
    private JRadioButton randioButton1;
    private JRadioButton randioButton2;
    private JRadioButton randioButton3;
    private JRadioButton randioButton4;


    private JLabel rightTitle;
    private JTable jTable;
    private DefaultTableModel tabelModel;

    private JTextField startNodeText;
    private JTextField expressionText;


    private GrammarToGraph leftGrammarToGraph;

    private String[] titles = {"起点", "状态", "终点"};

    private BasicVisualizationServer<Node, Edge> nodeEdgeBasicVisualizationServer;


    public MainView() throws HeadlessException {
        super();
        initData();
        initView();
        initListener();
        setVisible(true);

    }

    @Override
    public void initData() {
        modelType = ModelType.LeftToNf;
        leftGrammarToGraph = new GrammarToGraph();

    }

    @Override
    public void initView() {


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
        randioButton1.setBounds(650, 0, 100, 30);
        randioButton2 = new JRadioButton("Right->NF");
        randioButton2.setBounds(750, 0, 100, 30);
        randioButton3 = new JRadioButton("NF->Left");
        randioButton3.setBounds(850, 0, 100, 30);
        randioButton4 = new JRadioButton("NF->Right");
        randioButton4.setBounds(950, 0, 100, 30);


        add(randioButton1);
        add(randioButton2);
        add(randioButton3);
        add(randioButton4);


        buttonGroup = new ButtonGroup();
        buttonGroup.add(randioButton1);
        buttonGroup.add(randioButton2);
        buttonGroup.add(randioButton3);
        buttonGroup.add(randioButton4);

        rightTitle = new JLabel("文法");
        rightTitle.setBounds(Constant.GraphWidth + (Constant.WindowWidth - Constant.GraphWidth - 80) / 2, 30, 80, 30);
        add(rightTitle);


        String[][] datas = {};

        tabelModel = new DefaultTableModel(datas, titles);
        jTable = new JTable(tabelModel);
        jTable.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(jTable);

        scrollPane.setBounds(Constant.GraphWidth, 60, Constant.RightWidth, 300);
        add(scrollPane);


        startNodeText = new JTextField();
        startNodeText.setText("起点");
        startNodeText.addFocusListener(new TextFiledFocusListener("起点", startNodeText));
        startNodeText.setBounds(Constant.GraphWidth + (Constant.RightWidth - 300) / 2, 450, 80, 30);
        add(startNodeText);


        expressionText = new JTextField();
        expressionText.setText("表达式");
        expressionText.addFocusListener(new TextFiledFocusListener("表达式", expressionText));
        expressionText.setBounds(100 + Constant.GraphWidth + (Constant.RightWidth - 300) / 2, 450, 200, 30);
        add(expressionText);


        addButton = new JButton("添加");
        addButton.setBounds(Constant.GraphWidth + (Constant.RightWidth - 100) / 2, 500, 100, 30);
        add(addButton);


        drawButton = new JButton("绘制");
        drawButton.setBounds(Constant.GraphWidth + (Constant.RightWidth - 100) / 2, 550, 100, 30);
        add(drawButton);


    }


    private void drawGraph() {
        graphPoetView = new GraphView();
        switch (modelType) {
            case LeftToNf:
                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(leftGrammarToGraph.leftGraph);
                break;
            case RightToNf:
                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(leftGrammarToGraph.rightGraph);
                break;
            case NfToLeft:
                break;
            case NfToRight:
                break;
            default:
                break;
        }
        nodeEdgeBasicVisualizationServer.setBounds(0, 0, 650, 650);
        add(nodeEdgeBasicVisualizationServer);
    }

    @Override
    public void initListener() {


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String start = startNodeText.getText().trim();
                String expression = expressionText.getText().trim();
                if (modelType == ModelType.LeftToNf || modelType == ModelType.RightToNf) {
                    if (start.equals("起点") || expression.equals("表达式")) {
                        JOptionPane.showMessageDialog(null, "填写内容不可为空！");

                    } else {
                        leftGrammarToGraph.addEdges(start, expression, modelType);
                        String[][] edges = leftGrammarToGraph.getStrEdges(modelType);

                        updateTableData(edges);


                        startNodeText.setText("起点");
                        expressionText.setText("表达式");
                    }
                }

            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawGraph();
            }
        });

        randioButton1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton1.isSelected() && modelType != ModelType.LeftToNf) {
                    modelType = ModelType.LeftToNf;
                    System.out.println("1");
                }
            }
        });


        randioButton2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton2.isSelected() && modelType != ModelType.RightToNf) {
                    System.out.println("2");
                    modelType = ModelType.RightToNf;
                }
            }
        });

        randioButton3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton3.isSelected() && modelType != ModelType.NfToLeft) {
                    System.out.println("3");
                    modelType = ModelType.NfToLeft;
                }
            }
        });

        randioButton4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (randioButton4.isSelected() && modelType != ModelType.NfToRight) {
                    System.out.println("4");
                    modelType = ModelType.NfToRight;
                }
            }
        });

    }


    private void updateTableData(String[][] edges) {
        int count = tabelModel.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModel.removeRow(0);
        }
        for (String[] edge : edges) {
            tabelModel.addRow(edge);
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
