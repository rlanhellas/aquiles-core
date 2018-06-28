package br.com.aquiles.core.util;

import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Set;

public abstract class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {
    public static void setNullAllFields(Object o) throws IllegalAccessException {
        Set<Field> fields = ReflectionUtils.getAllFields(o.getClass());
        for(Field f : fields){
            if (!f.getType().isPrimitive()) {
                f.setAccessible(true);
                f.set(o, null);
            }
        }
    }
}
