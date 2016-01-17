package de.canitzp.rarmor.inventory;

import de.canitzp.rarmor.inventory.container.ContainerRFArmor;
import de.canitzp.rarmor.inventory.gui.GuiRFArmor;
import de.canitzp.rarmor.items.rfarmor.ItemRFArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * @author canitzp
 */
public class GuiHandler implements IGuiHandler {

    public static final int RFArmorGui = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        ItemStack stack = player.getCurrentArmor(ItemRFArmor.ArmorType.BODY.getId()+1);
        switch (ID){
            case RFArmorGui:
                return new ContainerRFArmor(player);
            default: return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        ItemStack stack = player.getCurrentArmor(ItemRFArmor.ArmorType.BODY.getId()+1);
        switch (ID){
            case RFArmorGui:
                return new GuiRFArmor(player, new ContainerRFArmor(player));
            default: return null;
        }
    }
}
