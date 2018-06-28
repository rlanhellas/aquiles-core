package br.com.aquiles.security.bean.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Ronaldo Lanhellas on 11/11/2016.
 */
@Embeddable
public class AutorizacaoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2101033923143993704L;

	@Column(name="sg_pfl")
    private String sgPfl;

    @Column(name="sg_mdl")
    private String sgMdl;

    @Column(name="sg_doc")
    private String sgDoc;

    @Column(name="sg_trn")
    private String sgTrn;

    @Column(name="sg_fuc")
    private String sgFuc;

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

    public String getSgDoc() {
        return sgDoc;
    }

    public void setSgDoc(String sgDoc) {
        this.sgDoc = sgDoc;
    }

    public String getSgTrn() {
        return sgTrn;
    }

    public void setSgTrn(String sgTrn) {
        this.sgTrn = sgTrn;
    }

    public String getSgFuc() {
        return sgFuc;
    }

    public void setSgFuc(String sgFuc) {
        this.sgFuc = sgFuc;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sgDoc == null) ? 0 : sgDoc.hashCode());
		result = prime * result + ((sgFuc == null) ? 0 : sgFuc.hashCode());
		result = prime * result + ((sgMdl == null) ? 0 : sgMdl.hashCode());
		result = prime * result + ((sgPfl == null) ? 0 : sgPfl.hashCode());
		result = prime * result + ((sgTrn == null) ? 0 : sgTrn.hashCode());
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
		AutorizacaoPK other = (AutorizacaoPK) obj;
		if (sgDoc == null) {
			if (other.sgDoc != null)
				return false;
		} else if (!sgDoc.equals(other.sgDoc))
			return false;
		if (sgFuc == null) {
			if (other.sgFuc != null)
				return false;
		} else if (!sgFuc.equals(other.sgFuc))
			return false;
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
		if (sgTrn == null) {
			if (other.sgTrn != null)
				return false;
		} else if (!sgTrn.equals(other.sgTrn))
			return false;
		return true;
	}
}
