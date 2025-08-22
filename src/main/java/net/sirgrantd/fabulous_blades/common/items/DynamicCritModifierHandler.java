package net.sirgrantd.fabulous_blades.common.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import dev.shadowsoffire.apothic_attributes.api.ALObjects;
import net.sirgrantd.fabulous_blades.FabulousBladesMod;
import net.sirgrantd.fabulous_blades.common.items.weapons.legendarys.HealtheaterItem;

@EventBusSubscriber(modid = FabulousBladesMod.MODID)
public class DynamicCritModifierHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (!(heldItem.getItem() instanceof HealtheaterItem)) return;

        float healthUnits = (player.getMaxHealth() + player.getAbsorptionAmount()) / 2f;
        float bonusCrit = healthUnits * 0.04f;

        ItemAttributeModifiers original = heldItem.get(DataComponents.ATTRIBUTE_MODIFIERS);
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        if (original != null) {
            original.forEach(EquipmentSlotGroup.MAINHAND, (attribute, modifier) -> {
                if (!attribute.unwrapKey().equals(ALObjects.Attributes.CRIT_DAMAGE.unwrapKey())) {
                    builder.add(attribute, modifier, EquipmentSlotGroup.MAINHAND);
                }
            });
        }

        builder.add(
            ALObjects.Attributes.CRIT_DAMAGE,
            new AttributeModifier(
                ResourceLocation.withDefaultNamespace("crit_chance_bonus"),
                bonusCrit,
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND
        );

        heldItem.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
    }

}
