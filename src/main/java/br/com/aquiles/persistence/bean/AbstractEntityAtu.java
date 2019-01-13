package br.com.aquiles.persistence.bean;

import br.com.aquiles.persistence.listener.EntityListenerAtu;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Classe básica para mapeamento de entidades, já contendo cdUsuAtu e dhAtu
 * 
 * @author Ronaldo Lanhellas
 * */
@MappedSuperclass
@EntityListeners(EntityListenerAtu.class)
public abstract class AbstractEntityAtu extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract Object getId();
	
	@Column(name="CD_USU_ATU")
	private String cdUsuAtu;
	
	@Column(name="DH_ATU")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dhAtu;

	public String getCdUsuAtu() {
		return cdUsuAtu;
	}

	public void setCdUsuAtu(String cdUsuAtu) {
		this.cdUsuAtu = cdUsuAtu;
	}

	public Date getDhAtu() {
		return dhAtu;
	}

	public void setDhAtu(Date dhAtu) {
		this.dhAtu = dhAtu;
	}
	

}
