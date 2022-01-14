package fr.karmaowner.erthilia.client.event;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class RenderFirstPersonEvent extends Event {

	private final float partialTicks;
    private final float interpolatedPitch;
    private final float swingProgress;
    private final float equipProgress;
    
    @Nonnull
    private final ItemStack stack;

    public RenderFirstPersonEvent(float partialTicks, float interpolatedPitch, float swingProgress, float equipProgress, @Nonnull ItemStack stack)
    {
        this.partialTicks = partialTicks;
        this.interpolatedPitch = interpolatedPitch;
        this.swingProgress = swingProgress;
        this.equipProgress = equipProgress;
        this.stack = stack;
    }

    public float getPartialTicks()
    {
        return partialTicks;
    }

    /**
     * @return The interpolated pitch of the player entity
     */
    public float getInterpolatedPitch()
    {
        return interpolatedPitch;
    }

    /**
     * @return The swing progress of the hand being rendered
     */
    public float getSwingProgress()
    {
        return swingProgress;
    }

    /**
     * @return The progress of the equip animation. 1.0 is fully equipped.
     */
    public float getEquipProgress()
    {
        return equipProgress;
    }

    /**
     * @return The ItemStack to be rendered, or null.
     */
    @Nonnull
    public ItemStack getItemStack()
    {
        return stack;
    }
}
