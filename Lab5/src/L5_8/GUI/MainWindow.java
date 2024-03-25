package L5_8.GUI;

import L5_8.data.Region;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.JTextField;



public class MainWindow extends JFrame{
    private JTable jTable;
    private MyTableModel myTableModel;
    private JButton buttonDelete;
    private JButton buttonAdd;
    private JButton buttonSearch;
    private JScrollPane jScrollPane;

    public MainWindow(){
       setTitle("Список мест");
       setSize(600, 400);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel panelFlow = new JPanel();
       panelFlow.setLayout(new FlowLayout(FlowLayout.LEFT));

       this.buttonAdd = new JButton("Добавить");
        panelFlow.add(buttonAdd);

       this.buttonSearch = new JButton("Найти");
        panelFlow.add(buttonSearch);

       this.buttonDelete = new JButton("Удалить место");
       panelFlow.add(buttonDelete);

       JPanel panelBorder = new JPanel(new BorderLayout());
       panelBorder.add(panelFlow, BorderLayout.NORTH);

       myTableModel = new MyTableModel(new Region());

       jTable = new JTable();
       jTable.setModel(myTableModel);

       this.jScrollPane = new JScrollPane(jTable);
       this.add(jScrollPane);

       panelBorder.add(jScrollPane, BorderLayout.CENTER);



        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    myTableModel.delete(jTable.getSelectedRow());
                }catch(IndexOutOfBoundsException ex){
                    String message = "Выделите место";
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setSize(250, 270);
                dialog.setTitle("Добавление");
                dialog.setLocationRelativeTo(null);

                JPanel grid = new JPanel();
                grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                GridLayout gridLayout = new GridLayout(6, 2, 0, 15);
                grid.setLayout(gridLayout);

                grid.add(new JLabel("Название:"));
                TextField textName = new TextField(20);
                grid.add(textName);


                grid.add(new JLabel("Площадь:"));
                TextField textArea = new TextField(20);
                grid.add(textArea);

                grid.add(new JLabel("Население:"));
                TextField textPopulation = new TextField(20);
                grid.add(textPopulation);

                grid.add(new JLabel("Глава:"));
                TextField textHeadChief = new TextField(20);
                grid.add(textHeadChief);


                grid.add(new JLabel("Тип:"));

                JComboBox comboBoxType = new JComboBox();

                comboBoxType.addItem("Город");
                comboBoxType.addItem("Деревня");

                grid.add(comboBoxType);
                grid.add(new JLabel(""));

                JButton buttonAddDialog = new JButton("Добавить");
                grid.add(buttonAddDialog);

                buttonAddDialog.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textName.setBackground(Color.WHITE);
                        textArea.setBackground(Color.WHITE);
                        textPopulation.setBackground(Color.WHITE);
                        textHeadChief.setBackground(Color.WHITE);
                        Color paleRed = new Color(251, 170, 170);
                        boolean areaEmpty = false;
                        boolean populationEmpty = false;


                        try {
                            double area = -1;
                            int population = -1;
                            String empty = "";
                            String name = textName.getText();
                            try{area = Double.parseDouble(textArea.getText());}
                            catch (NumberFormatException exception){
                                areaEmpty = true;
                            }

                            try{population = Integer.parseInt(textPopulation.getText());}
                            catch (NumberFormatException exception){
                                populationEmpty = true;
                            }

                            String headChief = textHeadChief.getText();
                            if(textName.getText().equals(empty)) throw new NumberFormatException();
                            if(areaEmpty || area <= -1) throw new NumberFormatException();
                            if(populationEmpty || area <= -1) throw new NumberFormatException();
                            if(textHeadChief.getText().equals(empty)) throw new NumberFormatException();
                            if (comboBoxType.getSelectedItem().equals("Город")) {
                                myTableModel.addCity(name, area, population, headChief);
                            } else if (comboBoxType.getSelectedItem().equals("Деревня")) {
                                myTableModel.addVillage(name, area, population, headChief);
                            }
                            dialog.dispose();
                        }catch(NumberFormatException exception){
                            String empty = "";
                            if(textName.getText().equals(empty)) textName.setBackground(paleRed);
                            if(textArea.getText().equals(empty) || areaEmpty) textArea.setBackground(paleRed);
                            if(textPopulation.getText().equals(empty) || populationEmpty) textPopulation.setBackground(paleRed);
                            if(textHeadChief.getText().equals(empty)) textHeadChief.setBackground(paleRed);
                        }
                    }
                });
                dialog.getContentPane().add(grid);
                dialog.setVisible(true);
            }
        });

        Frame f = new Frame();

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setSize(400, 120);
                dialog.setTitle("Поиск");
                dialog.setLocationRelativeTo(null);

                JButton buttonSearch = new JButton("Искать");

                dialog.add(buttonSearch);
                JTextField textSearch = new JTextField(30);
                dialog.add(textSearch);

                JPanel panel = new JPanel();
                JPanel panel1 = new JPanel();
                JPanel panel2= new JPanel();

                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel1.add(textSearch);
                panel2.add(buttonSearch);
                panel.add(panel1);
                panel.add(panel2);
                dialog.getContentPane().add(panel);

                buttonSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = textSearch.getText();
                        String message = myTableModel.search(text);;
                        JOptionPane.showMessageDialog(null, message);
                    }
                });
                dialog.setVisible(true);
            }
        });
        setContentPane(panelBorder);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
