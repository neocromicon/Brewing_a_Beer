package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod$Init;
import cpw.mods.fml.common.Mod$Instance;
import cpw.mods.fml.common.Mod$PostInit;
import cpw.mods.fml.common.Mod$PreInit;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.neocromicon.src.Bier.BlockGerste;
import mods.neocromicon.src.Bier.BlockHopfen;
import mods.neocromicon.src.Bier.BlockHopfenExt;
import mods.neocromicon.src.Bier.BlockWein;
import mods.neocromicon.src.Bier.BlockWeinExt;
import mods.neocromicon.src.Bier.ItemManager;
import mods.neocromicon.src.Bier.ItemSamenGerste;
import mods.neocromicon.src.Bier.ItemSamenHopfen;
import mods.neocromicon.src.Bier.MilchItem;
import mods.neocromicon.src.Bier.SchwarzBierItem;
import mods.neocromicon.src.Bier.WeinItem;
import mods.neocromicon.src.Bier.WeizenBierItem;
import mods.neocromicon.src.BierFass.BlockBierFass;
import mods.neocromicon.src.BierFass.ItemBierFass;
import mods.neocromicon.src.BierFass.TileEntityBierFass;
import mods.neocromicon.src.GaerTank.BlockGaerTank;
import mods.neocromicon.src.GaerTank.RecipesGaerTank;
import mods.neocromicon.src.GaerTank.TileEntityGaerTank;
import mods.neocromicon.src.GasExtraktor.BlockGasExtraktor;
import mods.neocromicon.src.GasExtraktor.RecipesGasExtraktor;
import mods.neocromicon.src.GasExtraktor.TileEntityGasExtraktor;
import mods.neocromicon.src.ZapfFass.BlockZapfFass;
import mods.neocromicon.src.ZapfFass.GasAmpulleLeerItem;
import mods.neocromicon.src.ZapfFass.GasAmpulleVollItem;
import mods.neocromicon.src.ZapfFass.RecipesZapfFass;
import mods.neocromicon.src.ZapfFass.TileEntityZapfFass;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "Brewing_a_Beer", name = "Brewing a Beer", version = "1.4"
)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"BeerModChannel"}, packetHandler = PacketHandler.class
)
public class BierMod
{
    @Instance
    public static BierMod instance = new BierMod();
    public static String modVersion = "1.4";
    public static String modID = "Brewing_a_Beer";
    
    @SidedProxy(clientSide = "mods.neocromicon.src.BierModBase.ClientProxy", serverSide = "mods.neocromicon.src.BierModBase.CommonProxy"
    )
    public static CommonProxy proxy;
    public static CreativeTabs tabBeerCreative = new TabBeerCreative(CreativeTabs.getNextID(), "Brewing a Beer");
    public static Potion DrunkEffekt;
    
