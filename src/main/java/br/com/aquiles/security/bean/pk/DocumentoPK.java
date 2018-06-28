package br.com.aquiles.security.bean.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DocumentoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6219839047346973685L;

	@Column(name = "sg_mdl")
    private String sgMdl;

    @Column(name = "sg_doc")
    private String sgDoc;

    public String getSgMdl() {
        return sgMdl;
    }

    public void setSgMdl(String sgMdl) {
        this.sgMdl = sgMdl;
    }

    public String getSgDoc() {
        return sgDoc;
    }

    public void setSgDoc(String sgDoc) {
        this.sgDoc = sgDoc;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sgDoc == null) ? 0 : sgDoc.hashCode());
		result = prime * result + ((sgMdl == null) ? 0 : sgMdl.hashCode());
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
		DocumentoPK other = (DocumentoPK) obj;
		if (sgDoc == null) {
			if (other.sgDoc != null)
				return false;
		} else if (!sgDoc.equals(other.sgDoc))
			return false;
		if (sgMdl == null) {
			if (other.sgMdl != null)
				return false;
		} else if (!sgMdl.equals(other.sgMdl))
			return false;
		return true;
	}
}
