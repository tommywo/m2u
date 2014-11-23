package com.tguzik.m2u.data.junit;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author Tomek
 * @see <pre>
 * http://stackoverflow.com/questions/4922867/junit-xml-format-specification-that-hudson-supports
 * </pre>
 */
@XStreamAlias("testsuite")
public class TestSuite extends BaseObject {
    @XStreamAsAttribute
    @XStreamAlias("disabled")
    @XmlAttribute(name="disabled")
    private int disabledTests;

    @XStreamAsAttribute
    @XStreamAlias("errors")
    @XmlAttribute(name="errors")
    private int errorsInTests;

    @XStreamAsAttribute
    @XStreamAlias("failures")
    @XmlAttribute(name="failures")
    private int failuresInTests;

    @XStreamAsAttribute
    @XStreamAlias("tests")
    @XmlAttribute(name="tests")
    private int totalTests;

    @XStreamAsAttribute
    @XStreamAlias("hostname")
    @XmlAttribute(name="hostname")
    private String hostname;

    @XStreamAsAttribute
    @XStreamAlias("id")
    @XmlAttribute(name="id")
    private String id;

    @XStreamAsAttribute
    @XStreamAlias("name")
    @XmlAttribute(name="name")
    private String name;

    @XStreamAsAttribute
    @XStreamAlias("package")
    @XmlAttribute(name="package")
    private String packageName;

    @XStreamAsAttribute
    @XStreamAlias("skipped")
    @XmlAttribute(name="skipped")
    private int skippedTests;

    @XStreamAsAttribute
    @XStreamAlias("tests")
    @XmlAttribute(name="tests")
    private long timeSpentInMillis;

    @XStreamAsAttribute
    @XStreamAlias("timestamp")
    @XmlAttribute(name="timestamp")
    private long timestamp;

    @XStreamAlias("properties")
    @XmlElement(name="properties")
    private final Map<String, String> properties;

    @XStreamImplicit
    @XStreamAlias("testcase")
    @XmlList
    @XmlElement(name="testcase")
    private final List<TestCase> testCases;

    public TestSuite() {
        this.properties = Maps.newHashMap();
        this.testCases = Lists.newArrayList();
    }

    public int getDisabledTests() {
        return disabledTests;
    }

    public void setDisabledTests( int disabledTests ) {
        this.disabledTests = disabledTests;
    }

    public int getErrorsInTests() {
        return errorsInTests;
    }

    public void setErrorsInTests( int errorsInTests ) {
        this.errorsInTests = errorsInTests;
    }

    public int getFailuresInTests() {
        return failuresInTests;
    }

    public void setFailuresInTests( int failuresInTests ) {
        this.failuresInTests = failuresInTests;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests( int totalTests ) {
        this.totalTests = totalTests;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname( String hostname ) {
        this.hostname = hostname;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName( String packageName ) {
        this.packageName = packageName;
    }

    public int getSkippedTests() {
        return skippedTests;
    }

    public void setSkippedTests( int skippedTests ) {
        this.skippedTests = skippedTests;
    }

    public long getTimeSpent() {
        return timeSpentInMillis;
    }

    public void setTimeSpent( long timeSpent ) {
        this.timeSpentInMillis = timeSpent;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp( long timestamp ) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void addProperty( String key, Object value ) {
        this.properties.put( key, String.format( "%s", value ) );
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void addTestCase( TestCase tc ) {
        this.errorsInTests += tc.getErrors().size();
        this.failuresInTests += tc.getFailures().size();
        this.timeSpentInMillis += tc.getTotalTimeSpent();
        this.testCases.add( tc );
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
