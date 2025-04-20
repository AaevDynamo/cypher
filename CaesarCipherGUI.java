import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaesarCipherGUI extends JFrame implements ActionListener {
    private JTextField inputField, keyField, outputField;
    private JButton encryptButton, decryptButton;

    public CaesarCipherGUI() {
        setTitle("Caesar Cipher Converter");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create components
        inputField = new JTextField(20);
        keyField = new JTextField(5);
        outputField = new JTextField(20);
        outputField.setEditable(false); // Make output field read-only

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        // Set layout
        setLayout(new GridLayout(4, 2));

        // Add components to the frame
        add(new JLabel("Input Text:"));
        add(inputField);
        add(new JLabel("Key (-26 to 26):"));
        add(keyField);
        add(new JLabel("Output Text:"));
        add(outputField);
        add(encryptButton);
        add(decryptButton);

        // Add action listeners
        encryptButton.addActionListener(this);
        decryptButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        int key = Integer.parseInt(keyField.getText());

        if (e.getSource() == encryptButton) {
            String encryptedText = encrypt(input, key);
            outputField.setText(encryptedText);
        } else if (e.getSource() == decryptButton) {
            String decryptedText = decrypt(input, key);
            outputField.setText(decryptedText);
        }
    }

    private String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                encryptedText.append((char) (((ch - base + key) % 26) + base));
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    private String decrypt(String text, int key) {
        return encrypt(text, 26 - key);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CaesarCipherGUI gui = new CaesarCipherGUI();
            gui.setVisible(true);
        });
    }
}
