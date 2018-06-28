package br.com.aquiles.core.dto;

import java.io.Serializable;

/**
 * Created by rlanhellas on 10/04/2017.
 */
public class ParamInOut implements Serializable {

    public ParamInOut(Object value, String paramName, Class type, boolean input) {
        this.value = value;
        this.paramName = paramName;
        this.type = type;
        this.input = input;
    }

    private Object value;
    private String paramName;
    private Class type;
    private boolean input;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public boolean isInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
