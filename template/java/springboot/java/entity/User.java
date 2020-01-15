package com.ctgu.springbootbase.entity;
/**  
* @Title: User.java  
*
* @Package com.ctgu.bookstore.entity  
*
* @Description: TODO
*
* @author Fantasy  
*
* @date 2018年6月3日  
*
* @version V1.0  
*/
public class User {
	private Integer id;
	private String username;
	private String password;
	private String nickname;
	private String name;
	private String sex;
	private Integer age;
	private Integer school;
	private String studentid;
	private String summary;
	private String datetime;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSchool() {
		return school;
	}
	public void setSchool(Integer school) {
		this.school = school;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", name=" + name + ", sex=" + sex + ", age=" + age + ", school=" + school + ", studentid=" + studentid
				+ ", summary=" + summary + ", datetime=" + datetime + ", status=" + status + "]";
	}

	
}
