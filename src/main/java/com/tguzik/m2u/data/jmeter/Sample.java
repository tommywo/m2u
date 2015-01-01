package com.tguzik.m2u.data.jmeter;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
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

/**
 * Some of the fields might seem to have redundant @XStreamAlias annotations,
 * but that comes in handy when renaming the field.
 *
 * @author Tomek
 */
@Immutable
@ParametersAreNonnullByDefault
public final class Sample extends BaseObject {
    @XStreamImplicit
    @XStreamAlias( "assertionResult" )
    @XmlList
    @XmlElement( name = "assertionResult", type = AssertionResult.class )
    private final List<AssertionResult> assertionResults;

    /** in milliseconds */
    @XStreamAsAttribute
    @XStreamAlias( "t" )
    @XmlAttribute( name = "t" )
    private final Integer elapsedTime;

    /** in milliseconds */
    @XStreamAsAttribute
    @XStreamAlias( "lt" )
    @XmlAttribute( name = "lt" )
    private final Integer latency;

    @XStreamAsAttribute
    @XStreamAlias( "ts" )
    @XmlAttribute( name = "ts" )
    private final Long timestamp;

    @XStreamAsAttribute
    @XStreamAlias( "s" )
    @XmlAttribute( name = "s" )
    private final boolean success;

    @XStreamAsAttribute
    @XStreamAlias( "lb" )
    @XmlAttribute( name = "lb" )
    private final String label;

    @XStreamAsAttribute
    @XStreamAlias( "rc" )
    @XmlAttribute( name = "rc" )
    private final Integer httpResponseCode;

    @XStreamAsAttribute
    @XStreamAlias( "rm" )
    @XmlAttribute( name = "rm" )
    private final String httpResponseMessage;

    @XStreamAsAttribute
    @XStreamAlias( "tn" )
    @XmlAttribute( name = "tn" )
    private final String threadName;

    @XStreamAsAttribute
    @XStreamAlias( "dt" )
    @XmlAttribute( name = "dt" )
    private final String dataType;

    @XStreamAsAttribute
    @XStreamAlias( "de" )
    @XmlAttribute( name = "de" )
    private final String dataEncoding;

    @XStreamAsAttribute
    @XStreamAlias( "by" )
    @XmlAttribute( name = "by" )
    private final Integer bytes;

    @XStreamAsAttribute
    @XStreamAlias( "ng" )
    @XmlAttribute( name = "ng" )
    private final Integer numberOfActiveThreadsInGroup;

    @XStreamAsAttribute
    @XStreamAlias( "na" )
    @XmlAttribute( name = "na" )
    private final Integer numberOfAllActiveThreads;

    @XStreamAsAttribute
    @XStreamAlias( "hn" )
    @XmlAttribute( name = "hn" )
    private final String hostname;

    @XStreamAlias( "responseHeader" )
    @XmlElement( name = "responseHeader" )
    private final String responseHeader;

    @XStreamAlias( "requestHeader" )
    @XmlElement( name = "requestHeader" )
    private final String requestHeader;

    @XStreamAlias( "responseData" )
    @XmlElement( name = "responseData" )
    private final String responseData;

    @XStreamAlias( "samplerData" )
    @XmlElement( name = "samplerData" )
    private final String samplerData;

    @XStreamAlias( "cookies" )
    @XmlElement( name = "cookies" )
    private final String cookies;

    @XStreamAlias( "method" )
    @XmlElement( name = "method" )
    private final String method;

    @XStreamAlias( "queryString" )
    @XmlElement( name = "queryString" )
    private final String queryString;

    @XStreamAlias( "java.net.URL" )
    @XmlElement( name = "java.net.URL" )
    private final String url;

    /** Needed by jaxb */
    private Sample() {
        this( Lists.<AssertionResult>newArrayList(),
              0,
              0,
              0L,
              false,
              StringUtils.EMPTY,
              0,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              0,
              0,
              0,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY,
              StringUtils.EMPTY );
    }

    @ParametersAreNonnullByDefault
    public Sample( List<AssertionResult> assertionResults,
                   Integer elapsedTime,
                   Integer latency,
                   Long timestamp,
                   boolean success,
                   String label,
                   Integer httpResponseCode,
                   String httpResponseMessage,
                   String threadName,
                   String dataType,
                   String dataEncoding,
                   Integer bytes,
                   Integer numberOfActiveThreadsInGroup,
                   Integer numberOfAllActiveThreads,
                   String hostname,
                   String responseHeader,
                   String requestHeader,
                   String responseData,
                   String samplerData,
                   String cookies,
                   String method,
                   String queryString,
                   String url ) {
        //
        this.assertionResults = ImmutableList.copyOf( assertionResults );
        this.elapsedTime = Preconditions.checkNotNull( elapsedTime );
        this.latency = Preconditions.checkNotNull( latency );
        this.timestamp = Preconditions.checkNotNull( timestamp );
        this.success = Preconditions.checkNotNull( success );
        this.label = Preconditions.checkNotNull( label );
        this.httpResponseCode = Preconditions.checkNotNull( httpResponseCode );
        this.httpResponseMessage = Preconditions.checkNotNull( httpResponseMessage );
        this.threadName = Preconditions.checkNotNull( threadName );
        this.dataType = Preconditions.checkNotNull( dataType );
        this.dataEncoding = Preconditions.checkNotNull( dataEncoding );
        this.bytes = Preconditions.checkNotNull( bytes );
        this.numberOfActiveThreadsInGroup = Preconditions.checkNotNull( numberOfActiveThreadsInGroup );
        this.numberOfAllActiveThreads = Preconditions.checkNotNull( numberOfAllActiveThreads );
        this.hostname = Preconditions.checkNotNull( hostname );
        this.responseHeader = Preconditions.checkNotNull( responseHeader );
        this.requestHeader = Preconditions.checkNotNull( requestHeader );
        this.responseData = Preconditions.checkNotNull( responseData );
        this.samplerData = Preconditions.checkNotNull( samplerData );
        this.cookies = Preconditions.checkNotNull( cookies );
        this.method = Preconditions.checkNotNull( method );
        this.queryString = Preconditions.checkNotNull( queryString );
        this.url = Preconditions.checkNotNull( url );
    }

    public List<AssertionResult> getAssertionResults() {
        return assertionResults;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public Integer getLatency() {
        return latency;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getLabel() {
        return label;
    }

    public Integer getHttpResponseCode() {
        return httpResponseCode;
    }

    public String getHttpResponseMessage() {
        return httpResponseMessage;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataEncoding() {
        return dataEncoding;
    }

    public Integer getBytes() {
        return bytes;
    }

    public Integer getNumberOfActiveThreadsInGroup() {
        return numberOfActiveThreadsInGroup;
    }

    public Integer getNumberOfAllActiveThreads() {
        return numberOfAllActiveThreads;
    }

    public String getHostname() {
        return hostname;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public String getResponseData() {
        return responseData;
    }

    public String getSamplerData() {
        return samplerData;
    }

    public String getCookies() {
        return cookies;
    }

    public String getMethod() {
        return method;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
