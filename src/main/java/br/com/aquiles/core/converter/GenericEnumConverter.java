package br.com.aquiles.core.converter;

import br.com.aquiles.core.enums.IGenericEnum;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

/**
 * @author Ronaldo Lanhellas
 */
public class GenericEnumConverter<E extends Enum<E> & IGenericEnum> implements AttributeConverter<E, String> {

    private Class<E> enumClass;

    public GenericEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E e) {
        if (e == null) {
            return null;
        }
        return e.getValue();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        // which enum is it?
        for (E en : EnumSet.allOf(enumClass)) {
            if (en.getValue().equalsIgnoreCase(dbData.trim())) {
                return en;
            }
        }

        throw new IllegalArgumentException("Unknown database value:" + dbData);
    }
}
