package ar.gob.cfp.commons.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Utils {

    public static boolean esStringNuloEmpty(String text) {
        return text==null || text.trim().isEmpty();
    }
    
    /**
     * Retorna null si falla la codificacion
     * @param text
     * @return
     */
    public static String encriptMD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception ex) {
           // LOGGER.error(ex.getMessage(), ex);
        }
        return null;
    }


}
