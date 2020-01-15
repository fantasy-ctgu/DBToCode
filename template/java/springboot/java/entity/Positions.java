package com.ctgu.springbootbase.entity;
/**  
* @Title: Positions.java  
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
public class Positions {
	private Integer id;
	private Integer cid;        //发布公司
	private String positionsName;  //岗位名称
	private String minWages;       //最低薪资
	private String maxWages;       //最高薪资
	private String province;        //省份
	private String city;    //城市
	private Integer seniority;  //资历要求
	private String education;       //学历要求
	private Integer requirementNum;    //招聘人数
	private String welfare; //福利
	private String positionsInfo;  //岗位信息
	private Integer mainTypeId;  //岗位分类
	private String keyword; //关键字
	private String address; //详细工作地址
	private Integer positionsStatusId;
	private String createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getMinWages() {
		return minWages;
	}
	public void setMinWages(String minWages) {
		this.minWages = minWages;
	}
	public String getMaxWages() {
		return maxWages;
	}
	public void setMaxWages(String maxWages) {
		this.maxWages = maxWages;
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
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Integer getRequirementNum() {
		return requirementNum;
	}
	public void setRequirementNum(Integer requirementNum) {
		this.requirementNum = requirementNum;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	public String getPositionsInfo() {
		return positionsInfo;
	}
	public void setPositionsInfo(String positionsInfo) {
		this.positionsInfo = positionsInfo;
	}
	public Integer getMainTypeId() {
		return mainTypeId;
	}
	public void setMainTypeId(Integer mainTypeId) {
		this.mainTypeId = mainTypeId;
	}
	public String getPositionsName() {
		return positionsName;
	}
	public void setPositionsName(String positionsName) {
		this.positionsName = positionsName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPositionsStatusId() {
		return positionsStatusId;
	}
	public void setPositionsStatusId(Integer positionsStatusId) {
		this.positionsStatusId = positionsStatusId;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}
