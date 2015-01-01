package com.tguzik.m2u.data.jmeter;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.commons.lang3.StringUtils;

/**
 * View of the data parsed:
 * <p/>
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="UTF-8"?>
 * <testResults version="1.2">
 *     <sample t="0" lt="0" ts="0" s="true" lb="Label" rc="200" rm="OK" tn="Thread Group" dt="text" de="" by="0"
 *     ng="0" na="0" hn="0">
 *       <responseHeader class="java.lang.String">text</responseHeader>
 *       <requestHeader class="java.lang.String">text</requestHeader>
 *       <responseData class="java.lang.String"></responseData>
 *       <samplerData class="java.lang.String">text</samplerData>
 *     </sample>
 *     <httpSample t="0" lt="0" ts="0" s="true" lb="Label" rc="200" rm="OK" tn="Thread Group" dt="text" de="utf-8"
 *     by="0" ng="0" na="0" hn="0">
 *       <assertionResult>
 *         <name>Assertion name</name>
 *         <failure>false</failure>
 *         <error>false</error>
 *         <failureMessage>text</failureMessage>
 *       </assertionResult>
 *       <responseHeader class="java.lang.String">text</responseHeader>
 *       <requestHeader class="java.lang.String">text</requestHeader>
 *       <responseData class="java.lang.String">text</responseData>
 *       <cookies class="java.lang.String">text</cookies>
 *       <method class="java.lang.String">text</method>
 *       <queryString class="java.lang.String">text</queryString>
 *       <java.net.URL>text</java.net.URL>
 *     </httpSample>
 *
 *     <!-- more sample and httpSample elements -->
 *
 * </testResults>
 * }
 * </pre>
 *
 * @author Tomek
 * @see http://jmeter.apache.org/usermanual/listeners.html#attributes
 */
@XStreamAlias( "testResults" )
@NotThreadSafe
@ParametersAreNonnullByDefault
@XmlRootElement( name = "testResults" )
public final class TestResults extends BaseObject {
    @XStreamAsAttribute
    @XmlAttribute( name = "version" )
    private final String version;

    @XStreamImplicit
    @XStreamAlias( impl = Sample.class, value = "sample" )
    @XmlList
    @XmlElement( name = "sample", type = Sample.class )
    private final List<Sample> samples;

    @XStreamImplicit
    @XStreamAlias( impl = Sample.class, value = "httpSample" )
    @XmlList
    @XmlElement( name = "httpSample", type = Sample.class )
    private final List<Sample> httpSamples;

    /** Needed by jaxb */
    private TestResults() {
        this( StringUtils.EMPTY, Lists.<Sample>newArrayList(), Lists.<Sample>newArrayList() );
    }

    public TestResults( String version, List<Sample> samples, List<Sample> httpSamples ) {
        this.version = Preconditions.checkNotNull( version );
        this.samples = ImmutableList.copyOf( samples );
        this.httpSamples = ImmutableList.copyOf( httpSamples );
    }

    public String getVersion() {
        return version;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public List<Sample> getHttpSamples() {
        return httpSamples;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
