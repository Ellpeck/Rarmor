/*
 * This file ("ActiveModuleMain.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.mod.module.main;

import de.canitzp.rarmor.api.RarmorAPI;
import de.canitzp.rarmor.api.inventory.RarmorModuleContainer;
import de.canitzp.rarmor.api.inventory.RarmorModuleGui;
import de.canitzp.rarmor.api.module.IActiveRarmorModule;
import de.canitzp.rarmor.mod.inventory.gui.BasicInventory;
import net.minecraft.nbt.NBTTagCompound;

public class ActiveModuleMain implements IActiveRarmorModule{

    public static final String IDENTIFIER = RarmorAPI.MOD_ID+"Main";

    public BasicInventory inventory = new BasicInventory("modules", 3);

    @Override
    public void readFromNBT(NBTTagCompound compound){
        this.inventory.loadSlots(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound){
        this.inventory.saveSlots(compound);
    }

    @Override
    public RarmorModuleContainer createContainer(){
        return new ContainerModuleMain(this);
    }

    @Override
    public RarmorModuleGui createGui(){
        return new GuiModuleMain(this);
    }

    @Override
    public void onInstalled(){

    }

    @Override
    public void onUninstalled(){

    }

    @Override
    public boolean hasTab(){
        return true;
    }
}
