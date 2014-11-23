package com.tguzik.m2u.xml;

import com.tguzik.m2u.data.jmeter.TestResults;
import com.tguzik.util.xstream.AbstractXStreamConverter;

public class JmeterXmlConverter extends AbstractXStreamConverter<TestResults> {
    public JmeterXmlConverter() {
        super( TestResults.class );
    }
}
