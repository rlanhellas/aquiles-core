package br.com.aquiles.core.converter;

import javax.persistence.AttributeConverter;

/**
 * @author Ronaldo Lanhellas
 */
public class GenericBooleanConverter implements AttributeConverter<Boolean, String> {


    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) {
            return null;
        }
        if (attribute) {
            return "S";
        }

        return "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        if (dbData.equalsIgnoreCase("S")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
