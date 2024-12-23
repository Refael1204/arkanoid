//  Refael Avrahami 206482655
package Default;

import Collides.SpriteCollection;
import Interface.Animation;
import Interface.LevelInformation;
import biuoop.DrawSurface;
import geometryShape.Point;

import java.awt.Color;
/**
 * count down from X to zero (in our example from 3 to 0).
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private boolean stop;
    private final long beginTime;
    private long relativeTime;
    private final long beginRelativeTime;
    private final LevelInformation levelInfo;

    /**
     * Constructor for countdown.
     * @param numOfSeconds seconds.
     * @param countFrom count from this to zero.
     * @param gameScreen where to display.
     * @param levelInfo info of level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, LevelInformation levelInfo) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.beginTime = System.currentTimeMillis();
        beginRelativeTime = (long) (1000 * this.numOfSeconds / this.countFrom);
        relativeTime = beginRelativeTime;
        this.levelInfo = levelInfo;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.drawBackground(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawCircle((d.getWidth() / 2), d.getHeight() / 2  - 10, 25);
        d.setColor(Color.cyan.darker().darker());
        d.fillCircle((d.getWidth() / 2), d.getHeight() / 2 - 10, 25);
        d.setColor(Color.white);
        d.drawText((d.getWidth() / 2) - 8, d.getHeight() / 2, countFrom + "", 30);
        long passedTime = System.currentTimeMillis() - beginTime;
        if (passedTime > relativeTime) {
            countFrom--;
            relativeTime += beginRelativeTime;
        }
        if (countFrom == 0) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * @param d drawing the background.
     */
    public void drawBackground(DrawSurface d) {
        if (this.levelInfo.levelName().equals("Direct Hit")) {
            geometryShape.Point sun = new Point(400, 170);
            this.levelInfo.getBackground().drawOn(d);
            int radius = 40;
            d.drawLine((int) sun.getX(), (int) sun.getY() - radius, (int) sun.getX(), (int) sun.getY() + radius);
            d.drawLine((int) sun.getX() - radius, (int) sun.getY(), (int) sun.getX() + radius, (int) sun.getY());
            d.setColor(Color.BLUE);
            d.drawCircle((int) sun.getX(), (int) sun.getY(), radius);
            d.setColor(Color.BLUE);
            d.drawCircle((int) sun.getX(), (int) sun.getY(), radius + 33);
            d.setColor(Color.BLUE);
            d.drawCircle((int) sun.getX(), (int) sun.getY(), radius + 66);
            d.setColor(Color.BLUE);
            d.drawLine(295, 170, 400, 170);
            d.drawLine(400, 170, 505, 170);
            d.drawLine(400, 170, 400, 275);
            d.drawLine(400, 170, 400, 65);
        }
            if (this.levelInfo.levelName().equals("Easy Level")) {
                Color color;
                this.levelInfo.getBackground().drawOn(d);
                int j = 0;
                color = new Color(255, 255, 180);
                d.setColor(color);
                for (int i = 25; i < 100; i++) {
                    d.drawLine(100, 120, j, 245);
                    j += 12;
                }
                d.fillCircle(100, 120, 55);
                color = new Color(255, 255, 120);
                d.setColor(color);
                d.fillCircle(100, 120, 45);
                color = new Color(255, 255, 60);
                d.setColor(color);
                d.fillCircle(100, 120, 40);
            }

        if (this.levelInfo.levelName().equals("Medium Level")) {
            this.levelInfo.getBackground().drawOn(d);
            // building
            d.setColor(Color.black);
            d.fillRectangle(70, 487, 90, 120);
            Color color;
            d.setColor(Color.red.darker().darker());
            for (int i = 78; i < 144; i += 16) {
                for (int j = 495; j < 600; j += 22) {
                    if (i == 78 && j == 495) {
                        color = new Color(180, 50, 50);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 517) || (i == 94 && j == 495)) {
                        color = new Color(180, 120, 50);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 539) || (i == 94 && j == 517) || (i == 110 && j == 495)) {
                        color = new Color(255, 255, 120);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 561) || (i == 94 && j == 539) || (i == 110 && j == 517)
                            || (i == 126 && j == 495)) {
                        color = new Color(160, 255, 120);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 78 && j == 583) || (i == 94 && j == 561) || (i == 110 && j == 539)
                            || (i == 126 && j == 517) || (i == 142 && j == 495)) {
                        color = new Color(100, 150, 100);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 94 && j == 583) || (i == 110 && j == 561) || (i == 126 && j == 539)
                            || (i == 142 && j == 517)) {
                        color = new Color(183, 236, 242);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 110 && j == 583) || (i == 126 && j == 561) || (i == 142 && j == 539)) {
                        color = new Color(200, 150, 200);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if ((i == 126 && j == 583) || (i == 142 && j == 561)) {
                        color = new Color(180, 160, 255);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    } else if (i == 142 && j == 583) {
                        color = new Color(90, 60, 130);
                        d.setColor(color);
                        d.fillRectangle(i, j, 10, 15);
                    }
                }
            }
            d.setColor(Color.black);
            d.fillRectangle(111, 200, 8, 250);
            d.fillRectangle(105, 450, 20, 37);
            d.setColor(Color.yellow);
            d.fillCircle(115, 200, 10);
            d.setColor(Color.BLACK);
        }
        if (this.levelInfo.levelName().equals("Hard Level")) {
            this.levelInfo.getBackground().drawOn(d);
            Color color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(690, 295, 35);
            d.drawLine(690, 295, 650, 600);
            d.drawLine(660, 315, 620, 620);
            d.drawLine(610, 335, 570, 640);
            d.drawLine(630, 355, 590, 660);
            color = new Color(183, 236, 242);
            d.setColor(color);
            d.fillCircle(600, 300, 35);
            d.fillCircle(635, 320, 40);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(650, 260, 35);


            d.fillCircle(450, 360, 35);
            d.drawLine(400, 400, 370, 600);
            d.drawLine(425, 425, 395, 625);
            d.drawLine(450, 450, 420, 650);
            d.drawLine(485, 400, 455, 600);
            d.fillCircle(490, 395, 35);
            color = new Color(183, 236, 242);
            d.setColor(color);
            d.fillCircle(400, 400, 35);
            d.fillCircle(435, 420, 40);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(450, 360, 35);

            color = new Color(169, 232, 239);
            d.setColor(color);
            d.fillCircle(660, 195, 35);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(200, 300, 35);
            d.drawLine(270, 300, 240, 600);
            d.drawLine(245, 300, 215, 600);
            d.drawLine(220, 300, 190, 600);
            d.drawLine(195, 300, 165, 600);

            color = new Color(169, 232, 239);
            d.setColor(color);
            d.fillCircle(230, 350, 35);
            d.fillCircle(255, 300, 35);
            color = new Color(211, 244, 248);
            d.setColor(color);
            d.fillCircle(230, 260, 35);
        }
    }
}
