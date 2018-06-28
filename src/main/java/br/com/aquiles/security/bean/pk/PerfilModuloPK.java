package br.com.aquiles.security.bean.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Embeddable
public class PerfilModuloPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 197993698777939207L;

	@Column(name="sg_pfl")
    private String sgPfl;

    @Column(name="sg_mdl")
    private String sgMdl;

    public String getSgPfl() {
        return sgPfl;
    }

    public void setSgPfl(String sgPfl) {
        this.sgPfl = sgPfl;
    }

    public String getSgMdl() {
        return sgMdl;
    }

    public void setSgMdl(String sgMdl) {
        this.sgMdl = sgMdl;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sgMdl == null) ? 0 : sgMdl.hashCode());
		result = prime * result + ((sgPfl == null) ? 0 : sgPfl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilModuloPK other = (PerfilModuloPK) obj;
		if (sgMdl == null) {
			if (other.sgMdl != null)
				return false;
		} else if (!sgMdl.equals(other.sgMdl))
			return false;
		if (sgPfl == null) {
			if (other.sgPfl != null)
				return false;
		} else if (!sgPfl.equals(other.sgPfl))
			return false;
		return true;
	}
}
