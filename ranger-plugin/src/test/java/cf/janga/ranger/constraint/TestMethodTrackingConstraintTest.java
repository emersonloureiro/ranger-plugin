package cf.janga.ranger.constraint;

import java.util.Arrays;

import org.jmock.Expectations;

import cf.janga.ranger.constraint.TestMethodTrackingConstraint;
import cf.janga.ranger.core.CallHierarchyNode;
import cf.janga.ranger.core.DefaultCallHierarchyNode;
import cf.janga.ranger.core.Source;
import cf.janga.ranger.extensions.TestChecker;
import cf.janga.ranger.test.UnitTestHelper;

/**
 * The class <code>TestMethodTrackingConstraintTest</code> contains tests for
 * the class {@link <code>TestMethodTrackingConstraint</code>}
 * 
 * @pattern JUnit Test Case
 * 
 * @generatedBy CodePro
 * 
 * @author Emerson Loureiro
 */
public class TestMethodTrackingConstraintTest extends UnitTestHelper {

	/**
	 * Tests the
	 * {@link TestMethodTrackingConstraint#keepGoing(cf.janga.ranger.core.CallHierarchyNode)}
	 * method
	 */
	public void testKeepGoing() {
		TestMethodTrackingConstraint trackingConstraint = new TestMethodTrackingConstraint(10, Arrays.asList(this.createTestCheckerMock(false)));
		// Check for a hierarchy node with a depth of less than the maximum,
		// whose source isn't in an anonymous block of code and is not a test
		// method.
		final Source notWithinAnonymousInvokable = this.createSourceMock(false);
		assertTrue("Tracking should determine to keep going!", trackingConstraint.keepGoing(new DefaultCallHierarchyNode(null, 0, notWithinAnonymousInvokable)));

		// Check for a hierarchy node with a depth of less than the maximum,
		// whose source isn't in an anonymous block of code and is a test
		// method.
		trackingConstraint.setTestChecker(Arrays.asList(this.createTestCheckerMock(true)));
		assertFalse("Tracking should determine to not keep going!", trackingConstraint.keepGoing(new DefaultCallHierarchyNode(null, 0, notWithinAnonymousInvokable)));

		// Check for a hierarchy node with a depth of more than the maximum
		assertFalse("Tracking should determine to not keep going!", trackingConstraint.keepGoing(new DefaultCallHierarchyNode(null, 11, this.mock(Source.class))));

		// Check for a hierarchy node within the limit and whose invokable is on
		// a anonymous block of code
		final Source withinAnonymousInvokable = this.createSourceMock(true);
		assertFalse("Tracking should determine to not keep going!", trackingConstraint.keepGoing(new DefaultCallHierarchyNode(null, 0, withinAnonymousInvokable)));
	}

	/**
	 * Tests for
	 * {@link TestMethodTrackingConstraint#isTarget(CallHierarchyNode)} method.
	 */
	public void testIsTarget() {
		// Testing when none of the test checkers identify a source as a test
		TestMethodTrackingConstraint trackingConstraint = new TestMethodTrackingConstraint(10, Arrays.asList(createTestCheckerMock(false), createTestCheckerMock(false)));
		assertFalse(trackingConstraint.isTarget(new DefaultCallHierarchyNode(null, 0, this.mock(Source.class))));
		// Testing when at least one of the test checkers identify a source as a
		// test
		trackingConstraint.setTestChecker(Arrays.asList(createTestCheckerMock(false), createTestCheckerMock(true)));
		assertTrue(trackingConstraint.isTarget(new DefaultCallHierarchyNode(null, 0, this.mock(Source.class))));
		// Testing when at least onf of the test checkers identify a source as a
		// test but the test is disabled
		trackingConstraint.setTestChecker(Arrays.asList(createTestCheckerMock(false), createTestCheckerMock(true, true)));
		assertFalse(trackingConstraint.isTarget(new DefaultCallHierarchyNode(null, 0, this.mock(Source.class))));
	}

	private Source createSourceMock(final boolean withinAnonymous) {
		final Source source = this.mock(Source.class);
		this.getMockery().checking(new Expectations() {
			{
				atLeast(0).of(source).isWithinAnonymous();
				will(returnValue(withinAnonymous));
			}
		});
		return source;
	}

	private TestChecker createTestCheckerMock(final boolean isTest, final boolean isDisabled) {
		final TestChecker checker = this.mock(TestChecker.class);
		this.getMockery().checking(new Expectations() {
			{
				atLeast(0).of(checker).isTest(with(aNonNull(Source.class)));
				will(returnValue(isTest));

				atLeast(0).of(checker).isDisabled(with(aNonNull(Source.class)));
				will(returnValue(isDisabled));
			}
		});
		return checker;
	}

	private TestChecker createTestCheckerMock(final boolean isTest) {
		return createTestCheckerMock(isTest, false);
	}
}