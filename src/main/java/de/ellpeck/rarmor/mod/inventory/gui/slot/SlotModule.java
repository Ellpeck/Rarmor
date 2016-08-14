/*
 * This file ("SlotModule.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.ellpeck.rarmor.mod.inventory.gui.slot;

import de.ellpeck.rarmor.api.internal.IRarmorData;
import de.ellpeck.rarmor.api.module.ActiveRarmorModule;
import de.ellpeck.rarmor.api.module.IRarmorModuleItem;
import de.ellpeck.rarmor.mod.inventory.ContainerRarmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class SlotModule extends Slot{

    private final IRarmorData currentData;
    private final EntityPlayer player;
    private final ContainerRarmor container;
    private final int uniqueModuleSlotId;

    public SlotModule(IInventory inventory, EntityPlayer player, IRarmorData currentData, ContainerRarmor container, int uniqueModuleSlotId, int index, int xPosition, int yPosition){
        super(inventory, index, xPosition, yPosition);
        this.player = player;
        this.currentData = currentData;
        this.container = container;
        this.uniqueModuleSlotId = uniqueModuleSlotId;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack someStack){
        super.onPickupFromSlot(player, someStack);

        this.uninstallModule();
    }

    @Override
    public void putStack(ItemStack newStack){
        if(!this.container.isPuttingStacksInSlots){
            ItemStack stack = this.getStack();
            this.uninstallModule();
        }

        super.putStack(newStack);

        if(!this.container.isPuttingStacksInSlots){
            if(newStack != null){
                this.installModule(newStack);
            }
        }
    }

    @Override
    public ItemStack decrStackSize(int amount){
        ItemStack toReturn = super.decrStackSize(amount);

        ItemStack stack = this.getStack();
        if(stack == null || stack.stackSize <= 0){
            this.uninstallModule();
        }

        return toReturn;
    }

    private void installModule(ItemStack stack){
        this.currentData.installModule(stack, this.player, this.uniqueModuleSlotId);
    }

    private void uninstallModule(){
        this.currentData.uninstallModule(getActiveModule(this.uniqueModuleSlotId, this.currentData), this.player, this.uniqueModuleSlotId);
    }

    public static ActiveRarmorModule getActiveModule(int slotIndex, IRarmorData data){
        String identifier = data.getSlotToModuleMap().get(slotIndex);
        if(identifier != null){
            List<ActiveRarmorModule> modules = data.getCurrentModules();
            for(ActiveRarmorModule module : modules){
                if(identifier.equals(module.getIdentifier())){
                    return module;
                }
            }
        }
        return null;
    }

    @Override
    public boolean isItemValid(ItemStack stack){
        return stack.getItem() instanceof IRarmorModuleItem && ((IRarmorModuleItem)stack.getItem()).canInstall(this.player, this);
    }

    @Override
    public boolean canTakeStack(EntityPlayer player){
        ItemStack stack = this.getStack();
        ActiveRarmorModule module = getActiveModule(this.uniqueModuleSlotId, this.currentData);
        return stack == null || module == null || !(stack.getItem() instanceof IRarmorModuleItem) || ((IRarmorModuleItem)stack.getItem()).canUninstall(player, this, module);
    }
}