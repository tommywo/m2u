package com.tguzik.m2u.conversion;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.Objects;

import com.tguzik.m2u.conversion.readability.JMeter;
import com.tguzik.m2u.conversion.readability.JUnit;
import com.tguzik.m2u.data.jmeter.TestResults;
import com.tguzik.m2u.data.junit.TestSuite;
import com.tguzik.m2u.data.junit.TestSuites;

/**
 * Converts Jmeter data structure to the one reflecting Junit results. This class is thread safe because it has no
 * state.
 */
@ThreadSafe
@ParametersAreNonnullByDefault
public class JmeterToJunitDataStructure {
    /* On JDK8 this class would implement BiFunction interface... */
    private final ResultsToTestSuites resultsToTestSuites;

    public JmeterToJunitDataStructure() {
        this( new ResultsToTestSuites() );
    }

    public JmeterToJunitDataStructure( ResultsToTestSuites resultsToTestSuites ) {
        this.resultsToTestSuites = Objects.requireNonNull( resultsToTestSuites );
    }

    @JUnit
    public TestSuites apply( String testSuiteName, @JMeter TestResults jmeter ) {
        final List<TestSuite> testSuites = resultsToTestSuites.apply( testSuiteName, jmeter );

        return new TestSuites( testSuites,
                               testSuiteName,
                               disabledTests( testSuites ),
                               errorsInTests( testSuites ),
                               failuresInTests( testSuites ),
                               totalTests( testSuites ),
                               totalTimeSpent( testSuites ) );
    }

    private int disabledTests( List<TestSuite> input ) {
        int accumulator = 0;

        for ( TestSuite suite : input ) {
            accumulator += suite.getDisabledTests();
        }

        return accumulator;
    }

    private int errorsInTests( List<TestSuite> input ) {
        int accumulator = 0;

        for ( TestSuite suite : input ) {
            accumulator += suite.getErrorsInTests();
        }

        return accumulator;
    }

    private int failuresInTests( List<TestSuite> input ) {
        int accumulator = 0;

        for ( TestSuite suite : input ) {
            accumulator += suite.getFailuresInTests();
        }

        return accumulator;
    }

    private int totalTests( List<TestSuite> input ) {
        int accumulator = 0;

        for ( TestSuite suite : input ) {
            accumulator += suite.getTotalTests();
        }

        return accumulator;
    }

    private long totalTimeSpent( List<TestSuite> input ) {
        long accumulator = 0;

        for ( TestSuite suite : input ) {
            accumulator += suite.getTimeSpentInMillis();
        }

        return accumulator;
    }
}

