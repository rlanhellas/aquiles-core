package br.com.aquiles.security.dto;

/**
 * Responsável por armazenar os valores do T400TRAN
 * @author  Ronaldo Lanhellas
 * */
public class MenuChild {

    private String descricao;
    private String siglaTran;
    private Integer ordem;

    public MenuChild(String descricao, String siglaTran, Integer ordem) {
        this.descricao = descricao;
        this.siglaTran = siglaTran.toLowerCase().trim();
        this.ordem = ordem;
    }

    public MenuChild(String siglaTran) {
        this.siglaTran = siglaTran;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSiglaTran() {
        return siglaTran;
    }

    public void setSiglaTran(String siglaTran) {
        this.siglaTran = siglaTran.toLowerCase().trim();
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuChild menuChild = (MenuChild) o;

        return siglaTran.equalsIgnoreCase(menuChild.siglaTran);
    }

    @Override
    public int hashCode() {
        return siglaTran.hashCode();
    }
}
