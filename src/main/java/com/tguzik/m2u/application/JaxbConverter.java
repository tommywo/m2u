package com.tguzik.m2u.application;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;

import com.google.common.base.Preconditions;

/**
 * @author Tomasz Guzik <tomek@tguzik.com>
 */
@ParametersAreNonnullByDefault
public class JaxbConverter<T> {
    private final JAXBContext context;

    public JaxbConverter( Class<T> classToBeBound ) throws JAXBException {
        Preconditions.checkNotNull( classToBeBound, "Converter must be bound to a specific class" );
        this.context = JAXBContext.newInstance( classToBeBound );
    }

    public void serialize( T object, @WillNotClose OutputStream outputStream ) throws JAXBException {
        Preconditions.checkNotNull( object, "Cannot serialize null object" );
        Preconditions.checkNotNull( outputStream, "Cannot save results of serialization into null stream" );

        createMarshaller().marshal( object, outputStream );
    }

    public String serialize( T object ) throws Exception {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        serialize( object, buffer );

        return buffer.toString( StandardCharsets.UTF_8.name() );
    }

    @SuppressWarnings( "unchecked" )
    public T deserialize( @WillNotClose InputStream inputStream ) throws JAXBException {
        Preconditions.checkNotNull( inputStream, "Cannot deserialize objects from null input stream" );
        final Object deserialized = createUnmarshaller().unmarshal( inputStream );

        return (T) deserialized;
    }

    public T deserialize( String xml ) throws UnsupportedEncodingException, JAXBException {
        final ByteArrayInputStream stream = new ByteArrayInputStream( xml.getBytes( StandardCharsets.UTF_8.name() ) );

        return deserialize( stream );
    }

    private Marshaller createMarshaller() throws JAXBException {
        final Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
        marshaller.setProperty( Marshaller.JAXB_ENCODING, "UTF-8" );
        marshaller.setProperty( Marshaller.JAXB_FRAGMENT, Boolean.TRUE );

        return marshaller;
    }

    private Unmarshaller createUnmarshaller() throws JAXBException {
        /* Standard Jaxb provider does not require unmarshallers to support any properties. This method is mostly to
         * keep parity with the {@link #createMarshaller} method.
         */
        return context.createUnmarshaller();
    }
}
