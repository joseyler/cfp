package ar.gob.cfp.commons.jsonmap;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import ar.gob.cfp.commons.exceptions.JsonParseException;
import ar.gob.cfp.commons.utils.Utils;

public class JsonDoubleDesSerializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (Utils.esStringNuloEmpty(p.getText())) {
            return null;
        }
        try {
            String floatStr = p.getText().replace(",", ".");
            return Double.parseDouble(floatStr);
        } catch (NumberFormatException pe) {
            throw new JsonParseException("No se puede parsear el double con valor: " + p.getText() + "  --->  se espera formato: DOUBLE");
        }
    }

}
