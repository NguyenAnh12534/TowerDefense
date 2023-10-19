package vn.ha.tower_defense.ui.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameButton {
    private int x, y, height, width;
    private String text;
    private Rectangle bound;

    private boolean isHovered = false;
    private boolean isPressed = false;

    public GameButton(int x, int y, int width, int height, String text) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBound();
    }

    public GameButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBound();
    }

    protected void initBound() {
        this.bound = new Rectangle(x, y, width, height);
    }

    public Rectangle getBound() {
        return this.bound;
    }

    public void draw(Graphics g) {
        drawBody(g);
        drawBorder(g);
    }

    protected void drawAffect(Graphics g) {
        if (this.getIsHovered()) {
            this.drawHoverEffect(g);
        }

        if (this.getIsPressed()) {
            this.drawPressedEffect(g);
        }
    }

    public void drawBody(Graphics g) {
        // Fill background color
        g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, this.width, this.height);

        if (isHovered) {
            drawHoverEffect(g);
        }
    }

    public void drawHoverEffect(Graphics g) {
        Color transparentColor = new Color(0, 0, 0, 50); // Adjust alpha value for desired transparency
        g.setColor(Color.black);
        g.drawRect(this.x, this.y, this.width, this.height);
        g.setColor(transparentColor);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    public void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(this.x, this.y, this.width, this.height);
        if (isPressed) {
            drawPressedEffect(g);
        }
    }

    protected void drawPressedEffect(Graphics g) {
        g.setColor(Color.WHITE);

        g.drawRect(this.x + 1, this.y + 1, this.width - 2, this.height - 2);
        g.drawRect(this.x + 2, this.y + 2, this.width - 4, this.height - 4);
    }

    // Getter and Setter

    public void setIsHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getIsHovered() {
        return this.isHovered;
    }

    public boolean getIsPressed() {
        return this.isPressed;
    }

    public String getText() {
        return this.text;
    }
}
