package de.canitzp.rarmor.api.tooltip;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * @author canitzp
 */
public interface IInWorldTooltip {

    @SideOnly(Side.CLIENT)
    default void showTooltip(WorldClient world, EntityPlayerSP player, @Nullable ItemStack stack, ScaledResolution resolution, FontRenderer fontRenderer, RenderGameOverlayEvent.ElementType type, float partialTicks, boolean isHelmet){}

    @SideOnly(Side.CLIENT)
    default TooltipComponent showTooltipAtBlock(WorldClient world, EntityPlayerSP player, @Nullable ItemStack stack, ScaledResolution resolution, FontRenderer fontRenderer, IBlockState state, TileEntity tileEntity, float partialTicks, boolean isHelmet){return null;}

    @SideOnly(Side.CLIENT)
    default TooltipComponent showTooltipAtEntity(WorldClient world, EntityPlayerSP player, @Nullable ItemStack stack, ScaledResolution resolution, FontRenderer fontRenderer, Entity entity, float partialTicks, boolean isHelmet){return null;}

}
