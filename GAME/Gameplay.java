//import javax.swing.*;
//import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Gameplay extends JPanel implements KeyListener {
    /*In this class we're going to combine the whole game*/

    public Gameplay(int positionA, int positionB, int width, int height){
        this.setBounds(positionA, positionB, width, height);

        this.setBackground(Color.blue);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
