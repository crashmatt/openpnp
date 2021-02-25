package org.openpnp.machine.reference.feeder;

import org.openpnp.gui.support.Wizard;
import org.openpnp.machine.reference.feeder.wizards.BlindsFeederAdvancedConfigurationWizard;

public class BlindsFeederAdvanced extends BlindsFeeder{

	@Override
	public Wizard getConfigurationWizard() {
	    return new BlindsFeederAdvancedConfigurationWizard(this);
	}

}
