package com.tguzik.m2u.data.junit;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.commons.lang3.StringUtils;

@XStreamAlias( "testcase" )
@Immutable
@ParametersAreNonnullByDefault
public final class TestCase extends BaseObject {
    @XStreamAsAttribute
    @XStreamAlias( "classname" )
    @XmlAttribute( name = "classname" )
    private final String classname;

    @XStreamAsAttribute
    @XStreamAlias( "assertions" )
    @XmlAttribute( name = "assertions" )
    private final int assertions;

    @XStreamAsAttribute
    @XStreamAlias( "name" )
    @XmlAttribute( name = "name" )
    private final String testName;

    @XStreamAsAttribute
    @XStreamAlias( "status" )
    @XmlAttribute( name = "status" )
    private final String status;

    @XStreamAsAttribute
    @XStreamAlias( "time" )
    @XmlAttribute( name = "time" )
    private final double totalTimeSpentInSeconds;

    @XStreamImplicit
    @XStreamAlias( "error" )
    @XmlList
    @XmlElement( name = "error", type = Error.class )
    private final List<Error> errors;

    @XStreamImplicit
    @XStreamAlias( "failure" )
    @XmlList
    @XmlElement( name = "failure" )
    private final List<Error> failures;

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

    private TestCase() {
        this( Lists.<Error>newArrayList(),
              Lists.<Error>newArrayList(),
              Lists.<String>newArrayList(),
              Lists.<String>newArrayList(),
              StringUtils.EMPTY,
              0,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              0 );
    }

    public TestCase( List<Error> errors,
                     List<Error> failures,
                     List<String> systemOut,
                     List<String> systemErr,
                     String classname,
                     int assertions,
                     String testName,
                     String status,
                     double totalTimeSpentInSeconds ) {
        //
        this.errors = ImmutableList.copyOf( errors );
        this.failures = ImmutableList.copyOf( failures );
        this.systemOut = ImmutableList.copyOf( systemOut );
        this.systemErr = ImmutableList.copyOf( systemErr );
        this.classname = Preconditions.checkNotNull( classname );
        this.assertions = Preconditions.checkNotNull( assertions );
        this.testName = Preconditions.checkNotNull( testName );
        this.status = Preconditions.checkNotNull( status );
        this.totalTimeSpentInSeconds = Preconditions.checkNotNull( totalTimeSpentInSeconds );
    }

    public String getClassname() {
        return classname;
    }

    public int getAssertions() {
        return assertions;
    }

    public String getTestName() {
        return testName;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalTimeSpentInSeconds() {
        return totalTimeSpentInSeconds;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public List<Error> getFailures() {
        return failures;
    }

    public List<String> getSystemOut() {
        return systemOut;
    }

    public List<String> getSystemErr() {
        return systemErr;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
