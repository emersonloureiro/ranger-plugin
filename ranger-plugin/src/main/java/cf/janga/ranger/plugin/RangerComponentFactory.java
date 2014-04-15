package cf.janga.ranger.plugin;

import cf.janga.ranger.search.SearchProcessor;
import cf.janga.ranger.view.SearchResultViewController;


/**
 * @author Emerson Loureiro
 * 
 */
public interface RangerComponentFactory {

	SearchProcessor getSearchProcessor();

	SearchResultViewController getSearchResultViewController();
}
