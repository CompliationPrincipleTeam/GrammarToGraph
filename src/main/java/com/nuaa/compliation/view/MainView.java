package com.nuaa.compliation.view;

import com.nuaa.compliation.bean.*;
import com.nuaa.compliation.convert.GrammarToGraph;
import com.nuaa.compliation.enums.ModelType;
import com.nuaa.compliation.inter.IMainView;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    private GrammarToGraph leftGrammarToGraph;

    private String[] titles = {"起点", "状态", "终点"};
    private String[] titles2 = {"结点", "表达式"};

    private BasicVisualizationServer<Node, Edge> nodeEdgeBasicVisualizationServer;


    private int deleGrammerIndex = -1;
    private int deleGraphIndex = -1;

    int operateBoardX = 0;


    private ArrayList<String[]> expressionList;
    private ArrayList<String[]> graphList;


    public MainView() throws HeadlessException {
        super();
        initData();
        initView();
        initListener();
        setVisible(true);

    }

    @Override
    public void initData() {

        grammarJPopupMenu = new JPopupMenu();
        graphJPopupMenu = new JPopupMenu();

        grammarDelMenItem = new JMenuItem();
        grammarDelMenItem.setText("  删除  ");

        grammarJPopupMenu.add(grammarDelMenItem);


        graphDelMenItem = new JMenuItem();
        graphDelMenItem.setText("  删除  ");

        graphJPopupMenu.add(graphDelMenItem);


        expressionList = new ArrayList<>();
        graphList = new ArrayList<>();


        modelType = ModelType.LeftToNf;
        leftGrammarToGraph = new GrammarToGraph();

        startText = new JTextField();
        startText.setText("起点");
        startText.addFocusListener(new TextFiledFocusListener("起点", startText));
        startText.setBounds(operateBoardX + (Constant.RightWidth - 350) / 2, 550, 100, 30);


        valueText = new JTextField();
        valueText.setText("经过");
        valueText.addFocusListener(new TextFiledFocusListener("经过", valueText));
        valueText.setBounds(125 + operateBoardX + (Constant.RightWidth - 350) / 2, 550, 100, 30);


        endText = new JTextField();
        endText.setText("终点");
        endText.addFocusListener(new TextFiledFocusListener("终点", endText));
        endText.setBounds(250 + operateBoardX + (Constant.RightWidth - 350) / 2, 550, 100, 30);


        chuTaiText = new JTextField();
        chuTaiText.setText("初态集");
        chuTaiText.addFocusListener(new TextFiledFocusListener("初态集", chuTaiText));
        chuTaiText.setBounds(operateBoardX + (Constant.RightWidth - 220) / 2, 590, 100, 30);


        zhongTaiText = new JTextField();
        zhongTaiText.setText("终态集");
        zhongTaiText.addFocusListener(new TextFiledFocusListener("终态集", zhongTaiText));
        zhongTaiText.setBounds(120 + operateBoardX + (Constant.RightWidth - 220) / 2, 590, 100, 30);


        startNodeText = new JTextField();
        startNodeText.setText("起点");
        startNodeText.addFocusListener(new TextFiledFocusListener("起点", startNodeText));
        startNodeText.setBounds(operateBoardX + (Constant.RightWidth - 300) / 2, 550, 80, 30);


        expressionText = new JTextField();
        expressionText.setText("表达式");
        expressionText.addFocusListener(new TextFiledFocusListener("表达式", expressionText));
        expressionText.setBounds(100 + operateBoardX + (Constant.RightWidth - 300) / 2, 550, 200, 30);

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
        addButton.setBounds(operateBoardX + (Constant.RightWidth - 100) / 2, 630, 100, 30);
        add(addButton);


        drawButton = new JButton("绘制");
        drawButton.setBounds(operateBoardX + (Constant.RightWidth - 100) / 2, 670, 100, 30);
        add(drawButton);


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
            leftGrammarToGraph.addEdges(grammar[0], grammar[1], modelType);

        }

        String[][] strEdges = leftGrammarToGraph.getStrEdges(modelType);

        graphList.clear();
        for (String[] edge : strEdges) {
            graphList.add(edge);
        }

        updateGraphTable();
    }




    private void drawGraph() {


        graphPoetView = new GraphView();
        switch (modelType) {
            case LeftToNf:
                coculateGrammerToGraph();
                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(leftGrammarToGraph.leftGraph);
                break;
            case RightToNf:
                coculateGrammerToGraph();
                nodeEdgeBasicVisualizationServer = graphPoetView.updateGraph(leftGrammarToGraph.rightGraph);
                break;
            case NfToLeft:
                break;
            case NfToRight:
                break;
            default:
                break;
        }
        nodeEdgeBasicVisualizationServer.setBounds(Constant.RightWidth, 0, 650, 650);
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
                        String[] tablecontent = {start, expression};


                        expressionList.add(tablecontent);

                        updateGrammarTable();


                        startNodeText.setText("起点");
                        expressionText.setText("表达式");
                    }
                } else {

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

                    if (modelType != ModelType.RightToNf) {
                        initGrammerToGraph();
                    }
                    modelType = ModelType.LeftToNf;
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
                    modelType = ModelType.RightToNf;
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

                    modelType = ModelType.NfToLeft;
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
                    modelType = ModelType.NfToRight;
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
                updateGrammarTable();
            }
        });


        graphDelMenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("delete:" + deleGraphIndex);
                if (deleGraphIndex >= 0) {
                    graphList.remove(deleGraphIndex);
                    updateGraphTable();
                }
            }
        });
    }


    private void updateGrammarTable() {
        int count = tabelModelGrammar.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModelGrammar.removeRow(0);
        }


        for (String[] edge : expressionList) {
            tabelModelGrammar.addRow(edge);
        }
    }

    private void updateGraphTable() {
        int count = tabelModelGraph.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModelGraph.removeRow(0);
        }


        for (String[] edge : graphList) {
            tabelModelGraph.addRow(edge);
        }

    }

    private void updateTableData(String[][] edges) {
        int count = tabelModelGraph.getRowCount();
        for (int i = 0; i < count; i++) {
            tabelModelGraph.removeRow(0);
        }

        graphList.clear();

        for (String[] edge : edges) {
            tabelModelGraph.addRow(edge);
            graphList.add(edge);
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
