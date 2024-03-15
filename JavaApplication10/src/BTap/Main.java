package BTap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.*;

public class Main extends JFrame {
    private JTextField display;
    private double num1, num2, result;
    private String operator;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        display = new JTextField();
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberButtonActionListener());
        }

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton solveButton = new JButton("solve"); // Renamed "=" to "solve"
        JButton modButton = new JButton("%"); // Added "%"
        JButton negButton = new JButton("neg"); // Added "neg" for negative number
        JButton deleteButton = new JButton("delete"); // Added "delete"
        JButton backButton = new JButton("←"); // Added "←" for backspace

        addButton.addActionListener(new OperatorButtonActionListener("+"));
        subtractButton.addActionListener(new OperatorButtonActionListener("-"));
        multiplyButton.addActionListener(new OperatorButtonActionListener("*"));
        divideButton.addActionListener(new OperatorButtonActionListener("/"));
        solveButton.addActionListener(new EqualsButtonActionListener());
        modButton.addActionListener(new OperatorButtonActionListener("%"));
        negButton.addActionListener(new OperatorButtonActionListener("neg")); // ActionListener for "neg"
        deleteButton.addActionListener(new DeleteButtonActionListener()); // ActionListener for "delete"
        backButton.addActionListener(new BackButtonActionListener()); // ActionListener for backspace

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        // Horizontal layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(display)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(numberButtons[7])
                                .addComponent(numberButtons[4])
                                .addComponent(numberButtons[1])
                                .addComponent(numberButtons[0]))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(numberButtons[8])
                                .addComponent(numberButtons[5])
                                .addComponent(numberButtons[2])
                                .addComponent(solveButton)) // "=" changed to "solve"
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(numberButtons[9])
                                .addComponent(numberButtons[6])
                                .addComponent(numberButtons[3])
                                .addComponent(divideButton)) // "/" changed to "%"
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(addButton)
                                .addComponent(subtractButton)
                                .addComponent(multiplyButton)
                                .addComponent(modButton))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(negButton) // Button for negative number
                                .addComponent(deleteButton) // Button for delete
                                .addComponent(backButton)))) // Button for backspace
                    .addContainerGap())
        );

        // Vertical layout
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(display, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(numberButtons[7])
                                .addComponent(numberButtons[8])
                                .addComponent(numberButtons[9]))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(numberButtons[4])
                                .addComponent(numberButtons[5])
                                .addComponent(numberButtons[6]))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(numberButtons[1])
                                .addComponent(numberButtons[2])
                                .addComponent(numberButtons[3]))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(numberButtons[0])
                                .addComponent(solveButton)
                                .addComponent(divideButton)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(subtractButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(multiplyButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(modButton)
                            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                .addComponent(modButton)
                                .addComponent(negButton)) // Button for negative number
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteButton) // Button for delete
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(backButton))) // Button for backspace
                    .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }

    private class NumberButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            display.setText(display.getText() + button.getText());
        }
    }

    private class OperatorButtonActionListener implements ActionListener {
        private String operator;

        public OperatorButtonActionListener(String operator) {
            this.operator = operator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                num1 = Double.parseDouble(display.getText());
                display.setText("");
                Main.this.operator = operator;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
    }

    private class EqualsButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                    case "%":
                        if (num2 != 0) {
                            result = num1 % num2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                    case "neg":
                        result = -num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
    }

    private class DeleteButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = null;
        }
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
