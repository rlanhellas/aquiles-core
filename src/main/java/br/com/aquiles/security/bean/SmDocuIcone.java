package br.com.aquiles.security.bean;

import br.com.aquiles.persistence.bean.AbstractEntity;
import br.com.aquiles.security.bean.pk.SmDocuIconePK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sm_docuicone")
public class SmDocuIcone extends AbstractEntity {

    @EmbeddedId
    private SmDocuIconePK id;

    @Column(name="awesome_class")
    private String awesomeClass;


    @Override
    public Object getId() {
        return this.id;
    }

    public void setId(SmDocuIconePK id) {
        this.id = id;
    }

    public String getAwesomeClass() {
        return awesomeClass;
    }

    public void setAwesomeClass(String awesomeClass) {
        this.awesomeClass = awesomeClass;
    }
}
