package com.tguzik.m2u.conversion;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.tguzik.util.CollectionUtils.safe;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.tguzik.m2u.conversion.readability.JMeter;
import com.tguzik.m2u.conversion.readability.JUnit;
import com.tguzik.m2u.data.jmeter.AssertionResult;
import com.tguzik.m2u.data.jmeter.Sample;
import com.tguzik.m2u.data.junit.Error;
import com.tguzik.m2u.data.junit.TestCase;
import com.tguzik.objects.BaseObject;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Tomasz Guzik <tomek@tguzik.com>
 */
@ThreadSafe
@ParametersAreNonnullByDefault
class SampleToTestCase implements Function<Sample, TestCase> {
    private static final double MILLIS_TO_SECONDS_RATIO = 1.0 / TimeUnit.SECONDS.toMillis( 1 );
    private final Function<AssertionResult, Error> assertionResultToError;
    private final Predicate<AssertionResult> assertionFailurePredicate;
    private final Predicate<AssertionResult> assertionErrorPredicate;

    public SampleToTestCase() {
        this( new AssertionResultToError(), new IsFailure(), new IsError() );
    }

    public SampleToTestCase( Function<AssertionResult, Error> assertionResultToError,
                             Predicate<AssertionResult> failurePredicate,
                             Predicate<AssertionResult> errorPredicate ) {
        //
        this.assertionResultToError = checkNotNull( assertionResultToError );
        this.assertionFailurePredicate = checkNotNull( failurePredicate );
        this.assertionErrorPredicate = checkNotNull( errorPredicate );
    }

    @JUnit
    @Override
    public TestCase apply( @JMeter Sample input ) {
        return new TestCase( errors( input.getAssertionResults() ),
                             failures( input.getAssertionResults() ),
                             systemOut( input ),
                             systemErr(),
                             className( input ),
                             numberOfTests( input ),
                             testName( input ),
                             status(),
                             elapsedTimeInSeconds( input ) );
    }

    @JUnit
    protected List<Error> errors( @JMeter List<AssertionResult> assertions ) {
        // In JDK8....
        final Collection<AssertionResult> assertionErrors = filter( assertions, assertionErrorPredicate );
        final Collection<Error> errors = transform( assertionErrors, assertionResultToError );

        return ImmutableList.copyOf( errors );
    }

    @JUnit
    protected List<Error> failures( @JMeter List<AssertionResult> assertions ) {
        // In JDK8....
        final Collection<AssertionResult> assertionErrors = filter( assertions, assertionFailurePredicate );
        final Collection<Error> errors = transform( assertionErrors, assertionResultToError );

        return ImmutableList.copyOf( errors );
    }

    protected List<String> systemOut( Sample input ) {
        return ImmutableList.of( input.toString( BaseObject.MULTILINE_NO_ADDRESS_STYLE ) );
    }

    protected List<String> systemErr() {
        // Parity with previous version. TODO: Meaningful status message would be nice.
        return ImmutableList.of();
    }

    protected String className( Sample input ) {
        return input.getThreadName();
    }

    protected int numberOfTests( Sample input ) {
        return safe( input.getAssertionResults() ).size();
    }

    protected String testName( Sample input ) {
        return input.getThreadName();
    }

    protected String status() {
        // Parity with previous version. TODO: Meaningful status message would be nice.
        return StringUtils.EMPTY;
    }

    protected double elapsedTimeInSeconds( Sample input ) {
        final Integer valueInMillis = input.getElapsedTime();

        if ( valueInMillis == null ) {
            return 0;
        }

        return valueInMillis * MILLIS_TO_SECONDS_RATIO;
    }

    public static class IsError implements Predicate<AssertionResult> {
        @Override
        public boolean apply( AssertionResult ar ) {
            return ar.isError();
        }
    }

    public static class IsFailure implements Predicate<AssertionResult> {
        @Override
        public boolean apply( AssertionResult ar ) {
            return ar.isFailure();
        }
    }
}
