package cf.janga.ranger.core;

import cf.janga.ranger.core.CallHierarchyNode;
import cf.janga.ranger.core.TrackingMonitor;

public class MockTrackMonitor implements TrackingMonitor {

	public int callFoundInvoked = 0;

	@Override
	public void callFound(CallHierarchyNode call) {
		this.callFoundInvoked++;
	}
}
