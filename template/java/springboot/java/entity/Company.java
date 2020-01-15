package com.ctgu.springbootbase.entity;
/**  
* @Title: Company.java  
*
* @Package com.ctgu.springbootbase.entity  
*
* @Description: TODO
*
* @author Fantasy  
*
* @date 2019年3月3日  
*
* @version V1.0  
*/
public class Company {
	private Integer id;
	private String username;        //用户名
	private String password;
	private String companyName;     //公司名称
	private String avatar;  //头像
	private String province;        //省份
	private String city;    //城市
	private String address; //详细地址
	private String contacts;        //联系人
	private String phone;   //联系电话
	private String email;
	private String introduce;       //公司介绍
	private String lastLogin;       //最后登陆时间
	private String lastIp;  //最后登陆ip
	private Integer perfect;  //是否完善信息
	private String updatetime; 
	private String createtime;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Integer getPerfect() {
		return perfect;
	}
	public void setPerfect(Integer perfect) {
		this.perfect = perfect;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", username=" + username + ", password=" + password + ", companyName="
				+ companyName + ", avatar=" + avatar + ", province=" + province + ", city=" + city + ", address="
				+ address + ", contacts=" + contacts + ", phone=" + phone + ", email=" + email + ", introduce="
				+ introduce + ", lastLogin=" + lastLogin + ", lastIp=" + lastIp + ", perfect=" + perfect
				+ ", updatetime=" + updatetime + ", createtime=" + createtime + "]";
	}
}
