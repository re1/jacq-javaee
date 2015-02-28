package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_specimens_voucher database table.
 * 
 */
@Entity
@Table(name="tbl_specimens_voucher")
@NamedQuery(name="TblSpecimensVoucher.findAll", query="SELECT t FROM TblSpecimensVoucher t")
public class TblSpecimensVoucher implements Serializable {
	private static final long serialVersionUID = 1L;
	private int voucherID;
	private String voucher;

	public TblSpecimensVoucher() {
	}


	@Id
	public int getVoucherID() {
		return this.voucherID;
	}

	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}


	public String getVoucher() {
		return this.voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

}