package com.tguzik.m2u.conversion;

import static com.tguzik.util.CollectionUtils.safe;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.tguzik.m2u.data.jmeter.AssertionResult;
import com.tguzik.m2u.data.jmeter.Sample;
import com.tguzik.m2u.data.jmeter.TestResults;
import com.tguzik.m2u.data.junit.Failure;
import com.tguzik.m2u.data.junit.TestCase;
import com.tguzik.m2u.data.junit.TestSuite;
import com.tguzik.m2u.data.junit.TestSuites;

/**
 * Converts Jmeter data structure to the one reflecting Junit results. This class is thread safe because it has no
 * state.
 */
@ThreadSafe
@ParametersAreNonnullByDefault
public class JmeterToJunitDataStructure {
    /* On JDK8 this class would implement BiFunction interface */

    private static final Predicate<AssertionResult> FAILED_ASSERTIONS = new FailedAssertionPredicate();
    private static final Predicate<AssertionResult> ERRORED_ASSERTIONS = new ErroredOutAssertionPredicate();

    public TestSuites apply( String testSuiteName, TestResults jmeter ) {
        TestSuites suites = new TestSuites();
        TestSuite singleSuite = new TestSuite();

        suites.setTestGroupName( testSuiteName );
        singleSuite.setName( testSuiteName );

        for ( Sample sample : safe( jmeter.getSamples() ) ) {
            TestCase tc = convertSample( sample );
            singleSuite.addTestCase( tc );
        }

        for ( Sample sample : safe( jmeter.getHttpSamples() ) ) {
            TestCase tc = convertHttpSample( sample );
            singleSuite.addTestCase( tc );
        }

        suites.addTestSuite( singleSuite );

        return suites;
    }

    private TestCase convertSample( Sample sample ) {
        TestCase tc = new TestCase();

        tc.setAssertions( safe( sample.getAssertionResults() ).size() );
        tc.setClassname( sample.getThreadName() );
        tc.setTestName( sample.getThreadName() );
        tc.setTotalTimeSpent( sample.getElapsedTime() );
        tc.addSystemOut( sample.toString() );

        for ( AssertionResult ar : Iterables.filter( safe( sample.getAssertionResults() ), ERRORED_ASSERTIONS ) ) {
            tc.addError( convertErroredOutAssertion( ar ) );
        }

        for ( AssertionResult ar : Iterables.filter( safe( sample.getAssertionResults() ), FAILED_ASSERTIONS ) ) {
            tc.addFailure( convertFailedAssertion( ar ) );
        }

        return tc;
    }

    private Failure convertFailedAssertion( AssertionResult ar ) {
        Failure failure = new Failure();

        failure.setMessage( ar.getFailureMessage() );
        failure.setType( ar.getName() );

        return failure;
    }

    private com.tguzik.m2u.data.junit.Error convertErroredOutAssertion( AssertionResult ar ) {
        com.tguzik.m2u.data.junit.Error error = new com.tguzik.m2u.data.junit.Error();

        error.setMessage( ar.getFailureMessage() );
        error.setType( ar.getName() );

        return error;
    }

    private TestCase convertHttpSample( Sample sample ) {
        return convertSample( sample );
    }

    private static class ErroredOutAssertionPredicate implements Predicate<AssertionResult> {
        @Override
        public boolean apply( AssertionResult ar ) {
            return ar.isError();
        }
    }

    private static class FailedAssertionPredicate implements Predicate<AssertionResult> {
        @Override
        public boolean apply( AssertionResult ar ) {
            return ar.isFailure();
        }
    }
}

