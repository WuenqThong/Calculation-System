import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField display;
    private double num1, num2, result;
    private String operator;

    public Main() {
        initComponents();
    }

    private void initComponents() {
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 4, 10, 10)); // Số cột được thiết lập thành 4

        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            numberButtons[i].addActionListener(new NumberButtonActionListener());
            buttonPanel.add(numberButtons[i]);
        }

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton solveButton = new JButton("=");
        JButton modButton = new JButton("%");
        JButton clearButton = new JButton("CE");
        JButton backButton = new JButton("←");
        JButton deleteButton = new JButton("→");
        JButton squareButton = new JButton("x²");
        JButton sqrtButton = new JButton("√");

        addButton.setFont(new Font("Arial", Font.PLAIN, 18));
        subtractButton.setFont(new Font("Arial", Font.PLAIN, 18));
        multiplyButton.setFont(new Font("Arial", Font.PLAIN, 18));
        divideButton.setFont(new Font("Arial", Font.PLAIN, 18));
        solveButton.setFont(new Font("Arial", Font.PLAIN, 18));
        modButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 18));
        squareButton.setFont(new Font("Arial", Font.PLAIN, 18));
        sqrtButton.setFont(new Font("Arial", Font.PLAIN, 18));

        addButton.addActionListener(new OperatorButtonActionListener("+"));
        subtractButton.addActionListener(new OperatorButtonActionListener("-"));
        multiplyButton.addActionListener(new OperatorButtonActionListener("*"));
        divideButton.addActionListener(new OperatorButtonActionListener("/"));
        solveButton.addActionListener(new EqualsButtonActionListener());
        modButton.addActionListener(new OperatorButtonActionListener("%"));
        clearButton.addActionListener(new ClearButtonActionListener());
        backButton.addActionListener(new BackButtonActionListener());
        deleteButton.addActionListener(new DeleteButtonActionListener());
        squareButton.addActionListener(new SquareButtonActionListener());
        sqrtButton.addActionListener(new SqrtButtonActionListener());

        buttonPanel.add(squareButton);
        buttonPanel.add(sqrtButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(addButton);
        buttonPanel.add(subtractButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(solveButton);
        buttonPanel.add(modButton);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(display)
                        .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(display, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                }
                display.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
    }

    private class ClearButtonActionListener implements ActionListener {
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

    private class DeleteButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    private class SquareButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double input = Double.parseDouble(display.getText());
                result = input * input;
                display.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
    }

    private class SqrtButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double input = Double.parseDouble(display.getText());
                if (input >= 0) {
                    result = Math.sqrt(input);
                    display.setText(String.valueOf(result));
                } else {
                    display.setText("Error");
                }
            } catch (NumberFormatException ex) {
                display.setText("Error");
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