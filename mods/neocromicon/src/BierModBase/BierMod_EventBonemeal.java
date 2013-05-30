package mods.neocromicon.src.BierModBase;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BierMod_EventBonemeal
{
    @ForgeSubscribe
    public void onUseBonemeal(BonemealEvent var1)
    {
        if (var1.ID == BierMod.BlockHopfen.blockID && !var1.world.isRemote)
        {
            ;
        }
    }
}
