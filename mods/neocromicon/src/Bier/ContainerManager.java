package mods.neocromicon.src.Bier;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public abstract class ContainerManager extends BlockContainer
{
    public ContainerManager(int var1, int var2, Material var3)
    {
        super(var1, var3);
    }

    public ContainerManager(int var1, Material var2)
    {
        super(var1, var2);
    }

    public abstract TileEntity getBlockEntity();
}
