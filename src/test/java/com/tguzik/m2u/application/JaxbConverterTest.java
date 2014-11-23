package com.tguzik.m2u.application;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.tguzik.objects.BaseObject;
import com.tguzik.tests.Normalize;
import org.junit.Before;
import org.junit.Test;

public class JaxbConverterTest {
    private JaxbConverter<TestingObject> converter;
    private TestingObject testObject;

    @Before
    public void setUp() throws Exception {
        this.converter = new JaxbConverter<>( TestingObject.class );
        this.testObject = new TestingObject( "text", 42 );
    }

    @Test
    public void serialize_converts_object_to_string_with_xml_successfully() throws Exception {
        final String expected = Normalize.newLines( "<obj string=\"text\">\n    <number>42</number>\n</obj>" );
        final String actual = converter.serialize( testObject ).trim();

        assertThat( Normalize.newLines( actual ) ).isEqualTo( expected );
    }

    @Test
    public void serialize_converts_object_to_stream_successfully() throws Exception {
        final String expected = Normalize.newLines( "<obj string=\"text\">\n    <number>42</number>\n</obj>" );
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();

        converter.serialize( testObject, stream );

        final String actual = stream.toString().trim();

        assertThat( Normalize.newLines( actual ) ).isEqualTo( expected );
    }

    @Test( expected = NullPointerException.class )
    public void serialize_object_throws_NullPointerException_given_null_object() throws Exception {
        converter.serialize( null );
    }

    @Test( expected = NullPointerException.class )
    public void serialize_object_stream_throws_NullPointerException_given_null_object() throws Exception {
        converter.serialize( null, new ByteArrayOutputStream() );
    }

    @Test( expected = NullPointerException.class )
    public void serialize_object_stream_throws_NullPointerException_given_stream() throws Exception {
        converter.serialize( testObject, null );
    }

    @Test
    public void deserialize_reads_object_from_string() throws Exception {
        final String input = "<obj string=\"text in attribute\"><number>42</number></obj>";
        final TestingObject expected = new TestingObject( "text in attribute", 42 );
        final TestingObject actual = converter.deserialize( input );

        assertThat( actual ).isEqualTo( expected ).isEqualToComparingFieldByField( expected );
    }

    @Test
    public void deserialize_reads_object_from_stream() throws Exception {
        final String input = "<obj string=\"text in attribute\"><number>42</number></obj>";
        final TestingObject expected = new TestingObject( "text in attribute", 42 );
        final TestingObject actual = converter.deserialize( new ByteArrayInputStream( input.getBytes() ) );

        assertThat( actual ).isEqualTo( expected ).isEqualToComparingFieldByField( expected );
    }

    @Test( expected = NullPointerException.class )
    public void deserialize_string_throws_NullPointerException_given_null_object() throws Exception {
        converter.deserialize( (String) null );
    }

    @Test( expected = NullPointerException.class )
    public void deserialize_stream_throws_NullPointerException_given_null_object() throws Exception {
        converter.deserialize( (InputStream) null );
    }

    @XmlRootElement( name = "obj" )
    private static class TestingObject extends BaseObject {
        @XmlAttribute
        String string;

        @XmlElement
        int number;

        public TestingObject() {
        }

        public TestingObject( String string, int number ) {
            this.string = string;
            this.number = number;
        }
    }
}
