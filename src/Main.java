import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Doodle");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();



    }
}



interface B{

    public default void x(){
        System.out.println("x");
    }

}

interface C{

}

class A implements B,C {


    A(){x();}

    @Override
    public void x() {
        System.out.println("s");
    }
}

