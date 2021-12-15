package com.loucaskreger.simpletexthud.client;

import com.loucaskreger.simpletexthud.client.widgets.IntegerFieldWidget;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.text.LiteralText;

public class EditHudScreen extends Screen {

    private static final MinecraftClient client = MinecraftClient.getInstance();

    private IntegerFieldWidget xPosField;
    private IntegerFieldWidget yPosField;
    private IntegerFieldWidget widgetWidth;
    private IntegerFieldWidget widgetHeight;
    private TextFieldWidget widgetText;

    private FormatStringParser parser;

    public EditHudScreen() {
        super(new LiteralText("Editor"));
        this.parser = new FormatStringParser();
    }

    @Override
    protected void init() {
        super.init();
        this.xPosField = this.addDrawableChild(new IntegerFieldWidget(client.textRenderer, 50, 50, 0, this.width));
        this.yPosField = this.addDrawableChild(new IntegerFieldWidget(client.textRenderer, 50, 65, 0, this.height));
        this.widgetWidth = this.addDrawableChild(new IntegerFieldWidget(client.textRenderer, 50, 80, 0, this.width, 32));
        this.widgetHeight = this.addDrawableChild(new IntegerFieldWidget(client.textRenderer, 50, 95, 0, this.height, 32));
        this.widgetText = this.addDrawableChild(new TextFieldWidget(client.textRenderer, 50, 110, 100, 12, new LiteralText("")));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        RenderSystem.disableDepthTest();
        DrawableHelper.fill(matrices, this.widgetWidth.getInt(), this.widgetHeight.getInt(), this.xPosField.getInt(), this.yPosField.getInt(), 0x90000000);
        this.textRenderer.draw(matrices, this.parser.parse(this.widgetText.getText()), 100, 100, 0xFFFFFF);
        RenderSystem.enableDepthTest();
    }

    @Override
    public void tick() {
        FormatStringParser.tick();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
