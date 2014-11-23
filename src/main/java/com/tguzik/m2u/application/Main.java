package com.tguzik.m2u.application;

import java.io.*;

import com.tguzik.m2u.conversion.JmeterToJunitDataStructure;
import com.tguzik.m2u.data.jmeter.TestResults;
import com.tguzik.m2u.data.junit.TestSuites;
import com.tguzik.m2u.xml.JmeterXmlConverter;
import com.tguzik.m2u.xml.JunitXmlConverter;

/**
 * Main class of the converter. Neither multiple input file nor multiple output
 * files are not supported yet - it would require some awkward command line
 * tricks.
 */
public class Main {

    public static final JmeterToJunitDataStructure JMETER_TO_JUNIT_DATA_STRUCTURE = new JmeterToJunitDataStructure();
    public static final JmeterXmlConverter JMETER_XML_CONVERTER = new JmeterXmlConverter();
    public static final JunitXmlConverter JUNIT_XML_CONVERTER = new JunitXmlConverter();

    public static void main( String[] args ) throws Exception {
        ProgramOptions po = CommandLineParser.parse( args );
        new Main( po ).process();
    }

    private final ProgramOptions programOptions;

    public Main( ProgramOptions po ) {
        this.programOptions = po;
    }

    public void process() throws IOException {
        try ( InputStreamReader input = new FileReader( programOptions.getInputFileName() );
              OutputStreamWriter output = new FileWriter( programOptions.getOutputFileName() ); ) {

            TestResults jmeterResults = JMETER_XML_CONVERTER.fromXML( input );
            TestSuites junitResults = JMETER_TO_JUNIT_DATA_STRUCTURE.apply( programOptions.getTestSuiteName(),
                                                                            jmeterResults );
            JUNIT_XML_CONVERTER.toXML( junitResults, output );
        }
    }
}
