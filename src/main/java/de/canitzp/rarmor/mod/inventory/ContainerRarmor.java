/*
 * This file ("ContainerRarmor.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.mod.inventory;

import de.canitzp.rarmor.api.inventory.RarmorModuleContainer;
import de.canitzp.rarmor.api.module.IActiveRarmorModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerRarmor extends Container{

    public final RarmorModuleContainer container;

    public ContainerRarmor(EntityPlayer player, IActiveRarmorModule module){
        this.container = module.createContainer(player, this);

        for(Slot slot : this.container.getSlots()){
            this.addSlotToContainer(slot);
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 9; j++){
                this.addSlotToContainer(new Slot(player.inventory, j+(i+1)*9, 38+j*18, 147+i*18));
            }
        }

        for(int i = 0; i < 9; i++){
            this.addSlotToContainer(new Slot(player.inventory, i, 38+i*18, 205));
        }
    }

    @Override
    public void addListener(IContainerListener listener){
        super.addListener(listener);
        this.container.addListener(listener);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void removeListener(IContainerListener listener){
        super.removeListener(listener);
        this.container.removeListener(listener);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn){
        return true;
    }

    @Override
    public void detectAndSendChanges(){
        super.detectAndSendChanges();

        this.container.detectAndSendChanges();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index){
        return this.container.transferStackInSlot(player, index);
    }

    @Override
    public void onContainerClosed(EntityPlayer player){
        super.onContainerClosed(player);
        this.container.onContainerClosed(player);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory){
        super.onCraftMatrixChanged(inventory);
        this.container.onCraftMatrixChanged(inventory);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data){
        super.updateProgressBar(id, data);
        this.container.updateProgressBar(id, data);
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickType, EntityPlayer player){
        ItemStack stack = this.container.slotClick(slotId, dragType, clickType, player);
        if(stack != null){
            return stack;
        }
        return super.slotClick(slotId, dragType, clickType, player);
    }
}