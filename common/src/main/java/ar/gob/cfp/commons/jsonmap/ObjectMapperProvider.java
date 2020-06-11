package ar.gob.cfp.commons.jsonmap;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperProvider {


    public static Jackson2ObjectMapperBuilder getBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        //solo incluye atributos no nulos
        builder.serializationInclusion(Include.NON_NULL);
        
        //si tiene atributos extras los ignora
        builder.failOnUnknownProperties(false);
        
        //parecido anterior pero con objetos
        builder.failOnEmptyBeans(false);
        
        //le pongo mis serializadores
        builder.serializerByType(Float.class, new JsonFloatSerializer());
        builder.serializerByType(Double.class, new JsonDoubleSerializer());
        builder.deserializerByType(Float.class, new JsonFloatDesSerializer());
        builder.deserializerByType(Double.class, new JsonDoubleDesSerializer());
        
        return builder;
    }
    
    public static String pasarAJSON(Object objeto) {
        try {
            ObjectMapper mapper = getBuilder().build();
            return mapper.writeValueAsString(objeto);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    
    ///hacemos un metodo parametrizado para pasar un json a objeto
    public static <T> T pasarAObjeto(String json, Class<T> tipo) {
        try {
            ObjectMapper mapper = getBuilder().build();
            return mapper.readValue(json, tipo);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

}
