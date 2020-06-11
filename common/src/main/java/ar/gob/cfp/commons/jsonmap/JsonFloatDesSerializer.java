package ar.gob.cfp.commons.jsonmap;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import ar.gob.cfp.commons.exceptions.JsonParseException;
import ar.gob.cfp.commons.utils.Utils;

public class JsonFloatDesSerializer extends JsonDeserializer<Float> {


    @Override
    public Float deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (Utils.esStringNuloEmpty(p.getText())) {
            return null;
        }
        try {
            String floatStr = p.getText().replace(",", ".");
            return Float.parseFloat(floatStr);
        } catch (NumberFormatException pe) {
            throw new JsonParseException("No se puede parsear el float con valor: " + p.getText() + "  --->  se espera formato: FLOAT");
        }
    }

}
