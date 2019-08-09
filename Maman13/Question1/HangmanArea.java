
package hangman;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
/**
 * A HangMan game
 * Paint the area of HangMan
 * @author Dmitriy Chudnovsky 324793900
 * @since 16/4/2019
 */
class HangManArea extends JPanel {

    HangMan controller;

    public HangManArea(HangMan controller) {
        this.controller = controller;
        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
        setBorder(compound);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Draw the gallows
        if (controller.verColumn) {
            g.setColor(Color.black);
            g.drawRect(10, 10, 25, 350);
            g.fillRect(11, 11, 24, 349);

        }
        if (controller.gorColumn) {
            g.setColor(Color.black);
            g.drawRect(10, 10, 200, 25);
            g.fillRect(11, 11, 199, 24);
        }
        //Draw the head and noose
        if (controller.headDrawn) {
            g.setColor(Color.gray);
            g.fillRect(210, 35, 5, 100);
            g.setColor(Color.black);
            g.fillOval(180, 70, 70, 70);
            g.setColor(Color.gray);
            g.fillRect(203, 140, 25, 6);
        }

        //Draw the body
        if (controller.bodyDrawn) {
            g.setColor(Color.black);
            g.fillOval(195, 146, 40, 80);
        }

        //Draw the left arm
        if (controller.leftArmDrawn) {
            g.setColor(Color.black);
            // fillPolygon(xPoints, yPoints, numPoints)
            int x6Points[] = {235, 255, 285, 285, 255, 235};
            int y6Points[] = {170, 175, 170, 180, 185, 180};
            g.fillPolygon(x6Points, y6Points, x6Points.length);
        }

        //Draw the right arm
        if (controller.rightArmDrawn) {
            g.setColor(Color.black);
            int x6Points[] = {200, 180, 150, 150, 180, 200};
            int y6Points[] = {170, 175, 170, 180, 185, 180};
            g.fillPolygon(x6Points, y6Points, x6Points.length);
        }

        //Draw the left leg
        if (controller.leftLegDrawn) {
            g.setColor(Color.black);
            int x9Points[] = {220, 250, 270, 270, 280, 280, 270, 250, 220};
            int y9Points[] = {200, 210, 230, 260, 260, 270, 270, 230, 230};
            g.fillPolygon(x9Points, y9Points, x9Points.length);
        }

        //Draw the right leg and finishing touches -- he's dead
        if (controller.rightLegDrawn) {
            g.setColor(Color.black);
            int x9Points[] = {210, 180, 160, 160, 150, 150, 160, 180, 210};
            int y9Points[] = {200, 210, 230, 260, 260, 270, 270, 230, 230};
            g.fillPolygon(x9Points, y9Points, x9Points.length);

        }
    }
}
