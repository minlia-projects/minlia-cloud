//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.minlia.cloud.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@JacksonStdImpl
public final class MinliaStringDeserializer extends StdScalarDeserializer<String> {
    private static final long serialVersionUID = 1L;
    protected static final int FEATURES_ACCEPT_ARRAYS;
    public static final MinliaStringDeserializer instance;

    public MinliaStringDeserializer() {
        super(String.class);
    }

    public boolean isCachable() {
        return true;
    }

    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING)) {
            return StringUtils.isNotBlank(p.getText()) ? p.getText() : null;
        } else {
            JsonToken t = p.getCurrentToken();
            if (t == JsonToken.START_ARRAY) {
                return this._deserializeFromArray(p, ctxt);
            } else if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object ob = p.getEmbeddedObject();
                if (ob == null) {
                    return null;
                } else {
                    return ob instanceof byte[] ? ctxt.getBase64Variant().encode((byte[]) ((byte[]) ob), false) : ob.toString();
                }
            } else {
                String text = p.getValueAsString();
                return text != null ? text : (String) ctxt.handleUnexpectedToken(this._valueClass, p);
            }
        }
    }

    public String deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return this.deserialize(p, ctxt);
    }

    protected String _deserializeFromArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t;
        if (ctxt.hasSomeOfFeatures(FEATURES_ACCEPT_ARRAYS)) {
            t = p.nextToken();
            if (t == JsonToken.END_ARRAY && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                return (String) this.getNullValue(ctxt);
            }

            if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                String parsed = this._parseString(p, ctxt);
                if (p.nextToken() != JsonToken.END_ARRAY) {
                    this.handleMissingEndArrayForSingle(p, ctxt);
                }

                return parsed;
            }
        } else {
            t = p.getCurrentToken();
        }

        return (String) ctxt.handleUnexpectedToken(this._valueClass, t, p, (String) null, new Object[0]);
    }

    static {
        FEATURES_ACCEPT_ARRAYS = DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS.getMask() | DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.getMask();
        instance = new MinliaStringDeserializer();
    }
}