    private GuiHandler guiHandler = new GuiHandler();23
    public static int LeeresBierGlasID;
    public static int SchwarzBierID;
    public static int PilsBierID;
    public static int LeeresFassID;
    public static int SchwarzBierExtraktID;
    public static int PilsBierExtraktID;
    public static int ItemSamenGersteID;
    public static int ItemGersteID;
    public static int ItemSamenHopfenID;
    public static int ItemHopfenID;
    public static int BlockGersteID;
    public static int BlockHopfenID;
    public static int BlockHopfenExtID;
    public static int GasAmpulleLeerID;
    public static int GasAmpulleVollID;
    public static int GasExtraktorIdleID;
    public static int GasExtraktorActiveID;
    public static int ZapfFassIdleID;
    public static int ZapfFassActiveID;
    public static int BlockGaerTankID;
    public static int HopfenPaketID;
    public static int GerstenPaketID;
    public static int SchwarzBierZutatID;
    public static int PilsBierZutatID;
    public static int BlockWeinID;
    public static int BlockWeinExtID;
    public static int ItemSamenWeinID;
    public static int ItemWeinTraubenID;
    public static int BlockBierFassID;
    public static int bierFassModelID;
    public static int regalModelID;
    public static int ItemBierFassID;
    public static int WeinTraubenPaketID;
    public static int WeinExtraktID;
    public static int WeinTraubenZutatID;
    public static int LeeresWeinGlasID;
    public static int WeinID;
    public static int MilchID;
    public static int GlassRegalID;
    public static int GlassRegalItemID;
    public static String soundEnable;
    public static Item LeeresBierGlas;
    public static Item SchwarzBier;
    public static Item PilsBier;
    public static Item LeeresFass;
    public static Item SchwarzBierExtrakt;
    public static Item PilsBierExtrakt;
    public static Item ItemGerste;
    public static Item ItemSamenGerste;
    public static Item ItemSamenHopfen;
    public static Item ItemHopfen;
    public static Item GasAmpulleLeer;
    public static Item GasAmpulleVoll;
    public static Item HopfenPaket;
    public static Item GerstenPaket;
    public static Item SchwarzBierZutat;
    public static Item PilsBierZutat;
    public static Item ItemSamenWein;
    public static Item ItemWeinTrauben;
    public static Item WeinTraubenPaket;
    public static Item WeinExtrakt;
    public static Item WeinTraubenZutat;
    public static Item LeeresWeinGlas;
    public static Item Wein;
    public static Item Milch;
    public static Item GlassRegalItem;
    public static Block BlockGerste;
    public static Block BlockHopfen;
    public static Block BlockHopfenExt;
    public static Block BlockGaerTank;
    public static Block GasExtraktorIdle;
    public static Block GasExtraktorActive;
    public static Block ZapfFassIdle;
    public static Block ZapfFassActive;
    public static Block BlockWein;
    public static Block BlockWeinExt;
    public static Block BlockBierFass;
    public static Block GlassRegal;

    @Mod$PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {
        Configuration var2 = new Configuration(var1.getSuggestedConfigurationFile());

        try
        {
            var2.load();
            BlockGersteID = var2.getBlock("Block Barley", 500).getInt();
            BlockHopfenID = var2.getBlock("Block Hops", 501).getInt();
            GasExtraktorIdleID = var2.getBlock("Gas Extractor Idle", 502).getInt();
            GasExtraktorActiveID = var2.getBlock("Gas Extractor Active", 503).getInt();
            ZapfFassIdleID = var2.getBlock("Beer Pump Idle", 504).getInt();
            ZapfFassActiveID = var2.getBlock("Beer Pump Active", 505).getInt();
            BlockGaerTankID = var2.getBlock("Fermentation Tank", 506).getInt();
            BlockWeinID = var2.getBlock("Block Wine", 507).getInt();
            BlockBierFassID = var2.getBlock("Block Beer Barrel", 508).getInt();
            GlassRegalID = var2.getBlock("Glass Shelf", 509).getInt();
            BlockHopfenExtID = var2.getBlock("Block Hops Ext", 510).getInt();
            BlockWeinExtID = var2.getBlock("Block Wine Ext", 511).getInt();
            soundEnable = var2.get("Sound", "general", "true").getString();
            LeeresBierGlasID = var2.getItem("Empty Beer Jug", 8000).getInt();
            SchwarzBierID = var2.getItem("Dark Beer", 8001).getInt();
            PilsBierID = var2.getItem("Pilsner Beer", 8002).getInt();
            ItemSamenGersteID = var2.getItem("Barley Seeds", 8003).getInt();
            ItemGersteID = var2.getItem("Item Barley", 8004).getInt();
            ItemSamenHopfenID = var2.getItem("Hops Seeds", 8005).getInt();
            ItemHopfenID = var2.getItem("Item Hops", 8006).getInt();
            GasAmpulleLeerID = var2.getItem("Empty Gas Refill", 8007).getInt();
            GasAmpulleVollID = var2.getItem("Full Gas Refill", 8008).getInt();
            LeeresFassID = var2.getItem("Empty Barrel", 8009).getInt();
            SchwarzBierExtraktID = var2.getItem("Dark Mash", 8010).getInt();
            PilsBierExtraktID = var2.getItem("Bright Mash", 8011).getInt();
            HopfenPaketID = var2.getItem("Hops Package", 8012).getInt();
            GerstenPaketID = var2.getItem("Barley Package", 8013).getInt();
            SchwarzBierZutatID = var2.getItem("Black Beer Ingredients", 8014).getInt();
            PilsBierZutatID = var2.getItem("Pils Beer Ingredients", 8015).getInt();
            LeeresWeinGlasID = var2.getItem("Empty Wine Glas", 8017).getInt();
            WeinID = var2.getItem("Wine", 8018).getInt();
            ItemSamenWeinID = var2.getItem("Grapes Seeds", 8019).getInt();
            ItemWeinTraubenID = var2.getItem("Item Grapes", 8020).getInt();
            WeinTraubenPaketID = var2.getItem("Grapes Package", 8021).getInt();
            WeinTraubenZutatID = var2.getItem("Grapes Ingredients", 8022).getInt();
            WeinExtraktID = var2.getItem("Grapes Mash", 8023).getInt();
            MilchID = var2.getItem("Milk", 8024).getInt();
            GlassRegalItemID = var2.getItem("Glass Shelf", 8025).getInt();
        }
        catch (Exception var7)
        {
            var7.printStackTrace();
        }
        finally
        {
            var2.save();
        }

        proxy.renderInformation();
    }

