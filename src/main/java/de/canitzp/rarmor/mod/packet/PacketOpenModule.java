/*
 * This file ("PacketOpenGui.java") is part of the Rarmor mod for Minecraft.
 * It is created by Ellpeck and owned by canitzp & Ellpeck and distributed
 * under the Rarmor License to be found at
 * https://github.com/Ellpeck/Rarmor/blob/master/LICENSE.md
 * View the source code at https://github.com/Ellpeck/Rarmor
 *
 * © 2015-2016 canitzp & Ellpeck
 */

package de.canitzp.rarmor.mod.packet;

import de.canitzp.rarmor.api.internal.IRarmorData;
import de.canitzp.rarmor.mod.Rarmor;
import de.canitzp.rarmor.mod.data.RarmorData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenModule implements IMessage{

    public int moduleId;
    public boolean alsoSetData;

    public PacketOpenModule(){

    }

    public PacketOpenModule(int moduleId, boolean alsoSetData){
        this.moduleId = moduleId;
        this.alsoSetData = alsoSetData;
    }

    @Override
    public void fromBytes(ByteBuf buf){
        this.moduleId = buf.readInt();
        this.alsoSetData = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeInt(this.moduleId);
        buf.writeBoolean(this.alsoSetData);
    }

    public static class Handler implements IMessageHandler<PacketOpenModule, IMessage>{

        @Override
        public IMessage onMessage(PacketOpenModule message, MessageContext context){
            System.out.println("WJOIEF");
            EntityPlayerMP player = context.getServerHandler().playerEntity;
            if(message.alsoSetData){
                IRarmorData data = RarmorData.getDataForChestplate(player);
                if(data != null){
                    data.selectModule(message.moduleId);
                }
            }
            player.openGui(Rarmor.instance, message.moduleId, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
            return null;
        }
    }
}
