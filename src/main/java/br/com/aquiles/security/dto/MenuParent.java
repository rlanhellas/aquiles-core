package br.com.aquiles.security.dto;

import java.util.List;

/**
 * Responsável por armazenar os valores da tabela T400DOCU
 * @author Ronaldo Lanhellas
 * */
public class MenuParent {

    private String descricao;
    private String siglaDoc;
    private String siglaTran;
    private String awesomeClass;
    private Integer ordem;
    private List<MenuChild> childs;

    /*
     * When this property is TRUE the DOC act as a TRAN, then in Sidebar Menu only the DOC will appear but with a LINK to TRAN.
     * Instead of :
     *    - Home (DOC)
     *       - Home (TRAN)
     *
     * You will have:
     *    - Home (DOC)
     *
     * To active this property the DOC value should be equals TRAN, and DOC should have only one TRAN as child.
     * */
    private boolean docAsTran;

    public MenuParent(String descricao, String siglaDoc, String siglaTran, Integer ordem, String awesomeClass, List<MenuChild> childs) {
        this.descricao = descricao;
        this.siglaDoc = siglaDoc.toLowerCase().trim();
        this.siglaTran = siglaTran.toLowerCase().trim();
        this.ordem = ordem;
        this.awesomeClass = awesomeClass.toLowerCase().trim();
        this.childs = childs;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<MenuChild> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuChild> childs) {
        this.childs = childs;
    }

    public String getSiglaDoc() {
        return siglaDoc;
    }

    public void setSiglaDoc(String siglaDoc) {
        this.siglaDoc = siglaDoc.toLowerCase().trim();
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public String getSiglaTran() {
        return siglaTran;
    }

    public void setSiglaTran(String siglaTran) {
        this.siglaTran = siglaTran.toLowerCase().trim();
    }

    public String getAwesomeClass() {
        return awesomeClass;
    }

    public void setAwesomeClass(String awesomeClass) {
        this.awesomeClass = awesomeClass.toLowerCase().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuParent that = (MenuParent) o;

        return siglaDoc.equals(that.siglaDoc);

    }

    @Override
    public int hashCode() {
        return siglaDoc.hashCode();
    }

    public boolean isDocAsTran() {
        return docAsTran;
    }

    public void setDocAsTran(boolean docAsTran) {
        this.docAsTran = docAsTran;
    }
}
