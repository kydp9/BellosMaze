package visual.graphics;

import bellos.Direction;
import visual.MazeVisual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextHitInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImagePanel extends JPanel {



    private BufferedImage image;

    public static final int STATE_EMPTY = 0;
    public static final int STATE_OCCUPIED = 1;
    private JLabel myLabel;
    private int state;

    private Direction direction;



    public MyImagePanel(int state, Direction direction) {

        this.state = state;
        this.direction = direction;

    image = BufferedImageArray.getBufferedImage(this.direction.ordinal());
    setLayout(new BorderLayout (5,5));
    myLabel=new JLabel(new ImageIcon(image));
    add(myLabel);

}

    // very important!
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(this), image.getHeight(this));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 50, 50, this); // see javadoc for more info on the parameters
    }

    public void setMyLabel(JLabel myLabel) {
        this.myLabel = myLabel;
    }

    public JLabel getMyLabel() {
        return myLabel;
    }

    public void setImage(int i){
        myLabel.setIcon(new ImageIcon(BufferedImageArray.getBufferedImage(i)));
    }
    @Override
    public void repaint() {
        if(direction != null && BufferedImageArray.getBufferedImage(this.direction.ordinal()) != null){
            image = BufferedImageArray.getBufferedImage(this.direction.ordinal());
        }
        super.repaint();

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getDirectionDescription() {
        return direction.getDescription();
    }

    public void flip() {
        int newDirection = direction.ordinal() + 1;
        if (newDirection > 3) {
            newDirection = 0;
        }
        this.setImage(newDirection);
        direction = Direction.class.getEnumConstants()[newDirection];

    }
}
