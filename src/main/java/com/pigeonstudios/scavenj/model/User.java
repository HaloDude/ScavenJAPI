package com.pigeonstudios.scavenj.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement //used to convert this to xml
public class User {
	
	private long id;
	private String name;
	private Date created;
	
	public User(){
	}
	
	public User(long id, String name, Date created){
		this.id = id;
		this.name = name;
		this.created = created;
	}
	@XmlTransient // ignore this when building xml or json
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
	
}
