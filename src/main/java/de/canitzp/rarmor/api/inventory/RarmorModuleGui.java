/*
 * This file ("IRarmorModuleGui.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.api.inventory;

import de.canitzp.rarmor.api.module.IActiveRarmorModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.List;

@SideOnly(Side.CLIENT)
public class RarmorModuleGui extends Gui{

    public List<GuiButton> buttonList;
    public final IActiveRarmorModule module;
    public Minecraft mc;

    public int guiLeft;
    public int guiTop;

    public RarmorModuleGui(IActiveRarmorModule module){
        this.module = module;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{

    }

    public void mouseReleased(int mouseX, int mouseY, int state){

    }

    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick){

    }

    public void actionPerformed(GuiButton button) throws IOException{

    }

    public void initGui(){

    }

    public void updateScreen(){

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks){

    }

    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){

    }
}
