package L5_8.GUI;

import L5_8.data.Region;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Documented;

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
                        try{
                            String name = textName.getText();
                            double area = Double.parseDouble(textArea.getText());
                            int population = Integer.parseInt(textPopulation.getText());
                            String headChief = textHeadChief.getText();
                            if (comboBoxType.getSelectedItem().equals("Город")) {
                                myTableModel.addCity(name, area, population, headChief);
                            } else if (comboBoxType.getSelectedItem().equals("Деревня")) {
                                myTableModel.addVillage(name, area, population, headChief);
                            }
                            dialog.dispose();
                        }catch(NumberFormatException exception){
                            String message = "Некорректно заполнены поля";
                            JOptionPane.showMessageDialog(null, message);
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
