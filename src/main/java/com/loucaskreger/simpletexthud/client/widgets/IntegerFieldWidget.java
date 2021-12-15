package com.loucaskreger.simpletexthud.client.widgets;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.LiteralText;
import org.jetbrains.annotations.Nullable;

public class IntegerFieldWidget extends TextFieldWidget {

    private int minValue;
    private int maxValue;
    private int defaultValue;


    public IntegerFieldWidget(TextRenderer textRenderer, int x, int y, int minValue, int maxValue, int defaultValue) {
        super(textRenderer, x, y, 50, 12, new LiteralText(""));
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
    }

    public IntegerFieldWidget(TextRenderer textRenderer, int x, int y, int minValue, int maxValue) {
        this(textRenderer, x, y, minValue, maxValue, 0);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (Character.isDigit(chr)) {
            int nextNumber = Integer.parseInt(String.format("%s%c", this.getText(), chr));
            if ((nextNumber < this.maxValue && nextNumber > this.minValue)) {
                return super.charTyped(chr, modifiers);
            } else if (nextNumber < minValue) {
                nextNumber = minValue;
            } else if (nextNumber > maxValue) {
                nextNumber = maxValue;
            }
            this.setText(Integer.toString(nextNumber));
        }
        return false;
    }

    /**
     * @return The current text as an integer.
     */
    public int getInt() {
        if (!this.getText().isEmpty()) {
            return Integer.parseInt(this.getText());
        }
        return this.minValue;
    }
}
