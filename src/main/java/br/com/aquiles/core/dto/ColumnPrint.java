package br.com.aquiles.core.dto;

import java.io.Serializable;

/**
 * Classe responsável por representar o label da coluna que será mostrado em um arquivo de impressão (PDF,
 * XLS, DOC ...) e a propriedade que deverá ser lida de um Bean válido.
 * @author Ronaldo Lanhellas
 */
public class ColumnPrint implements Serializable {

    private String columnLabel;
    private String propertyName;
    private Integer columnSize;

    public ColumnPrint(String columnLabel, String propertyName) {
        this.columnLabel = columnLabel;
        this.propertyName = propertyName;
    }

    public ColumnPrint(String columnLabel, String propertyName, Integer columnSize) {
        this.columnLabel = columnLabel;
        this.propertyName = propertyName;
        this.columnSize = columnSize;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        br.com.aquiles.core.dto.ColumnPrint that = (br.com.aquiles.core.dto.ColumnPrint) o;

        if (columnLabel != null ? !columnLabel.equals(that.columnLabel) : that.columnLabel != null) return false;
        return propertyName != null ? propertyName.equals(that.propertyName) : that.propertyName == null;

    }

    @Override
    public int hashCode() {
        int result = columnLabel != null ? columnLabel.hashCode() : 0;
        result = 31 * result + (propertyName != null ? propertyName.hashCode() : 0);
        return result;
    }
}
