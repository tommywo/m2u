package com.tguzik.m2u.data.junit;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.xml.bind.annotation.XmlAttribute;

import com.google.common.base.Preconditions;
import com.tguzik.objects.BaseObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.apache.commons.lang3.StringUtils;

@XStreamAlias( "error" )
@Immutable
@ParametersAreNonnullByDefault
public final class Error extends BaseObject {
    @XStreamAsAttribute
    @XStreamAlias( "message" )
    @XmlAttribute( name = "message" )
    private final String message;

    @XStreamAsAttribute
    @XStreamAlias( "type" )
    @XmlAttribute( name = "type" )
    private final String type;

    private Error() {
        this( StringUtils.EMPTY, StringUtils.EMPTY );
    }

    public Error( String message, String type ) {
        this.message = Preconditions.checkNotNull( message );
        this.type = Preconditions.checkNotNull( type );
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
