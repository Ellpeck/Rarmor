package de.canitzp.rarmor.items.rfarmor;

import de.canitzp.rarmor.api.IRarmorModule;
import de.canitzp.rarmor.inventory.slots.SlotModule;
import de.canitzp.util.util.EnergyUtil;
import de.canitzp.util.util.NBTUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * @author canitzp
 */
public class ItemModuleSolarPanel extends ItemModule implements IRarmorModule {

    public ItemModuleSolarPanel() {
        super("moduleSolarPanel");
    }

    @Override
    public String getUniqueName() {
        return "SolarPanel";
    }

    @Override
    public void onModuleTickInArmor(World world, EntityPlayer player, ItemStack armorChestplate, ItemStack module, IInventory inventory) {
        if(NBTUtil.getInteger(module, "tick") >= 50){
            if(canPlayerSeeSky(player)){
                EnergyUtil.addEnergy(armorChestplate, 4, armorChestplate.getMaxDamage());
                NBTUtil.setInteger(module, "tick", 0);
                NBTUtil.setBoolean(module, "doWork", true);
            } else {
                NBTUtil.setBoolean(module, "doWork", false);
            }
        } else {
            NBTUtil.setInteger(module, "tick", NBTUtil.getInteger(module, "tick") + 1);
            if(NBTUtil.getBoolean(module, "doWork")){
                EnergyUtil.addEnergy(armorChestplate, 4, armorChestplate.getMaxDamage());
            }
        }
    }

    private boolean canPlayerSeeSky(EntityPlayer player) {
        if(!player.worldObj.isRaining() && player.worldObj.isDaytime()){
            for(int i = (int) player.posY + 1; i <= 256; i++){
                Block block = player.worldObj.getBlockState(new BlockPos(player.posX, i, player.posZ)).getBlock();
                if(block != null && ((block.isFullBlock() || block instanceof BlockLiquid) && !player.worldObj.isAirBlock(new BlockPos(player.posX, i, player.posZ)))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}