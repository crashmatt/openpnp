package org.openpnp.machine.reference.feeder;

import org.openpnp.gui.support.Wizard;
import org.openpnp.machine.reference.feeder.wizards.BlindsFeederAdvancedConfigurationWizard;

import org.openpnp.model.Length;
import org.openpnp.model.LengthUnit;
import org.simpleframework.xml.Element;
import org.openpnp.model.Location;

public class BlindsFeederAdvanced extends BlindsFeeder{
	
	@Element(required = false)
    private Length pickOffset = new Length(0, LengthUnit.Millimeters);

	@Override
	public Wizard getConfigurationWizard() {
	    return new BlindsFeederAdvancedConfigurationWizard(this);
	}
	
    @Override
    public Location getUncalibratedPickLocation(double pocketNumber)  {
    	BlindsFeeder blindsFeeder = this;
    	blindsFeeder.recalculateGeometry();
        // Calculate the pick location in local feeder coordinates. 
    	location = blindsFeeder.getLocation();
        Length feederX = blindsFeeder.getPocketPitch().multiply(pocketNumber-1.0).convertToUnits(location.getUnits()).add(blindsFeeder.getPocketDistance()).add(pickOffset);
        Length feederY = blindsFeeder.getPocketCenterline().convertToUnits(location.getUnits());

        Location feederLocation = new Location(location.getUnits(), feederX.getValue(), feederY.getValue(), 
        		location.getZ(), getPickRotationInTape());
        Location machineLocation = transformFeederToMachineLocation(feederLocation);
        return machineLocation;    	
    }
    
    public Length getPickOffset() {
        return pickOffset;
    }

    public void setPickOffset(Length pickOffset) {
        Length oldValue = this.pickOffset;
        this.pickOffset = pickOffset;
        firePropertyChange("pickOffset", oldValue, pickOffset);
    }
    	
}
