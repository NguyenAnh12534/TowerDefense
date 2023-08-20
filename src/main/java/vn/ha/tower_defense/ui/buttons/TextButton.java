package vn.ha.tower_defense.ui.buttons;

import java.awt.Graphics;

public class TextButton extends GameButton {
    private String text;

    public TextButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
        this.text = text;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawText(g);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int ascent = g.getFontMetrics().getAscent();
        int descent = g.getFontMetrics().getDescent();
        int leading = g.getFontMetrics().getLeading();
        int stringHeight = ascent + descent + leading;

        g.drawString(this.text, super.getX() + super.getWidth() / 2 + - w / 2, super.getY() + super.getHeight() / 2 + stringHeight / 3);

    }
    
    @Override
    public String getText() {
        return this.text;
    }

}
