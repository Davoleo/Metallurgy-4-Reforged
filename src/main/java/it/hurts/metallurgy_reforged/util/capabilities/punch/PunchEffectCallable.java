package it.hurts.metallurgy_reforged.util.capabilities.punch;

import java.util.concurrent.Callable;

public class PunchEffectCallable implements Callable<PunchEffect>{

	@Override
	public PunchEffect call() throws Exception {
		return new PunchEffect();
	}

}
