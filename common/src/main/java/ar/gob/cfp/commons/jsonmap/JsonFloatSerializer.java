package ar.gob.cfp.commons.jsonmap;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonFloatSerializer extends JsonSerializer<Float> {

    @Override
    public void serialize(Float src, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (src != null) {
            gen.writeNumber(src.toString());
        }
    }

}
