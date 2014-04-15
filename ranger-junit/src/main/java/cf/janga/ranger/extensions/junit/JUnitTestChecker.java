package cf.janga.ranger.extensions.junit;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IAnnotation;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;

import cf.janga.ranger.core.Source;
import cf.janga.ranger.extensions.TestChecker;

/**
 * A test checker for the <code>JUnit</code> testing framework.
 * 
 * @author Emerson Loureiro
 * 
 */
public class JUnitTestChecker implements TestChecker {

	private static final String JUNIT_TEST_METHOD_PREFIX = "test";
	private static final String JUNIT_TEST_CASE_CLASS_QUALIFIED_NAME = "junit.framework.TestCase";
	static final String JUNIT_TEST_ANNOTATION = "Test";
	private static final String JUNIT_IGNORE_TEST_ANNOTATION = "Ignore";
	static final String JUNIT_RUN_WITH_ANNOTATION = "RunWith";

	/** {@inheritDoc} */
	@Override
	public boolean isTest(Source source) {
		try {
			return (source.getJavaElement() instanceof IMethod) && (source.getName().startsWith(JUNIT_TEST_METHOD_PREFIX) || this.isTestAnnotated(source))
					&& (this.isInsideJUnitTestClass(source));
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean isDisabled(Source source) {
		boolean disabled = false;

		if (source.getJavaElement() instanceof IMethod) {
			IAnnotation testAnnotation = ((IMethod) source.getJavaElement()).getAnnotation(JUNIT_IGNORE_TEST_ANNOTATION);
			disabled = testAnnotation != null && testAnnotation.exists();
		}

		return disabled;
	}

	/** {@inheritDoc} */
	@Override
	public String getType() {
		return "JUnit";
	}

	private boolean isTestAnnotated(Source source) throws JavaModelException {
		for (IAnnotation annotationObject : ((IMethod) source.getJavaElement()).getAnnotations()) {
			String annotationName = annotationObject.getElementName();
			if (annotationName.equals("org.junit." + JUNIT_TEST_ANNOTATION) || annotationName.equals(JUNIT_TEST_ANNOTATION)) {
				return true;
			}
		}
		return false;
	}

	private boolean isInsideJUnitTestClass(Source source) {
		// First, get the type of the compilation unit being checked
		IType mainClass = source.getJavaFile().getCompilationUnit().findPrimaryType();
		try {
			if (hasRunWithAnnotation(mainClass)) {
				return true;
			}

			boolean insideUnitTest = false;
			// TODO REFC: Introduce a hierarchy cache under
			// junit.framework.TestCase to speedup the search

			// Create a super-class hierarchy for the type in the compilation
			// unit...
			ITypeHierarchy typeHierarchy = mainClass.newSupertypeHierarchy(new NullProgressMonitor());
			IType[] superClasses = typeHierarchy.getAllSuperclasses(mainClass);

			// ...then, go through each super-class...
			for (int i = 0; i < superClasses.length && !insideUnitTest; i++) {
				IType superClass = superClasses[i];
				// ...stopping when we find the JUnit junit.framework.TestCase
				// class
				insideUnitTest = (hasRunWithAnnotation(superClass)) || (superClass.getFullyQualifiedName() != null && superClass.getFullyQualifiedName().equals(JUNIT_TEST_CASE_CLASS_QUALIFIED_NAME));
			}
			return insideUnitTest;
		} catch (JavaModelException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean hasRunWithAnnotation(IType type) throws JavaModelException {
		for (IAnnotation annotationObject : type.getAnnotations()) {
			String annotationName = annotationObject.getElementName();
			if (annotationName.equals("org.junit.runner" + JUNIT_RUN_WITH_ANNOTATION) || annotationName.equals(JUNIT_RUN_WITH_ANNOTATION)) {
				return true;
			}
		}
		return false;
	}
}
