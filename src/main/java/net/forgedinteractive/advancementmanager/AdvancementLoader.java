package net.forgedinteractive.advancementmanager;

import net.minecraft.advancement.PlacedAdvancement;
import net.minecraft.advancement.AdvancementManager;
import net.minecraft.util.Identifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AdvancementLoader {

    private static final AdvancementManager advManager = new AdvancementManager();

    public static void ClearAdvancements() {
        Collection<PlacedAdvancement> advancements = advManager.getAdvancements();
        Set<Identifier> advancementIDs = new HashSet<Identifier>();
        for(PlacedAdvancement adv : advancements) {
            advancementIDs.add(adv.getAdvancementEntry().id());
        }
        advManager.removeAll(advancementIDs);
    }
}
