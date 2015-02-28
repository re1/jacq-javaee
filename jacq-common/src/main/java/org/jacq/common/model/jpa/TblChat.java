package org.jacq.common.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tbl_chat database table.
 * 
 */
@Entity
@Table(name="tbl_chat")
@NamedQuery(name="TblChat.findAll", query="SELECT t FROM TblChat t")
public class TblChat implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String chat;
	private Timestamp timestamp;
	private int uid;

	public TblChat() {
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