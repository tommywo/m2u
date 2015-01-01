package com.tguzik.m2u.conversion;

import static com.tguzik.util.CollectionUtils.safe;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.tguzik.m2u.conversion.readability.JMeter;
import com.tguzik.m2u.conversion.readability.JUnit;
import com.tguzik.m2u.data.jmeter.Sample;
import com.tguzik.m2u.data.jmeter.TestResults;
import com.tguzik.m2u.data.junit.TestCase;
import com.tguzik.m2u.data.junit.TestSuite;

/**
 * Converts JMeter test results into single JUnit test suite. Since we don't have the exact information on how the
 * {@link com.tguzik.m2u.data.jmeter.TestResults} data should be split, we will take the simplest approach - create
 * only one {@link com.tguzik.m2u.data.junit.TestSuite} with all {@link com.tguzik.m2u.data.jmeter.Sample}s converted
 * to individual {@link com.tguzik.m2u.data.junit.TestCase}s within that suite.
 *
 * @author Tomasz Guzik <tomek@tguzik.com>
 */
@ThreadSafe
@ParametersAreNonnullByDefault
class ResultsToTestSuites {
    /* Under JDK8 this would implement BiFunction interface. */

    protected static final long MILLIS_IN_SECOND = TimeUnit.SECONDS.toMillis( 1 ); // for readability
    protected static final String TEST_SUITE_HOSTNAME = "localhost";
    protected static final String TEST_SUITE_PACKAGE = "m2u";
    protected static final String TEST_SUITE_ID = "m2u";
    private final Function<Sample, TestCase> sampleToTestCase;

    public ResultsToTestSuites() {
        this( new SampleToTestCase() );
    }

    public ResultsToTestSuites( Function<Sample, TestCase> sampleToTestCase ) {
        this.sampleToTestCase = Preconditions.checkNotNull( sampleToTestCase );
    }

    @JUnit
    public List<TestSuite> apply( String name, @JMeter TestResults results ) {
        final List<TestCase> cases = Lists.newArrayList();

        // In JDK8 we'd use streams...
        cases.addAll( Lists.transform( safe( results.getSamples() ), sampleToTestCase ) );
        cases.addAll( Lists.transform( safe( results.getHttpSamples() ), sampleToTestCase ) );

        return ImmutableList.of( testSuite( name, cases ) );
    }

    protected TestSuite testSuite( String name, List<TestCase> cases ) {
        return new TestSuite( properties(),
                              cases,
                              TEST_SUITE_ID,
                              name,
                              TEST_SUITE_PACKAGE,
                              TEST_SUITE_HOSTNAME,
                              totalTests( cases ),
                              disabledTests(),
                              errorsInTests( cases ),
                              failuresInTests( cases ),
                              skippedTests(),
                              elapsedTimeInMillis( cases ),
                              timestamp() );
    }

    protected Map<String, String> properties() {
        return ImmutableMap.of();
    }

    protected int totalTests( List<TestCase> cases ) {
        return cases.size();
    }

    protected int disabledTests() {
        /* We don't have the information on how many samples are disabled. :( */
        return 0;
    }

    protected int errorsInTests( List<TestCase> cases ) {
        long accumulator = 0;

        for ( TestCase singleCase : cases ) {
            // I know I sound like a broken record, but under JDK8 it'd look better
            accumulator += singleCase.getErrors().size();
        }

        return Ints.checkedCast( accumulator );
    }

    protected int failuresInTests( List<TestCase> cases ) {
        long accumulator = 0;

        for ( TestCase singleCase : cases ) {
            // This one time in JDK8 camp...
            accumulator += singleCase.getFailures().size();
        }

        return Ints.checkedCast( accumulator );
    }

    protected int skippedTests() {
        /* We don't have the information on how many tests were skipped */
        return 0;
    }

    protected long elapsedTimeInMillis( List<TestCase> cases ) {
        long accumulator = 0;

        for ( TestCase singleCase : cases ) {
            // This one time in JDK8 camp...
            accumulator += MILLIS_IN_SECOND * singleCase.getTotalTimeSpentInSeconds();
        }

        return Ints.checkedCast( accumulator );
    }

    protected long timestamp() {
        /* We don't have the accurate information and we shouldn't put misleading information (like current
         * timestamp) here.
         */
        return 0;
    }
}
