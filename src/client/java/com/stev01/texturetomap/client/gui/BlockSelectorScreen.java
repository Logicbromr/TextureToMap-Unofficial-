package com.stev01.texturetomap.client.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockSelectorScreen extends Screen {
    private TextFieldWidget searchField;
    private List<Block> availableBlocks;
    private List<Block> filteredBlocks;
    private int scrollOffset = 0;
    private Block selectedBlock = null;
    private static final int BLOCKS_PER_PAGE = 10;

    public BlockSelectorScreen(Text title) {
        super(title);
        this.availableBlocks = new ArrayList<>();
        this.filteredBlocks = new ArrayList<>();
        this.loadAllBlocks();
    }

    private void loadAllBlocks() {
        Registries.BLOCK.forEach(block -> availableBlocks.add(block));
        this.filteredBlocks.addAll(availableBlocks);
    }

    @Override
    protected void init() {
        this.searchField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 20, 200, 20, Text.literal("Search blocks..."));
        this.searchField.setMaxLength(50);
        this.addDrawableChild(this.searchField);

        // Convert button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Convert Selected"), button -> {
            if (selectedBlock != null) {
                convertBlockTexture(selectedBlock);
                this.close();
            }
        }).dimensions(this.width / 2 - 100, this.height - 40, 200, 20).build());

        // Close button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Cancel"), button -> this.close())
            .dimensions(this.width / 2 - 100, this.height - 60, 200, 20).build());
    }

    @Override
    public void tick() {
        this.searchField.tick();
        String searchText = this.searchField.getText().toLowerCase();

        // Filter blocks based on search
        filteredBlocks.clear();
        for (Block block : availableBlocks) {
            String blockName = Registries.BLOCK.getId(block).toString().toLowerCase();
            if (blockName.contains(searchText)) {
                filteredBlocks.add(block);
            }
        }

        // Reset scroll if needed
        if (scrollOffset > Math.max(0, filteredBlocks.size() - BLOCKS_PER_PAGE)) {
            scrollOffset = Math.max(0, filteredBlocks.size() - BLOCKS_PER_PAGE);
        }
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);

        // Title
        context.drawCenteredTextWithShadow(this.textRenderer, "Block Selector", this.width / 2, 10, 0xFFFFFF);

        // Draw block list
        int y = 50;
        int blockListHeight = this.height - 100;
        int endBlock = Math.min(scrollOffset + BLOCKS_PER_PAGE, filteredBlocks.size());

        for (int i = scrollOffset; i < endBlock; i++) {
            Block block = filteredBlocks.get(i);
            String blockName = Registries.BLOCK.getId(block).toString();
            boolean isSelected = block == selectedBlock;
            int color = isSelected ? 0xFF55FF55 : 0xFFAAAAAA;

            // Draw background for selected
            if (isSelected) {
                context.fill(this.width / 2 - 100, y - 2, this.width / 2 + 100, y + 12, 0xFF2a2a2a);
            }

            context.drawText(this.textRenderer, blockName, this.width / 2 - 95, y, color, false);

            // Check if clicked
            if (mouseX >= this.width / 2 - 100 && mouseX <= this.width / 2 + 100 &&
                mouseY >= y - 2 && mouseY <= y + 12) {
                if (isSelected) {
                    context.drawBorder(this.width / 2 - 102, y - 4, 204, 16, 0xFF55FF55);
                }
            }

            y += 15;
        }

        // Draw scrollbar info
        context.drawText(this.textRenderer, String.format("Showing %d/%d", endBlock, filteredBlocks.size()), 
            this.width / 2 - 95, y + 5, 0xFFFFFF, false);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        scrollOffset = Math.max(0, Math.min(scrollOffset - (int) verticalAmount, Math.max(0, filteredBlocks.size() - BLOCKS_PER_PAGE)));
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }

        // Handle block selection
        int y = 50;
        int endBlock = Math.min(scrollOffset + BLOCKS_PER_PAGE, filteredBlocks.size());

        for (int i = scrollOffset; i < endBlock; i++) {
            if (mouseX >= this.width / 2 - 100 && mouseX <= this.width / 2 + 100 &&
                mouseY >= y - 2 && mouseY <= y + 12) {
                selectedBlock = filteredBlocks.get(i);
                return true;
            }
            y += 15;
        }

        return false;
    }

    private void convertBlockTexture(Block block) {
        String blockName = Registries.BLOCK.getId(block).toString();
        assert this.client != null;
        if (this.client.player != null) {
            this.client.player.sendMessage(Text.literal("§6Converting texture for: " + blockName), false);
        }
        // TODO: Add actual texture conversion logic here
    }

    @Override
    public void close() {
        this.client.setScreen(null);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
