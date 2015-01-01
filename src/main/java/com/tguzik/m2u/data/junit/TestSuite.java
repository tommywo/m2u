package com.tguzik.m2u.data.junit;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Tomek
 * @see <pre>
 * http://stackoverflow.com/questions/4922867/junit-xml-format-specification-that-hudson-supports
 * </pre>
 */
@XStreamAlias( "testsuite" )
@Immutable
@ParametersAreNonnullByDefault
public final class TestSuite extends BaseObject {
    @XStreamAsAttribute
    @XStreamAlias( "id" )
    @XmlAttribute( name = "id" )
    private final String id;

    @XStreamAsAttribute
    @XStreamAlias( "name" )
    @XmlAttribute( name = "name" )
    private final String name;

    @XStreamAsAttribute
    @XStreamAlias( "package" )
    @XmlAttribute( name = "package" )
    private final String packageName;

    @XStreamAsAttribute
    @XStreamAlias( "hostname" )
    @XmlAttribute( name = "hostname" )
    private final String hostname;

    @XStreamAsAttribute
    @XStreamAlias( "tests" )
    @XmlAttribute( name = "tests" )
    private final int totalTests;

    @XStreamAsAttribute
    @XStreamAlias( "disabled" )
    @XmlAttribute( name = "disabled" )
    private final int disabledTests;

    @XStreamAsAttribute
    @XStreamAlias( "errors" )
    @XmlAttribute( name = "errors" )
    private final int errorsInTests;

    @XStreamAsAttribute
    @XStreamAlias( "failures" )
    @XmlAttribute( name = "failures" )
    private final int failuresInTests;

    @XStreamAsAttribute
    @XStreamAlias( "skipped" )
    @XmlAttribute( name = "skipped" )
    private final int skippedTests;

    @XStreamAsAttribute
    @XStreamAlias( "tests" )
    @XmlAttribute( name = "tests" )
    private final long timeSpentInMillis;

    @XStreamAsAttribute
    @XStreamAlias( "timestamp" )
    @XmlAttribute( name = "timestamp" )
    private final long timestamp;

    @XStreamAlias( "properties" )
    @XmlElement( name = "properties" )
    private final Map<String, String> properties;

    @XStreamImplicit
    @XStreamAlias( "testcase" )
    @XmlList
    @XmlElement( name = "testcase" )
    private final List<TestCase> testCases;

    private TestSuite() {
        this( Maps.<String, String>newHashMap(),
              Lists.<TestCase>newArrayList(),
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              0,
              0,
              0,
              0,
              0,
              0L,
              0L );
    }

    public TestSuite( Map<String, String> properties,
                      List<TestCase> testCases,
                      String id,
                      String name,
                      String packageName,
                      String hostname,
                      int totalTests,
                      int disabledTests,
                      int errorsInTests,
                      int failuresInTests,
                      int skippedTests,
                      long timeSpentInMillis,
                      long timestamp ) {
        this.properties = ImmutableMap.copyOf( properties );
        this.testCases = ImmutableList.copyOf( testCases );
        this.id = Preconditions.checkNotNull( id );
        this.name = Preconditions.checkNotNull( name );
        this.packageName = Preconditions.checkNotNull( packageName );
        this.hostname = Preconditions.checkNotNull( hostname );
        this.totalTests = Preconditions.checkNotNull( totalTests );
        this.disabledTests = Preconditions.checkNotNull( disabledTests );
        this.errorsInTests = Preconditions.checkNotNull( errorsInTests );
        this.failuresInTests = Preconditions.checkNotNull( failuresInTests );
        this.skippedTests = Preconditions.checkNotNull( skippedTests );
        this.timeSpentInMillis = Preconditions.checkNotNull( timeSpentInMillis );
        this.timestamp = Preconditions.checkNotNull( timestamp );
    }

//    public void addTestCase( TestCase tc ) {
//        this.errorsInTests += tc.getErrors().size();
//        this.failuresInTests += tc.getFailures().size();
//        this.timeSpentInMillis += tc.getTotalTimeSpent();
//        this.testCases.add( tc );
//    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getHostname() {
        return hostname;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public int getDisabledTests() {
        return disabledTests;
    }

    public int getErrorsInTests() {
        return errorsInTests;
    }

    public int getFailuresInTests() {
        return failuresInTests;
    }

    public int getSkippedTests() {
        return skippedTests;
    }

    public long getTimeSpentInMillis() {
        return timeSpentInMillis;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
