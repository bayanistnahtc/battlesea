package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * Created by R2 on 28.07.2015.
 */
public class Painting extends JFrame {

    public Painting() {
        super("painting");
        uI();

    }

    public void uI(){
        setTitle("Battle Sea");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        add(panel, BorderLayout.CENTER);
        JButton btnNewGame = new JButton("New game");
        JButton btnLoadGame = new JButton("Load game");
        panel.add(btnNewGame);
        panel.add(btnLoadGame);
        JTextField t = new JTextField(20);


//        for(int i=0;i<3;i++){
//            for(int j=0;j<3;j++)
//            {
//                SwingComponent sc=new SwingComponent(i,j);
//                panel.add(sc);
//            }
//        }

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel2 = new JPanel();
                panel2.add(new JButton("gogo"));
                add(panel2, BorderLayout.CENTER);
            }
        });

        setVisible(true);
    }
    class SwingComponent extends JComponent {

        int i, j;

        public SwingComponent(int i, int j) {
            this.i = i;
            this.j = j;
        }

        // прорисовка компонента
        public void paintComponent(Graphics g) {
            g.drawRect(0, 0, this.getWidth(), this.getHeight());
            {
  //              g.setColor(Color.getHSBColor(0, 1, 1));
   //             g.fillOval(0, 0, this.getWidth(), this.getHeight());
            }
        }
    }

}
