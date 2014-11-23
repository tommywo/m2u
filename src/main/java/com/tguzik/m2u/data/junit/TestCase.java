package com.tguzik.m2u.data.junit;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;

import com.google.common.collect.Lists;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias( "testcase" )
public class TestCase extends BaseObject {
    @XStreamAsAttribute
    @XStreamAlias( "assertions" )
    @XmlAttribute( name = "assertions" )
    private int assertions;

    @XStreamAsAttribute
    @XStreamAlias( "classname" )
    @XmlAttribute( name = "classname" )
    private String classname;

    @XStreamAsAttribute
    @XStreamAlias( "name" )
    @XmlAttribute( name = "name" )
    private String testName;

    @XStreamAsAttribute
    @XStreamAlias( "status" )
    @XmlAttribute( name = "status" )
    private String status;

    @XStreamAsAttribute
    @XStreamAlias( "time" )
    @XmlAttribute( name = "time" )
    private double totalTimeSpentInSeconds;

    @XStreamImplicit
    @XStreamAlias( "error" )
    @XmlList
    @XmlElement( name = "error", type = Error.class )
    private final List<Error> errors;

    @XStreamImplicit
    @XStreamAlias( "failure" )
    @XmlList
    @XmlElement( name = "failure" )
    private final List<Failure> failures;

    @XStreamImplicit( itemFieldName = "system-out" )
    @XStreamAlias( "system-out" )
    @XmlList
    @XmlElement( name = "system-out" )
    private final List<String> systemOut;

    @XStreamImplicit
    @XStreamAlias( "system-err" )
    @XmlList
    @XmlElement( name = "system-err" )
    private final List<String> systemErr;

    public TestCase() {
        this.errors = Lists.newArrayList();
        this.failures = Lists.newArrayList();
        this.systemOut = Lists.newArrayList();
        this.systemErr = Lists.newArrayList();
    }

    public int getAssertions() {
        return assertions;
    }

    public void setAssertions( int assertions ) {
        this.assertions = assertions;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname( String classname ) {
        this.classname = classname;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName( String testName ) {
        this.testName = testName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public double getTotalTimeSpent() {
        return totalTimeSpentInSeconds;
    }

    public void setTotalTimeSpent( long totalTimeSpent ) {
        this.totalTimeSpentInSeconds = totalTimeSpent;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void addError( Error e ) {
        this.errors.add( e );
    }

    public List<Failure> getFailures() {
        return failures;
    }

    public void addFailure( Failure f ) {
        this.failures.add( f );
    }

    public List<String> getSystemOut() {
        return systemOut;
    }

    public void addSystemOut( String systemOut ) {
        this.systemOut.add( systemOut );
    }

    public List<String> getSystemErr() {
        return systemErr;
    }

    public void addSystemErr( String systemErr ) {
        this.systemErr.add( systemErr );
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
