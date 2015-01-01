package com.tguzik.m2u.conversion;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.base.Function;
import com.tguzik.m2u.data.jmeter.AssertionResult;
import com.tguzik.m2u.data.junit.Error;

/**
 * @author Tomasz Guzik <tomek@tguzik.com>
 */
@ThreadSafe
@ParametersAreNonnullByDefault
class AssertionResultToError implements Function<AssertionResult, Error> {
    // This class probably wouldn't even exist if we required JDK8

    @Override
    public Error apply( AssertionResult input ) {
        return new Error( input.getFailureMessage(), input.getName() );
    }
}
