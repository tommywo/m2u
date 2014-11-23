package com.tguzik.m2u.data.jmeter;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias( impl = HttpSample.class, value = "httpSample" )
public class HttpSample extends AbstractSample {
    // no changes needed
}
