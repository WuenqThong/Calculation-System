import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame {
    private JTextField display;
    private double num1, num2, result;
    private String operator;

    public main() {
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

        addButton.addActionListener(new OperatorButtonActionListener("+"));
        subtractButton.addActionListener(new OperatorButtonActionListener("-"));
        multiplyButton.addActionListener(new OperatorButtonActionListener("*"));
        divideButton.addActionListener(new OperatorButtonActionListener("/"));
        solveButton.addActionListener(new EqualsButtonActionListener());
        modButton.addActionListener(new OperatorButtonActionListener("%")); // Added ActionListener for %

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(display)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(numberButtons[7])
                                                        .addComponent(numberButtons[4])
                                                        .addComponent(numberButtons[1])
                                                        .addComponent(numberButtons[0]))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(numberButtons[8])
                                                        .addComponent(numberButtons[5])
                                                        .addComponent(numberButtons[2])
                                                        .addComponent(solveButton)) // "=" changed to "solve"
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(numberButtons[9])
                                                        .addComponent(numberButtons[6])
                                                        .addComponent(numberButtons[3])
                                                        .addComponent(divideButton)) // "/" changed to "%"
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addButton)
                                                        .addComponent(subtractButton)
                                                        .addComponent(multiplyButton)
                                                        .addComponent(modButton)))) // Added "%"
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(display, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(subtractButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(multiplyButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(modButton)) // Added "%"
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(numberButtons[7])
                                                        .addComponent(numberButtons[8])
                                                        .addComponent(numberButtons[9]))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(numberButtons[4])
                                                        .addComponent(numberButtons[5])
                                                        .addComponent(numberButtons[6]))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(numberButtons[1])
                                                        .addComponent(numberButtons[2])
                                                        .addComponent(numberButtons[3]))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(numberButtons[0])
                                                        .addComponent(solveButton) // "=" changed to "solve"
                                                        .addComponent(divideButton)))) // "/" changed to "%"
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
            num1 = Double.parseDouble(display.getText());
            display.setText("");
            main.this.operator = operator;
        }
    }

    private class EqualsButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
            }
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
}
