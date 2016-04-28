/*
 * This file 'IModuleSlot.java' is part of Rarmor by canitzp.
 * It isn't allowed to use more than 15% of the code
 * or redistribute the compiled jar file.
 * The source code can be found here: https://github.com/canitzp/Rarmor
 * © canitzp, 2016
 */

package de.canitzp.rarmor.api.slots;

/**
 * @author canitzp
 */
public interface IModuleSlot{

    boolean isSlotActive();

    void setSlotActive();

    void setSlotInactive();

}
