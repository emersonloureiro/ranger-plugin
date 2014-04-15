package cf.janga.ranger.plugin.gui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import cf.janga.ranger.plugin.RangerPlugin;
import cf.janga.ranger.plugin.actions.PreferenceConstants;


/**
 * Class used to initialize default preference values.
 * 
 * @author Emerson Loureiro
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/** {@inheritDoc} */
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RangerPlugin.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.MAX_SEARCH_DEPTH_PROPERTY_NAME, 5);
	}
}
