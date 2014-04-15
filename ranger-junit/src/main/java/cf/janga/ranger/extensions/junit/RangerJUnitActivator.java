package cf.janga.ranger.extensions.junit;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class RangerJUnitActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		RangerJUnitActivator.context = bundleContext;
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		RangerJUnitActivator.context = null;
	}
}
