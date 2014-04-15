package cf.janga.ranger.plugin.integration;

import cf.janga.ranger.plugin.RangerComponentFactory;
import cf.janga.ranger.search.SearchProcessor;
import cf.janga.ranger.view.SearchResultViewController;


/**
 * @author Emerson Loureiro
 * 
 */
public class RangerComponentFactoryImpl implements RangerComponentFactory {

	private SearchProcessor searchProcessor;

	private SearchResultViewController viewController;

	private static final RangerComponentFactory instance = new RangerComponentFactoryImpl();

	private RangerComponentFactoryImpl() {
		this.searchProcessor = new SingleSearchSearchProcessor();
		this.viewController = new DefaultSearchResultViewController();
	}

	public static RangerComponentFactory getInstance() {
		return instance;
	}

	/** {@inheritDoc} */
	@Override
	public SearchProcessor getSearchProcessor() {
		return this.searchProcessor;
	}

	/** {@inheritDoc} */
	@Override
	public SearchResultViewController getSearchResultViewController() {
		return this.viewController;
	}
}
