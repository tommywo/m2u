package com.tguzik.m2u.data.jmeter;

import javax.xml.bind.annotation.XmlElement;

import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias( "assertionResult" )
public class AssertionResult extends BaseObject {
    @XStreamAlias( "name" )
    @XmlElement( name = "name" )
    private String name;

    @XStreamAlias( "failure" )
    @XmlElement( name = "failure" )
    private boolean failure;

    @XStreamAlias( "error" )
    @XmlElement( name = "error" )
    private boolean error;

    @XStreamAlias( "failureMessage" )
    @XmlElement( name = "failureMessage" )
    private String failureMessage;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure( boolean failure ) {
        this.failure = failure;
    }

    public boolean isError() {
        return error;
    }

    public void setError( boolean error ) {
        this.error = error;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage( String failureMessage ) {
        this.failureMessage = failureMessage;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
