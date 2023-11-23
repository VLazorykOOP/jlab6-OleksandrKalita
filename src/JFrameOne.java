import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;


public class JFrameOne extends JFrame {
    private JFrame frame;

    public JFrameOne() {
        initialize();
    }
    public void initialize() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(10, 5));
        frame.setTitle("MatrixCalculation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        // panel.setBackground(Color.BLACK);

        JLabel label1 = new JLabel("Введіть розмірність матриці (n <= 15)");
        JTextField MatrixSizeField = new JTextField(3);
        Button Button = new Button("submit");
        Button LoadButton = new Button("load data");
        JTextArea ResultField = new JTextArea("Очікуємо результат...");


        Button.setPreferredSize(new Dimension(50, 20));
        Button.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = MatrixSizeField.getText();
                int size = Integer.parseInt(text);

                if(size > 0 && size <= 15) {
                    
                } else {
                    ResultField.setText("Хибне значення, повторіть спробу");
                }

                int[][] X = new int[size][size];
                int[] Y = new int[size];

                fillMatrixRandom(X);

                fillVector(X, Y);
                
                StringBuilder matrixString = new StringBuilder();

                matrixString.append("Матриця \'X\':\n");
                for(int i = 0; i < X.length; i++) {
                    for(int f = 0; f < X.length; f++) {
                        matrixString.append(X[f][i] + "\t");
                    }
                    matrixString.append("\n");
                }

                matrixString.append("\n\nВектор \'Y\':\n");
                for(int element : Y) {
                    matrixString.append(element + " ");
                }

                ResultField.setText(matrixString.toString());
            }
        });

        LoadButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
                    String line = reader.readLine();
                    MatrixSizeField.setText(line);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "File not found!");
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(frame, "Custom arithmetic exception occurred: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage());
                }
            }
        });

        panel.add(label1);
        panel.add(MatrixSizeField);
        panel.add(Button);
        panel.add(LoadButton);
        panel.add(ResultField);

        frame.add(panel);

        frame.setVisible(true);
    }

    // public static void secondTask() {
    //     Scanner scanner = new Scanner(System.in);
    //     int n;

    //     System.out.print("Введіть розмірність матриці (n <= 15): ");
        
    //     do{
    //         n = scanner.nextInt();

    //         if(n > 0 && n <= 15) {
    //             break;
    //         }else {
    //             System.out.print("Хибне значення, повторіть спробу! Введіть розмірність матриці: ");
    //         }
    //     }while(true);

    //     int[][] X = new int[n][n];
    //     int[] Y = new int[n];

    //     fillMatrixRandom(X);

    //     fillVector(X, Y);

    //     System.out.println("Матриця \'X\':");
    //     for(int i = 0; i < X.length; i++) {
    //         for(int f = 0; f < X.length; f++) {
    //             System.out.print(X[f][i] + "\t");
    //         }
    //         System.out.println("\n");
    //     }

    //     System.out.println("Вектор \'Y\':");
    //     for(int element : Y) {
    //         System.out.print(element + " ");
    //     }
    //     scanner.nextLine();
    // }
    public static void fillMatrixRandom(int[][] matrix) {
        Random random = new Random();

        for(int i = 0; i < matrix.length; i++) {
            for(int f = 0;f < matrix.length; f++) {
                matrix[i][f] = random.nextInt(100) - 10;
            }
        }
    }
    public static void fillVector(int[][] matrix, int[] vector) {    
        for(int i = 0; i < matrix.length; i++) {
            int sum = 0;
            boolean isNegativeValue = false;

            for(int f = 0; f < matrix.length; f++) {
                if(matrix[i][f] < 0) {
                    isNegativeValue = true;
                    sum += Math.abs(matrix[i][f]);
                }else if(isNegativeValue) {
                    sum += matrix[i][f];
                }
            }
            vector[i] = isNegativeValue ? sum : -1;
        }
    }
}