package com.tguzik.m2u.data.jmeter;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Some of the fields might seem to have redundant @XStreamAlias annotations,
 * but that comes in handy when renaming the field.
 *
 * @author Tomek
 */
@NotThreadSafe
@ParametersAreNullableByDefault
public class Sample extends BaseObject {
    @XStreamImplicit
    @XStreamAlias( "assertionResult" )
    @XmlList
    @XmlElement( name = "assertionResult", type = AssertionResult.class )
    private List<AssertionResult> assertionResults;

    /** in milliseconds */
    @XStreamAsAttribute
    @XStreamAlias( "t" )
    @XmlAttribute( name = "t" )
    private Integer elapsedTime;

    /** in milliseconds */
    @XStreamAsAttribute
    @XStreamAlias( "lt" )
    @XmlAttribute( name = "lt" )
    private Integer latency;

    @XStreamAsAttribute
    @XStreamAlias( "ts" )
    @XmlAttribute( name = "ts" )
    private Long timestamp;

    @XStreamAsAttribute
    @XStreamAlias( "s" )
    @XmlAttribute( name = "s" )
    private boolean success;

    @XStreamAsAttribute
    @XStreamAlias( "lb" )
    @XmlAttribute( name = "lb" )
    private String label;

    @XStreamAsAttribute
    @XStreamAlias( "rc" )
    @XmlAttribute( name = "rc" )
    private Integer httpResponseCode;

    @XStreamAsAttribute
    @XStreamAlias( "rm" )
    @XmlAttribute( name = "rm" )
    private String httpResponseMessage;

    @XStreamAsAttribute
    @XStreamAlias( "tn" )
    @XmlAttribute( name = "tn" )
    private String threadName;

    @XStreamAsAttribute
    @XStreamAlias( "dt" )
    @XmlAttribute( name = "dt" )
    private String dataType;

    @XStreamAsAttribute
    @XStreamAlias( "de" )
    @XmlAttribute( name = "de" )
    private String dataEncoding;

    @XStreamAsAttribute
    @XStreamAlias( "by" )
    @XmlAttribute( name = "by" )
    private Integer bytes;

    @XStreamAsAttribute
    @XStreamAlias( "ng" )
    @XmlAttribute( name = "ng" )
    private Integer numberOfActiveThreadsInGroup;

    @XStreamAsAttribute
    @XStreamAlias( "na" )
    @XmlAttribute( name = "na" )
    private Integer numberOfAllActiveThreads;

    @XStreamAsAttribute
    @XStreamAlias( "hn" )
    @XmlAttribute( name = "hn" )
    private String hostname;

    @XStreamAlias( "responseHeader" )
    @XmlElement( name = "responseHeader" )
    private String responseHeader;

    @XStreamAlias( "requestHeader" )
    @XmlElement( name = "requestHeader" )
    private String requestHeader;

    @XStreamAlias( "responseData" )
    @XmlElement( name = "responseData" )
    private String responseData;

    @XStreamAlias( "samplerData" )
    @XmlElement( name = "samplerData" )
    private String samplerData;

    @XStreamAlias( "cookies" )
    @XmlElement( name = "cookies" )
    private String cookies;

    @XStreamAlias( "method" )
    @XmlElement( name = "method" )
    private String method;

    @XStreamAlias( "queryString" )
    @XmlElement( name = "queryString" )
    private String queryString;

    @XStreamAlias( "java.net.URL" )
    @XmlElement( name = "java.net.URL" )
    private String url;

    public Sample() {
        this.assertionResults = Lists.newArrayList();
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
        this.assertionResults = Preconditions.checkNotNull( assertionResults );
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

    public void setAssertionResults( List<AssertionResult> assertionResults ) {
        this.assertionResults = assertionResults;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime( Integer elapsedTime ) {
        this.elapsedTime = elapsedTime;
    }

    public Integer getLatency() {
        return latency;
    }

    public void setLatency( Integer latency ) {
        this.latency = latency;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp( Long timestamp ) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess( boolean success ) {
        this.success = success;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel( String label ) {
        this.label = label;
    }

    public Integer getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode( Integer httpResponseCode ) {
        this.httpResponseCode = httpResponseCode;
    }

    public String getHttpResponseMessage() {
        return httpResponseMessage;
    }

    public void setHttpResponseMessage( String httpResponseMessage ) {
        this.httpResponseMessage = httpResponseMessage;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName( String threadName ) {
        this.threadName = threadName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType( String dataType ) {
        this.dataType = dataType;
    }

    public String getDataEncoding() {
        return dataEncoding;
    }

    public void setDataEncoding( String dataEncoding ) {
        this.dataEncoding = dataEncoding;
    }

    public Integer getBytes() {
        return bytes;
    }

    public void setBytes( Integer bytes ) {
        this.bytes = bytes;
    }

    public Integer getNumberOfActiveThreadsInGroup() {
        return numberOfActiveThreadsInGroup;
    }

    public void setNumberOfActiveThreadsInGroup( Integer numberOfActiveThreadsInGroup ) {
        this.numberOfActiveThreadsInGroup = numberOfActiveThreadsInGroup;
    }

    public Integer getNumberOfAllActiveThreads() {
        return numberOfAllActiveThreads;
    }

    public void setNumberOfAllActiveThreads( Integer numberOfAllActiveThreads ) {
        this.numberOfAllActiveThreads = numberOfAllActiveThreads;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname( String hostname ) {
        this.hostname = hostname;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader( String responseHeader ) {
        this.responseHeader = responseHeader;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader( String requestHeader ) {
        this.requestHeader = requestHeader;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData( String responseData ) {
        this.responseData = responseData;
    }

    public String getSamplerData() {
        return samplerData;
    }

    public void setSamplerData( String samplerData ) {
        this.samplerData = samplerData;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies( String cookies ) {
        this.cookies = cookies;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod( String method ) {
        this.method = method;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString( String queryString ) {
        this.queryString = queryString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    @Override
    public String toString() {
        return toString( MULTILINE_NO_ADDRESS_STYLE );
    }
}
