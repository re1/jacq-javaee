package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_tax_rank database table.
 * 
 */
@Entity
@Table(name="tbl_tax_rank")
@NamedQuery(name="TblTaxRank.findAll", query="SELECT t FROM TblTaxRank t")
public class TblTaxRank implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tax_rankID;
	private String botRankSuffix;
	private String rank;
	private String rankAbbr;
	private int rankHierarchy;
	private String rankLatin;
	private String rankPlural;
	private String zooRankSuffix;

	public TblTaxRank() {
	}


	@Id
	public int getTax_rankID() {
		return this.tax_rankID;
	}

	public void setTax_rankID(int tax_rankID) {
		this.tax_rankID = tax_rankID;
	}


	@Column(name="bot_rank_suffix")
	public String getBotRankSuffix() {
		return this.botRankSuffix;
	}

	public void setBotRankSuffix(String botRankSuffix) {
		this.botRankSuffix = botRankSuffix;
	}


	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}


	@Column(name="rank_abbr")
	public String getRankAbbr() {
		return this.rankAbbr;
	}

	public void setRankAbbr(String rankAbbr) {
		this.rankAbbr = rankAbbr;
	}


	@Column(name="rank_hierarchy")
	public int getRankHierarchy() {
		return this.rankHierarchy;
	}

	public void setRankHierarchy(int rankHierarchy) {
		this.rankHierarchy = rankHierarchy;
	}


	@Column(name="rank_latin")
	public String getRankLatin() {
		return this.rankLatin;
	}

	public void setRankLatin(String rankLatin) {
		this.rankLatin = rankLatin;
	}


	@Column(name="rank_plural")
	public String getRankPlural() {
		return this.rankPlural;
	}

	public void setRankPlural(String rankPlural) {
		this.rankPlural = rankPlural;
	}


	@Column(name="zoo_rank_suffix")
	public String getZooRankSuffix() {
		return this.zooRankSuffix;
	}

	public void setZooRankSuffix(String zooRankSuffix) {
		this.zooRankSuffix = zooRankSuffix;
	}

}