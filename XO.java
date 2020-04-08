package Lesson8;

import javax.swing.*;
import java.awt.*;

public class XO extends JFrame {

    private final ImageIcon imageX = new ImageIcon("src/main/java/Lesson8/x.jpg");
    private final ImageIcon imageO = new ImageIcon("src/main/java/Lesson8/o.jpg");
    private final ImageIcon imageEmpty = new ImageIcon("src/main/java/Lesson8/empty.jpg");
    private final String youWin = "Вы выиграли";
    private final String youLose = "Вы проиграли";
    private final String noOne = "Ничья";

    public XO() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500, 400);
        setSize(300, 300);
        setResizable(false);
        JPanel panel = new JPanel(new GridLayout(3, 3));
        JButton[][] buttons = new JButton[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j] = new JButton();
                    buttons[i][j].setIcon(imageEmpty);
                    JButton copy = buttons[i][j];
                    copy.addActionListener(action -> {
                        copy.setIcon(imageX);
                        copy.setEnabled(false);
                        copy.setDisabledIcon(imageX);
                        if (isVictory(buttons, imageX)) {
                            continueGame(youWin);
                            return;
                        }
                        movePC(buttons);
                        if (isVictory(buttons, imageO)) {
                            continueGame(youLose);
                            return;
                        }
                        if (isFull(buttons)) {
                            continueGame(noOne);
                        }
                    });
                    panel.add(buttons[i][j]);
                }
            }
        add(panel);
        setVisible(true);
    }

    private void movePC(JButton[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = (int) (Math.random() * 3);
                int y = (int) (Math.random() * 3);
                if (buttons[x][y].isEnabled()) {
                    buttons[x][y].setIcon(imageO);
                    buttons[x][y].setEnabled(false);
                    buttons[x][y].setDisabledIcon(imageO);
                    return;
                }
            }
        }
    }

    private boolean isFull(JButton[][] buttons) {      // НИЧЬЯ
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].isEnabled())
                    return false;
            }
        }
        return true;
    }


    private boolean isVictory(JButton[][] buttons, ImageIcon image) {  //Проверка победы
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[0][0].getIcon() == image && buttons[0][1].getIcon() == image && buttons[0][2].getIcon() == image) return true;
                if (buttons[1][0].getIcon() == image && buttons[1][1].getIcon() == image && buttons[1][2].getIcon() == image) return true;
                if (buttons[2][0].getIcon() == image && buttons[2][1].getIcon() == image && buttons[2][2].getIcon() == image) return true;
                if (buttons[0][0].getIcon() == image && buttons[1][0].getIcon() == image && buttons[2][0].getIcon() == image) return true;
                if (buttons[0][1].getIcon() == image && buttons[1][1].getIcon() == image && buttons[2][1].getIcon() == image) return true;
                if (buttons[0][2].getIcon() == image && buttons[1][2].getIcon() == image && buttons[2][2].getIcon() == image) return true;
                if (buttons[0][0].getIcon() == image && buttons[1][1].getIcon() == image && buttons[2][2].getIcon() == image) return true;
                if (buttons[2][0].getIcon() == image && buttons[1][1].getIcon() == image && buttons[0][2].getIcon() == image) return true;
            }
        }
        return false;
    }

    private void continueGame(String result) {
        JFrame alert = new JFrame(result);
        alert.setLocation(500, 400);
        alert.setSize(300, 100);
        JPanel alertPanel = new JPanel(new FlowLayout());
        JButton newGame = new JButton("new game");
        newGame.addActionListener(a -> {
            this.dispose();
            new XO();
            alert.dispose();
        });
        JButton close = new JButton("close");
        close.addActionListener(a -> {
            dispose();
            alert.dispose();
        });
        alertPanel.add(newGame);
        alertPanel.add(close);
        alert.add(alertPanel);
        alert.setResizable(false);
        alert.setVisible(true);
    }

    public static void main(String[] args) {
        /* Window win = */
        new XO();
    }
}