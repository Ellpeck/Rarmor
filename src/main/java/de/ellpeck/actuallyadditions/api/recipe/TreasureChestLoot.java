/*
 * This file ("TreasureChestLoot.java") is part of the Actually Additions Mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense/
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2016 Ellpeck
 */

package de.ellpeck.actuallyadditions.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

public class TreasureChestLoot extends WeightedRandom.Item{

    public ItemStack returnItem;
    public int minAmount;
    public int maxAmount;

    public TreasureChestLoot(ItemStack returnItem, int chance, int minAmount, int maxAmount){
        super(chance);
        this.returnItem = returnItem;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

}
