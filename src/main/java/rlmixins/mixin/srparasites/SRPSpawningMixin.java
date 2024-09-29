package rlmixins.mixin.srparasites;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPSpawning;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rlmixins.handlers.ForgeConfigHandler;

@Mixin(SRPSpawning.DimensionHandler.class)
public abstract class SRPSpawningMixin {

    @Inject(
            method="onSpawn",
            at=@At(value="RETURN"),
            remap=false
    )
    private static void forceSpawnerSpawnsMixin(LivingSpawnEvent.CheckSpawn event, CallbackInfo ci){
        if(ForgeConfigHandler.mixinConfig.enableSRPSpawners) {
            if (event.isSpawner() & event.getEntity() instanceof EntityParasiteBase) {
                event.setResult(Event.Result.DEFAULT);
            }
        }
    }
}