package cf.janga.ranger.view;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.jmock.Expectations;

import cf.janga.ranger.core.CallHierarchyNode;
import cf.janga.ranger.core.DefaultCallHierarchyNode;
import cf.janga.ranger.core.Source;
import cf.janga.ranger.test.UnitTestHelper;
import cf.janga.ranger.view.DefaultSearchResultGrouper;
import cf.janga.ranger.view.SearchViewNode;


/**
 * Unit test for <code>DefaultSearchResultGrouper</code>.
 * 
 * @author Emerson Loureiro
 * 
 */
public class DefaultSearchResultGrouperTest extends UnitTestHelper {

	public DefaultSearchResultGrouper grouper;

	@Override
	public void setUpImpl() {
		this.grouper = new DefaultSearchResultGrouper() {
			@Override
			SearchViewNode createSearchViewNode(CallHierarchyNode node) {
				return DefaultSearchResultGrouperTest.this.createSearchViewNodeMock();
			}
		};
	}

	/**
	 * Tests the grouping performed by DefaultSearchResultGrouper.
	 */
	public void testGroup() {
		// The search result to be grouped
		CallHierarchyNode target_1 = new DefaultCallHierarchyNode(null, 1, this.createSourceMock());
		CallHierarchyNode target_2 = new DefaultCallHierarchyNode(null, 1, this.createSourceMock());

		List<SearchViewNode> viewNodes = this.grouper.group(Arrays.asList(target_1, target_2));

		assertEquals("Incorrect number of view nodes returned", 2, viewNodes.size());
	}

	private SearchViewNode createSearchViewNodeMock() {
		SearchViewNode nodeMock = this.mock(SearchViewNode.class);
		return nodeMock;
	}

	private Source createSourceMock() {
		final Source source = this.mock(Source.class);
		final IJavaElement sourceJavaElement = this.mock(IJavaElement.class);

		this.getMockery().checking(new Expectations() {
			{
				atLeast(0).of(source).getJavaElement();
				will(returnValue(sourceJavaElement));
			}
		});

		return source;
	}
}
