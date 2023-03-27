import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    //FEATURES
    public final int unitSize = 15;
    public final int colNum = 34;
    public final int rowNum = 50;
    public final int gpWidth =colNum * unitSize;
    public final int gpHeight =rowNum * unitSize;

    private final BufferedImage bg;

    //SYSTEM
    int FPS = 10;
    Thread gameThread;
    Observer observer ;
    DoodleExecutor doodleExecutor;
    KeyHandler keyHandler = new KeyHandler();
    WorldCreator worldCreator;

    Screen screen;

    //ASSETS
    AssetManager assetManager ;

    public GamePanel(){
        this.setPreferredSize(new Dimension(gpWidth, gpHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        //width 510
        //height 750

        try {
            bg= ImageIO.read(getClass().getResourceAsStream("/backGround/bg.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assetManager = new AssetManager(this);

        worldCreator = new WorldCreator(this);

        observer = new Observer(this);

        doodleExecutor = new DoodleExecutor(this);

        screen = new Screen(this);


    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        //long timer = 0;
        //int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            //timer+= (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
               // drawCount++;
            }

        /*if(timer >= 1000000000){
            System.out.println("FPS:" + drawCount);
            drawCount=0;
            timer=0;
        }*/

        }

    }

    public void update(){

        observer.observe();

        assetManager.update();

        doodleExecutor.execute();

        screen.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(bg,0,0, gpWidth, gpHeight,null);

        assetManager.draw(g2);

        g2.dispose();
    }


}
