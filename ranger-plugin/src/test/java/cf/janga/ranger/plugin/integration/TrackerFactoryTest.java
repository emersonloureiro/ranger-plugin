package cf.janga.ranger.plugin.integration;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.search.IJavaSearchScope;

import cf.janga.ranger.core.Source;
import cf.janga.ranger.core.TrackingConstraint;
import cf.janga.ranger.plugin.integration.TrackerFactory;
import cf.janga.ranger.search.ProjectSearch;
import cf.janga.ranger.search.Search;
import cf.janga.ranger.search.WorkspaceSearch;
import cf.janga.ranger.test.UnitTestHelper;


/**
 * The class <code>TrackerFactoryTest</code> contains tests for the class
 * {@link <code>TrackerFactory</code>}
 * 
 * @pattern JUnit Test Case
 * 
 * @generatedBy CodePro
 * 
 * @author Emerson Loureiro
 */
public class TrackerFactoryTest extends UnitTestHelper {

	private TrackerFactory factory;

	@Override
	public void setUpImpl() {
		this.factory = new TrackerFactory() {

			@Override
			IJavaSearchScope createSearchScope(IJavaProject[] projects) {
				return TrackerFactoryTest.this.mock(IJavaSearchScope.class);
			}
		};
		TrackerFactory.setInstance(this.factory);
	}

	/**
	 * Tests the createTracker method, given a null input.
	 */
	public void testCreateTracker_NullSearch() throws Exception {
		// Mocks
		try {
			TrackerFactory.createTracker(null);
			fail("An IllegalArgumentException should've been thrown!");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * Tests the createTracker method, given a {@link WorkspaceSearch}.
	 */
	public void testCreateTracker_WorkspaceSearch() throws Exception {
		// Mocks
		Source source = this.mock(Source.class);
		TrackingConstraint constraint = this.mock(TrackingConstraint.class);
		IJavaProject project = this.mock(IJavaProject.class);
		Search search = new WorkspaceSearch(source, constraint, new IJavaProject[] { project });
		assertNotNull("Should've returned a valid Tracker object!", TrackerFactory.createTracker(search));
	}

	/**
	 * Tests the createTracker method, given a {@link ProjectSearch}.
	 */
	public void testCreateTracker_ProjectSearch() throws Exception {
		// Mocks
		Source source = this.mock(Source.class);
		TrackingConstraint constraint = this.mock(TrackingConstraint.class);
		IJavaProject project = this.mock(IJavaProject.class);
		Search search = new ProjectSearch(source, constraint, project);
		assertNotNull("Should've returned a valid Tracker object!", TrackerFactory.createTracker(search));
	}
}