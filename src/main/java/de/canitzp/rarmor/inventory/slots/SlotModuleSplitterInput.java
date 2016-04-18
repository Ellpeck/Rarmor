package de.canitzp.rarmor.inventory.slots;

import de.canitzp.rarmor.RarmorUtil;
import de.canitzp.rarmor.api.InventoryBase;
import de.canitzp.rarmor.api.modules.IRarmorModule;
import de.canitzp.rarmor.api.slots.SlotUpdate;
import de.canitzp.rarmor.items.ItemRegistry;
import de.canitzp.rarmor.util.NBTUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author canitzp
 */
public class SlotModuleSplitterInput extends SlotUpdate{

    protected ItemStack actualStack;

    public SlotModuleSplitterInput(InventoryBase inventory, int id, int x, int y, EntityPlayer player) {
        super(inventory, id, x, y, player);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof IRarmorModule && stack.getItem() != ItemRegistry.moduleModuleSplitter;
    }

    @Override
    public void onSlotChanged() {
        ItemStack stack = RarmorUtil.getPlayersRarmorChestplate(player);
        if (actualStack != null && !actualStack.isItemEqual(this.getStack())) {
            if (actualStack.getItem() != null && actualStack.getItem() instanceof IRarmorModule) {
                onPickupFromSlot(player, actualStack);
            }
        }
        if (getStack() != null && getStack().getItem() != null) {
            if (getStack().getItem() instanceof IRarmorModule) {
                NBTUtil.setBoolean(stack, "Module" + ((IRarmorModule) getStack().getItem()).getUniqueName(), true);
            }
        }
        actualStack = this.getStack();
        super.onSlotChanged();
    }

    @Override
    public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
        ((IRarmorModule) stack.getItem()).onPickupFromSlot(player.getEntityWorld(), player, RarmorUtil.getPlayersRarmorChestplate(player), stack, inv, this);
        actualStack = null;
    }
}
