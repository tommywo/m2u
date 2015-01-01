package com.tguzik.m2u.data.jmeter;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.Preconditions;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.StringUtils;

@Immutable
@ParametersAreNonnullByDefault
public final class AssertionResult extends BaseObject {
    @XStreamAlias( "name" )
    @XmlElement( name = "name" )
    private final String name;

    @XStreamAlias( "failure" )
    @XmlElement( name = "failure" )
    private final boolean failure;

    @XStreamAlias( "error" )
    @XmlElement( name = "error" )
    private final boolean error;

    @XStreamAlias( "failureMessage" )
    @XmlElement( name = "failureMessage" )
    private final String failureMessage;

    /** Needed by jaxb */
    private AssertionResult() {
        this( StringUtils.EMPTY, false, false, StringUtils.EMPTY );
    }

    public AssertionResult( String name, boolean failure, boolean error, String failureMessage ) {
        this.name = Preconditions.checkNotNull( name );
        this.failure = Preconditions.checkNotNull( failure );
        this.error = Preconditions.checkNotNull( error );
        this.failureMessage = Preconditions.checkNotNull( failureMessage );
    }

    public String getName() {
        return name;
    }

    public boolean isFailure() {
        return failure;
    }

    public boolean isError() {
        return error;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
