package me.flashyreese.mods.sodiumextra.client.gui;

import me.flashyreese.mods.sodiumextra.client.SodiumExtraClientMod;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

public class SuggestRSOScreen extends Screen {

    private static final Text HEADER = Text.translatable("sodium-extra.suggestRSO.header").formatted(Formatting.BOLD);
    private static final Text MESSAGE = Text.translatable("sodium-extra.suggestRSO.message");
    private static final Text CHECK_MESSAGE = Text.translatable("multiplayerWarning.check");
    private CheckboxWidget checkbox;
    private MultilineText lines = MultilineText.EMPTY;
    private final Screen prevScreen;

    public SuggestRSOScreen(Screen prevScreen) {
        super(Text.literal("Reese's Sodium Options Suggestion"));
        this.prevScreen = prevScreen;
    }

    @Override
    protected void init() {
        super.init();
        this.lines = MultilineText.create(this.textRenderer, MESSAGE, this.width - 50);
        int i = (this.lines.count() + 1) * this.textRenderer.fontHeight * 2;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 130 + i, 150, 20, Text.literal("CurseForge"), buttonWidget -> Util.getOperatingSystem().open("https://curseforge.com/minecraft/mc-mods/reeses-sodium-options")));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 160, 130 + i, 150, 20, Text.literal("Modrinth"), buttonWidget -> Util.getOperatingSystem().open("https://modrinth.com/mod/reeses-sodium-options")));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, 100 + i, 150, 20, ScreenTexts.PROCEED, buttonWidget -> {
            if (this.checkbox.isChecked()) {
                SodiumExtraClientMod.options().notificationSettings.hideRSORecommendation = true;
                SodiumExtraClientMod.options().writeChanges();
            }
            this.client.setScreen(this.prevScreen);
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 160, 100 + i, 150, 20, Text.translatable("menu.quit"), buttonWidget -> this.client.scheduleStop()));
        this.checkbox = new CheckboxWidget(this.width / 2 - 155 + 80, 76 + i, 150, 20, CHECK_MESSAGE, false);
        this.addDrawableChild(this.checkbox);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(0);
        drawTextWithShadow(matrices, this.textRenderer, HEADER, 25, 30, 0xFFFFFF);
        this.lines.drawWithShadow(matrices, 25, 70, this.textRenderer.fontHeight * 2, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
