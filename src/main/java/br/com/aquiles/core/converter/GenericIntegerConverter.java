package br.com.aquiles.core.converter;

import br.com.aquiles.core.enums.IGenericIntegerEnum;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;

/**
 * @author Ronaldo Lanhellas
 */
public class GenericIntegerConverter<E extends Enum<E> & IGenericIntegerEnum> implements AttributeConverter<E, Integer> {

    private Class<E> enumClass;

    public GenericIntegerConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(E e) {
        if (e == null) {
            return null;
        }
        return e.getValue();
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        // which enum is it?
        for (E en : EnumSet.allOf(enumClass)) {
            if (en.getValue().equals(dbData)) {
                return en;
            }
        }

        throw new IllegalArgumentException("Unknown database value:" + dbData);
    }
}
