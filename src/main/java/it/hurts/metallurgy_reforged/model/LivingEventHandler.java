/*==============================================================================
 = Class: LivingEventHandler
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.model;

import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.function.Consumer;

public class LivingEventHandler<E extends LivingEvent> {

    private final Consumer<E> delegate;
    private final Class<E> eClass;

    public LivingEventHandler(Consumer<E> delegate, Class<E> eClass) {
        this.delegate = delegate;
        this.eClass = eClass;
    }

    public Consumer<E> getDelegate() {
        return delegate;
    }

    public boolean equalsEvent(LivingEvent event) {
        return event.getClass().equals(eClass);
    }

}
