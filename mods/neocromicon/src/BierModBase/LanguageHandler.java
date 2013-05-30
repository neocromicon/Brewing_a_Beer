package mods.neocromicon.src.BierModBase;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LanguageHandler extends BierMod
{
    public static void addNamesDE()
    {
        LanguageRegistry.instance().addStringLocalization(BierMod.LeeresBierGlas.getUnlocalizedName() + ".name", "de_DE", "Leerer Bier Krug");
        LanguageRegistry.instance().addStringLocalization(BierMod.LeeresWeinGlas.getUnlocalizedName() + ".name", "de_DE", "Leeres Wein Glass");
        LanguageRegistry.instance().addStringLocalization(BierMod.SchwarzBier.getUnlocalizedName() + ".name", "de_DE", "Schwarzbier");
        LanguageRegistry.instance().addStringLocalization(BierMod.PilsBier.getUnlocalizedName() + ".name", "de_DE", "Pilsbier");
        LanguageRegistry.instance().addStringLocalization(BierMod.Wein.getUnlocalizedName() + ".name", "de_DE", "Wein");
        LanguageRegistry.instance().addStringLocalization(BierMod.Milch.getUnlocalizedName() + ".name", "de_DE", "(Nur Creative) Milch");
        LanguageRegistry.instance().addStringLocalization(BierMod.LeeresFass.getUnlocalizedName() + ".name", "de_DE", "Leeres Fass");
        LanguageRegistry.instance().addStringLocalization(BierMod.SchwarzBierExtrakt.getUnlocalizedName() + ".name", "de_DE", "Dunkle Maische");
        LanguageRegistry.instance().addStringLocalization(BierMod.PilsBierExtrakt.getUnlocalizedName() + ".name", "de_DE", "Helle Maische");
        LanguageRegistry.instance().addStringLocalization(BierMod.WeinExtrakt.getUnlocalizedName() + ".name", "de_DE", "Weintrauben Maische");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemSamenGerste.getUnlocalizedName() + ".name", "de_DE", "Gersten Samen");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemSamenHopfen.getUnlocalizedName() + ".name", "de_DE", "Hopfen Samen");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemSamenWein.getUnlocalizedName() + ".name", "de_DE", "Weintrauben Samen");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemHopfen.getUnlocalizedName() + ".name", "de_DE", "Hopfen");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemGerste.getUnlocalizedName() + ".name", "de_DE", "Gerste");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemWeinTrauben.getUnlocalizedName() + ".name", "de_DE", "Weintrauben");
        LanguageRegistry.instance().addStringLocalization(BierMod.HopfenPaket.getUnlocalizedName() + ".name", "de_DE", "Hopfen Paket");
        LanguageRegistry.instance().addStringLocalization(BierMod.GerstenPaket.getUnlocalizedName() + ".name", "de_DE", "Gersten Paket");
        LanguageRegistry.instance().addStringLocalization(BierMod.WeinTraubenPaket.getUnlocalizedName() + ".name", "de_DE", "Weintrauben Paket");
        LanguageRegistry.instance().addStringLocalization(BierMod.SchwarzBierZutat.getUnlocalizedName() + ".name", "de_DE", "Schwarzbier Zutaten");
        LanguageRegistry.instance().addStringLocalization(BierMod.PilsBierZutat.getUnlocalizedName() + ".name", "de_DE", "Pilsbier Zutaten");
        LanguageRegistry.instance().addStringLocalization(BierMod.WeinTraubenZutat.getUnlocalizedName() + ".name", "de_DE", "Weintrauben Zutat");
        LanguageRegistry.instance().addStringLocalization(BierMod.GasAmpulleLeer.getUnlocalizedName() + ".name", "de_DE", "Leere Gasampulle");
        LanguageRegistry.instance().addStringLocalization(BierMod.GasAmpulleVoll.getUnlocalizedName() + ".name", "de_DE", "Volle Gasampulle");
        LanguageRegistry.instance().addStringLocalization("tile.ZapfFassIdle.name", "de_DE", "Zapfanlage");
    }

    public static void addNamesEN()
    {
        LanguageRegistry.addName(LeeresBierGlas, "Empty Beer Jug");
        LanguageRegistry.addName(LeeresWeinGlas, "Empty Wine Glass");
        LanguageRegistry.addName(SchwarzBier, "Dark Beer");
        LanguageRegistry.addName(PilsBier, "Pilsner Beer");
        LanguageRegistry.addName(Wein, "Wine");
        LanguageRegistry.addName(Milch, "(Only creative) Milk");
        LanguageRegistry.addName(LeeresFass, "Empty Barrel");
        LanguageRegistry.addName(SchwarzBierExtrakt, "Dark Mash");
        LanguageRegistry.addName(PilsBierExtrakt, "Bright Mash");
        LanguageRegistry.addName(WeinExtrakt, "Grapes Mash");
        LanguageRegistry.addName(ItemHopfen, "Hops");
        LanguageRegistry.addName(ItemGerste, "Barley");
        LanguageRegistry.addName(ItemWeinTrauben, "Grapes");
        LanguageRegistry.addName(ItemSamenGerste, "Barley Seeds");
        LanguageRegistry.addName(ItemSamenHopfen, "Hops Seeds");
        LanguageRegistry.addName(ItemSamenWein, "Grapes Seeds");
        LanguageRegistry.addName(HopfenPaket, "Hops Package");
        LanguageRegistry.addName(GerstenPaket, "Barley Package");
        LanguageRegistry.addName(WeinTraubenPaket, "Grapes Package");
        LanguageRegistry.addName(SchwarzBierZutat, "Black Beer Ingredients");
        LanguageRegistry.addName(PilsBierZutat, "Pils Beer Ingredients");
        LanguageRegistry.addName(WeinTraubenZutat, "Grapes Ingredients");
        LanguageRegistry.addName(BlockGaerTank, "Fermentation Tank");
        LanguageRegistry.addName(GasAmpulleLeer, "Empty Gas Refill");
        LanguageRegistry.addName(GasAmpulleVoll, "Full Gas Refill");
        LanguageRegistry.instance().addStringLocalization("tile.ZapfFassIdle.name", "en_US", "Beer Pump");
        LanguageRegistry.instance().addStringLocalization("tile.GasExtraktorIdle.name", "en_US", "Gas Extractor");
    }

    public static void addNamesSE()
    {
        LanguageRegistry.instance().addStringLocalization(BierMod.LeeresBierGlas.getUnlocalizedName() + ".name", "sv_SE", "Tomt Oel Glass");
        LanguageRegistry.instance().addStringLocalization(BierMod.LeeresWeinGlas.getUnlocalizedName() + ".name", "sv_SE", "Tomt Vin Glass");
        LanguageRegistry.instance().addStringLocalization(BierMod.SchwarzBier.getUnlocalizedName() + ".name", "sv_SE", "Svart Oel");
        LanguageRegistry.instance().addStringLocalization(BierMod.PilsBier.getUnlocalizedName() + ".name", "sv_SE", "Pilsner");
        LanguageRegistry.instance().addStringLocalization(BierMod.Wein.getUnlocalizedName() + ".name", "sv_SE", "Vin");
        LanguageRegistry.instance().addStringLocalization(BierMod.Milch.getUnlocalizedName() + ".name", "sv_SE", "(Creative) MjOelk");
        LanguageRegistry.instance().addStringLocalization(BierMod.LeeresFass.getUnlocalizedName() + ".name", "sv_SE", "Tom Tunna");
        LanguageRegistry.instance().addStringLocalization(BierMod.SchwarzBierExtrakt.getUnlocalizedName() + ".name", "sv_SE", "Svart Maesk");
        LanguageRegistry.instance().addStringLocalization(BierMod.PilsBierExtrakt.getUnlocalizedName() + ".name", "sv_SE", "Vit Maesk");
        LanguageRegistry.instance().addStringLocalization(BierMod.WeinExtrakt.getUnlocalizedName() + ".name", "sv_SE", "Vindruvs Maesk");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemSamenGerste.getUnlocalizedName() + ".name", "sv_SE", "Korn Froen");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemSamenHopfen.getUnlocalizedName() + ".name", "sv_SE", "Humle Froen");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemSamenWein.getUnlocalizedName() + ".name", "sv_SE", "Druvor");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemHopfen.getUnlocalizedName() + ".name", "sv_SE", "Humle");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemGerste.getUnlocalizedName() + ".name", "sv_SE", "Korn");
        LanguageRegistry.instance().addStringLocalization(BierMod.ItemWeinTrauben.getUnlocalizedName() + ".name", "sv_SE", "Vindruvor");
        LanguageRegistry.instance().addStringLocalization(BierMod.HopfenPaket.getUnlocalizedName() + ".name", "sv_SE", "Humle Paket");
        LanguageRegistry.instance().addStringLocalization(BierMod.GerstenPaket.getUnlocalizedName() + ".name", "sv_SE", "Korn Paket");
        LanguageRegistry.instance().addStringLocalization(BierMod.WeinTraubenPaket.getUnlocalizedName() + ".name", "sv_SE", "Druv Paket");
        LanguageRegistry.instance().addStringLocalization(BierMod.SchwarzBierZutat.getUnlocalizedName() + ".name", "sv_SE", "Svart Ol Ingredienser");
        LanguageRegistry.instance().addStringLocalization(BierMod.PilsBierZutat.getUnlocalizedName() + ".name", "sv_SE", "Pilsner Ol Ingredienser");
        LanguageRegistry.instance().addStringLocalization(BierMod.WeinTraubenZutat.getUnlocalizedName() + ".name", "sv_SE", "Vin Ingredienser");
        LanguageRegistry.instance().addStringLocalization(BierMod.GasAmpulleLeer.getUnlocalizedName() + ".name", "sv_SE", "Tom Gas Flaska");
        LanguageRegistry.instance().addStringLocalization(BierMod.GasAmpulleVoll.getUnlocalizedName() + ".name", "sv_SE", "Full Gas Flaska");
        LanguageRegistry.instance().addStringLocalization("tile.ZapfFassIdle.name", "sv_SE", "Oel Pump");
        LanguageRegistry.instance().addStringLocalization("tile.GasExtraktorIdle.name", "sv_SE", "Gasborttagare");
    }
}
