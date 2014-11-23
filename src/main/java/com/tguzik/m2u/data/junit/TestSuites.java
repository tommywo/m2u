package com.tguzik.m2u.data.junit;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import com.google.common.collect.Lists;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Represents collection of all of the tests ran
 *
 * @author Tomek
 */
@XStreamAlias("testsuites")
@XmlRootElement(name="testsuites")
public class TestSuites extends BaseObject {
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
    @XStreamAlias("name")
    @XmlAttribute(name="name")
    private String testGroupName;

    @XStreamAsAttribute
    @XStreamAlias("tests")
    @XmlAttribute(name="tests")
    private int totalTests;

    /** in milliseconds */
    @XStreamAsAttribute
    @XStreamAlias("time")
    @XmlAttribute(name="time")
    private long totalTimeSpent;

    @XStreamImplicit
    @XStreamAlias("testsuite")
    @XmlList
    @XmlElement(name="testsuite", type = TestSuite.class)
    private final List<TestSuite> testSuites;

    public TestSuites() {
        this.testSuites = Lists.newArrayList();
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void addTestSuite( TestSuite ts ) {
        this.disabledTests += ts.getDisabledTests();
        this.errorsInTests += ts.getErrorsInTests();
        this.failuresInTests += ts.getFailuresInTests();
        this.totalTests += ts.getTotalTests();

        this.testSuites.add( ts );
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

    public String getTestGroupName() {
        return testGroupName;
    }

    public void setTestGroupName( String testsuitesGroupName ) {
        this.testGroupName = testsuitesGroupName;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests( int totalTests ) {
        this.totalTests = totalTests;
    }

    public long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent( long totalTimeSpent ) {
        this.totalTimeSpent = totalTimeSpent;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
