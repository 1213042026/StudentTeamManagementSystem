package me.cmnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "community_check_in", schema = "community")
public class CheckIn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private String user_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "record_time")
	private String record_time;
	
	@Column(name = "recorder")
	private String recorder;
	
	@Column(name = "community_id")
	private String community_id;
	
	public CheckIn() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "CheckIn [id=" + id + ", user_id=" + user_id + ", time=" + time + ", record_time=" + record_time
				+ ", recorder=" + recorder + "]";
	}

	public CheckIn(String user_id, String time, String record_time, String recorder, String community_id, String username) {
		this.user_id = user_id;
		this.time = time;
		this.record_time = record_time;
		this.recorder = recorder;
		this.community_id = community_id;
		this.username = username;
	}
	
	public String getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRecord_time() {
		return record_time;
	}

	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
}
