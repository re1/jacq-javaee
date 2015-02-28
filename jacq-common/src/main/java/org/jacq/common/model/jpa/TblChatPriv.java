package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_chat_priv database table.
 * 
 */
@Entity
@Table(name="tbl_chat_priv")
@NamedQuery(name="TblChatPriv.findAll", query="SELECT t FROM TblChatPriv t")
public class TblChatPriv implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String chat;
	private byte seen;
	private String theme;
	private int tid;
	private Timestamp timestamp;
	private int uid;

	public TblChatPriv() {
	}


	@Id
	@Column(name="ID")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Lob
	public String getChat() {
		return this.chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}


	public byte getSeen() {
		return this.seen;
	}

	public void setSeen(byte seen) {
		this.seen = seen;
	}


	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}


	public int getTid() {
		return this.tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}


	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}