    @Mod$Init
    public void init(FMLInitializationEvent var1)
    {
        initializeBlocksAndItems();
        this.registerBlocks();
        LanguageHandler.addNamesDE();
        LanguageHandler.addNamesEN();
        LanguageHandler.addNamesSE();
        this.addRecipes();
        this.addSmelting();
        this.registerHandlers();
        this.registerThings();
    }

    private void registerThings()
    {
        NetworkRegistry.instance().registerGuiHandler(this, this.guiHandler);
        MinecraftForge.addGrassSeed(new ItemStack(ItemSamenGerste), 10);
        MinecraftForge.addGrassSeed(new ItemStack(ItemSamenHopfen), 6);
        MinecraftForge.addGrassSeed(new ItemStack(ItemSamenWein), 6);
    }

    private static void initializeBlocksAndItems()
    {
        DrunkEffekt = (new DrunkEffect(30, true, 12196378, "Drunk")).setIconIndex(0, 0);
        LeeresBierGlas = (new ItemManager(LeeresBierGlasID, "BierGlass")).setCreativeTab(tabBeerCreative).setUnlocalizedName("LeeresBierGlas");
        LeeresWeinGlas = (new ItemManager(LeeresWeinGlasID, "WeinGlass")).setCreativeTab(tabBeerCreative).setUnlocalizedName("LeeresWeinGlas");
        SchwarzBier = (new SchwarzBierItem(SchwarzBierID, 0, 0, 0.0F, false, modID + ":SchwarzBier")).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("SchwarzBier");
        PilsBier = (new WeizenBierItem(PilsBierID, 0, 0, 0.0F, false, modID + ":WeizenBier")).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("PilsBier");
        Wein = (new WeinItem(WeinID, 0, 0, 0.0F, false, modID + ":Wein")).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("Wein");
        Milch = (new MilchItem(MilchID, 0, 0, 0.0F, false)).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("milk");
        SchwarzBierExtrakt = (new ItemManager(SchwarzBierExtraktID, "SchwarzBierExtrakt")).setCreativeTab(tabBeerCreative).setUnlocalizedName("SchwarzBierExtrakt");
        PilsBierExtrakt = (new ItemManager(PilsBierExtraktID, "PilsBierExtrakt")).setCreativeTab(tabBeerCreative).setUnlocalizedName("PilsBierExtrakt");
        WeinExtrakt = (new ItemManager(WeinExtraktID, "WeinBierExtrakt")).setCreativeTab(tabBeerCreative).setUnlocalizedName("WeinExtrakt");
        BlockGerste = (new BlockGerste(BlockGersteID)).setStepSound(Block.soundGrassFootstep).setHardness(0.0F).setUnlocalizedName("BlockGerste");
        ItemSamenGerste = (new ItemSamenGerste(ItemSamenGersteID, BlockGerste.blockID, Block.tilledField.blockID, modID + ":GerstenSamen")).setCreativeTab(tabBeerCreative).setUnlocalizedName("ItemSamenGerste");
        ItemGerste = (new ItemManager(ItemGersteID, "Gerste")).setUnlocalizedName("ItemGerste").setCreativeTab(tabBeerCreative);
        BlockHopfen = (new BlockHopfen(BlockHopfenID)).setStepSound(Block.soundGrassFootstep).setHardness(0.3F).setUnlocalizedName("BlockHopfen");
        BlockHopfenExt = (new BlockHopfenExt(BlockHopfenExtID)).setStepSound(Block.soundGrassFootstep).setHardness(0.3F).setUnlocalizedName("BlockHopfenExt");
        ItemSamenHopfen = (new ItemSamenHopfen(ItemSamenHopfenID, BlockHopfen.blockID, Block.tilledField.blockID, modID + ":HopfenSamen")).setCreativeTab(tabBeerCreative).setUnlocalizedName("ItemSamenHopfen");
        ItemHopfen = (new ItemManager(ItemHopfenID, "Hopfen")).setUnlocalizedName("ItemHopfen").setCreativeTab(tabBeerCreative);
        HopfenPaket = (new ItemManager(HopfenPaketID, "HopfenPaket")).setUnlocalizedName("HopfenPaket").setCreativeTab(tabBeerCreative);
        GerstenPaket = (new ItemManager(GerstenPaketID, "GerstenPaket")).setUnlocalizedName("GerstenPaket").setCreativeTab(tabBeerCreative);
        WeinTraubenPaket = (new ItemManager(WeinTraubenPaketID, "WeinPaket")).setUnlocalizedName("WeinPaket").setCreativeTab(tabBeerCreative);
        SchwarzBierZutat = (new ItemManager(SchwarzBierZutatID, "SchwarzZutat")).setCreativeTab(tabBeerCreative).setUnlocalizedName("SchwarzBierZutat");
        PilsBierZutat = (new ItemManager(PilsBierZutatID, "PilsZutat")).setCreativeTab(tabBeerCreative).setUnlocalizedName("PilsBierZutat");
        WeinTraubenZutat = (new ItemManager(WeinTraubenZutatID, "WeinZutat")).setCreativeTab(tabBeerCreative).setUnlocalizedName("WeinTraubenZutat");
        BlockWein = (new BlockWein(BlockWeinID)).setStepSound(Block.soundGrassFootstep).setHardness(0.3F).setUnlocalizedName("BlockWein");
        BlockWeinExt = (new BlockWeinExt(BlockWeinExtID)).setStepSound(Block.soundGrassFootstep).setHardness(0.3F).setUnlocalizedName("BlockWeinExt");
        ItemSamenWein = (new ItemSamenHopfen(ItemSamenWeinID, BlockWein.blockID, Block.tilledField.blockID, modID + ":WeinSamen")).setCreativeTab(tabBeerCreative).setUnlocalizedName("ItemSamenWein");
        ItemWeinTrauben = (new ItemManager(ItemWeinTraubenID, "WeinTrauben")).setUnlocalizedName("ItemWein").setCreativeTab(tabBeerCreative);
        GasExtraktorIdle = (new BlockGasExtraktor(GasExtraktorIdleID, false)).setHardness(6.0F).setResistance(0.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("GasExtraktorIdle").setCreativeTab(tabBeerCreative);
        GasExtraktorActive = (new BlockGasExtraktor(GasExtraktorActiveID, true)).setHardness(6.0F).setResistance(0.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("GasExtraktorActive");
        ZapfFassIdle = (new BlockZapfFass(ZapfFassIdleID, false)).setHardness(6.0F).setResistance(0.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("ZapfFassIdle").setCreativeTab(tabBeerCreative);
        ZapfFassActive = (new BlockZapfFass(ZapfFassActiveID, true)).setHardness(6.0F).setResistance(0.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("ZapfFassActive");
        BlockGaerTank = (new BlockGaerTank(BlockGaerTankID, 0, false)).setHardness(10.0F).setResistance(8.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockGaerTank").setCreativeTab(tabBeerCreative);
        GasAmpulleLeer = (new GasAmpulleLeerItem(GasAmpulleLeerID, "GasAmpulleLeer")).setCreativeTab(tabBeerCreative).setUnlocalizedName("GasAmpulleLeer");
        GasAmpulleVoll = (new GasAmpulleVollItem(GasAmpulleVollID, "GasAmpulleVoll")).setCreativeTab(tabBeerCreative).setUnlocalizedName("GasAmpulleVoll").setContainerItem(GasAmpulleLeer);
        BlockBierFass = (new BlockBierFass(BlockBierFassID, 58)).setCreativeTab(tabBeerCreative).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("BierFass");
        LeeresFass = (new ItemBierFass(LeeresFassID, BlockBierFass, modID + ":BierFass")).setCreativeTab(tabBeerCreative).setUnlocalizedName("LeeresFass");
    }

    private void registerBlocks()
    {
        GameRegistry.registerBlock(BlockGerste);
        GameRegistry.registerBlock(BlockHopfen);
        GameRegistry.registerBlock(BlockWein);
        GameRegistry.registerBlock(ZapfFassIdle);
        GameRegistry.registerBlock(ZapfFassActive);
        GameRegistry.registerBlock(GasExtraktorIdle);
        GameRegistry.registerBlock(GasExtraktorActive);
        GameRegistry.registerBlock(BlockGaerTank);
    }

    private void addRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(LeeresBierGlas, 1), new Object[] {"W W", "W W", "WWW", 'W', Block.thinGlass});
        GameRegistry.addRecipe(new ItemStack(LeeresWeinGlas, 1), new Object[] {"W W", "WWW", " W ", 'W', Block.thinGlass});
        GameRegistry.addRecipe(new ItemStack(LeeresFass, 1), new Object[] {"HHH", "HEH", "HHH", 'H', Block.planks, 'E', Item.ingotIron});
        GameRegistry.addRecipe(new ItemStack(GerstenPaket, 1), new Object[] {"GGG", "GGG", "GGG", 'G', ItemGerste});
        GameRegistry.addRecipe(new ItemStack(ItemGerste, 9), new Object[] {"G", 'G', GerstenPaket});
        GameRegistry.addRecipe(new ItemStack(Item.bread, 1), new Object[] {"GGG", 'G', ItemGerste});
        GameRegistry.addRecipe(new ItemStack(HopfenPaket, 1), new Object[] {"HHH", "HHH", "HHH", 'H', ItemHopfen});
        GameRegistry.addRecipe(new ItemStack(ItemHopfen, 9), new Object[] {"H", 'H', HopfenPaket});
        GameRegistry.addRecipe(new ItemStack(WeinTraubenPaket, 1), new Object[] {"HHH", "HHH", "HHH", 'H', ItemWeinTrauben});
        GameRegistry.addRecipe(new ItemStack(ItemWeinTrauben, 9), new Object[] {"H", 'H', WeinTraubenPaket});
        GameRegistry.addRecipe(new ItemStack(SchwarzBierZutat, 1), new Object[] {"HBH", 'B', Item.bucketWater, 'H', HopfenPaket});
        GameRegistry.addRecipe(new ItemStack(PilsBierZutat, 1), new Object[] {"GBH", 'G', GerstenPaket, 'B', Item.bucketWater, 'H', HopfenPaket});
        GameRegistry.addRecipe(new ItemStack(WeinTraubenZutat, 1), new Object[] {"HBH", 'B', Item.bucketWater, 'H', WeinTraubenPaket});
        GameRegistry.addRecipe(new ItemStack(ZapfFassIdle, 1), new Object[] {"HHH", "III", "HHH", 'H', Block.planks, 'I', Item.ingotIron});
    }

    private void addSmelting()
    {
        RecipesZapfFass.smelting().addSmelting1(LeeresBierGlas.itemID, SchwarzBierExtrakt.itemID, new ItemStack(SchwarzBier));
        RecipesZapfFass.smelting().addSmelting1(LeeresBierGlas.itemID, PilsBierExtrakt.itemID, new ItemStack(PilsBier));
        RecipesZapfFass.smelting().addSmelting1(LeeresWeinGlas.itemID, WeinExtrakt.itemID, new ItemStack(Wein));
        RecipesGasExtraktor.smelting().addSmelting(GasAmpulleLeer.itemID, new ItemStack(GasAmpulleVoll));
        RecipesGaerTank.smelting().addSmelting(SchwarzBierZutat.itemID, new ItemStack(SchwarzBierExtrakt));
        RecipesGaerTank.smelting().addSmelting(PilsBierZutat.itemID, new ItemStack(PilsBierExtrakt));
        RecipesGaerTank.smelting().addSmelting(WeinTraubenZutat.itemID, new ItemStack(WeinExtrakt));
    }

    private void registerHandlers()
    {
        bierFassModelID = RenderingRegistry.getNextAvailableRenderId();
        regalModelID = RenderingRegistry.getNextAvailableRenderId();
        GameRegistry.registerTileEntity(TileEntityGaerTank.class, "GaerTank");
        GameRegistry.registerTileEntity(TileEntityGasExtraktor.class, "GasExtraktor");
        GameRegistry.registerTileEntity(TileEntityZapfFass.class, "ZapfFass");
        GameRegistry.registerTileEntity(TileEntityBierFass.class, "BierFass");
    }

    @SideOnly(Side.CLIENT)
    @Mod$PostInit
    public void postInitClient(FMLPostInitializationEvent var1)
    {
        int var2 = ClientProxy.getPropertyVolume("CraftingDifficulty");
        System.out.println("BierMod Debug:");
        System.out.println("CraftingDifficulty: " + var2);

        if (var2 == 1)
        {
            GameRegistry.addRecipe(new ItemStack(GasAmpulleLeer, 1), new Object[] {"III", "IOI", "III", 'I', Item.ingotIron, 'O', Block.obsidian});
            GameRegistry.addRecipe(new ItemStack(GasExtraktorIdle, 1), new Object[] {"HHH", "IOI", "HHH", 'H', Block.planks, 'I', Item.ingotIron, 'O', Block.obsidian});
            GameRegistry.addRecipe(new ItemStack(BlockGaerTank, 1), new Object[] {"III", "IOI", "IOI", 'I', Item.ingotIron, 'O', Block.obsidian});
        }

        if (var2 == 2)
        {
            GameRegistry.addRecipe(new ItemStack(GasAmpulleLeer, 1), new Object[] {"III", "EOE", "EEE", 'I', Item.ingotIron, 'E', Block.blockIron, 'O', Block.obsidian});
            GameRegistry.addRecipe(new ItemStack(GasExtraktorIdle, 1), new Object[] {"HHH", "IEI", "HHH", 'H', Block.planks, 'I', Item.ingotIron, 'E', Block.blockIron});
            GameRegistry.addRecipe(new ItemStack(BlockGaerTank, 1), new Object[] {"EIE", "EOE", "EOE", 'I', Item.ingotIron, 'E', Block.blockIron, 'O', Block.obsidian});
        }
    }

    @SideOnly(Side.SERVER)
    @Mod$PostInit
    public void postInitServer(FMLPostInitializationEvent var1)
    {
        int var2 = CommonProxy.getPropertyVolume("CraftingDifficulty");
        System.out.println("BierMod Serverside Debug:");
        System.out.println("CraftingDifficulty: " + var2);

        if (var2 == 1)
        {
            GameRegistry.addRecipe(new ItemStack(GasAmpulleLeer, 1), new Object[] {"III", "IOI", "III", 'I', Item.ingotIron, 'O', Block.obsidian});
            GameRegistry.addRecipe(new ItemStack(GasExtraktorIdle, 1), new Object[] {"HHH", "IOI", "HHH", 'H', Block.planks, 'I', Item.ingotIron, 'O', Block.obsidian});
            GameRegistry.addRecipe(new ItemStack(BlockGaerTank, 1), new Object[] {"III", "IOI", "IOI", 'I', Item.ingotIron, 'O', Block.obsidian});
        }

        if (var2 == 2)
        {
            GameRegistry.addRecipe(new ItemStack(GasAmpulleLeer, 1), new Object[] {"III", "EOE", "EEE", 'I', Item.ingotIron, 'E', Block.blockIron, 'O', Block.obsidian});
            GameRegistry.addRecipe(new ItemStack(GasExtraktorIdle, 1), new Object[] {"HHH", "IEI", "HHH", 'H', Block.planks, 'I', Item.ingotIron, 'E', Block.blockIron});
            GameRegistry.addRecipe(new ItemStack(BlockGaerTank, 1), new Object[] {"EIE", "EOE", "EOE", 'I', Item.ingotIron, 'E', Block.blockIron, 'O', Block.obsidian});
        }
    }
}
