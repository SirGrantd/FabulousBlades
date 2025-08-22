package net.sirgrantd.fabulous_blades.common.items.weapons.legendarys;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import net.sirgrantd.fabulous_blades.common.items.FabulousWeapons;
import net.sirgrantd.fabulous_blades.common.items.WeaponsAttributes;
import net.sirgrantd.fabulous_blades.common.items.WeaponsTier;

public class EveOfDawnItem extends FabulousWeapons {

    private static final Tier TIER = WeaponsTier.getTier(
        2000,
        25,
        9f,
        Ingredient.of(new ItemStack(Items.NETHERITE_INGOT)),
        BlockTags.INCORRECT_FOR_NETHERITE_TOOL
    );

    private static final float ATTACK_DAMAGE = 9f;
    private static final float ATTACK_SPEED = -2f;
    private static final float CRIT_CHANCE = 0.10f;
    private static final float CRIT_DAMAGE = 0.75f;

    public EveOfDawnItem() {
        super(TIER, new Properties().attributes(WeaponsAttributes.swordsAttributes(ATTACK_DAMAGE, ATTACK_SPEED, CRIT_CHANCE, CRIT_DAMAGE)));
    }
    
}
