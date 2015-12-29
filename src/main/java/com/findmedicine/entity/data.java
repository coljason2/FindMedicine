package com.findmedicine.entity;

public class data {
	String name;
	String chname;
	String hid;
	String newprice;
	String inprice;
	String compy;

	@Override
	public String toString() {
		return "data [name=" + name + ", chname=" + chname + ", hid=" + hid
				+ ", newprice=" + newprice + ", inprice=" + inprice
				+ ", compy=" + compy + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChname() {
		return chname;
	}

	public void setChname(String chname) {
		this.chname = chname;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getNewprice() {
		return newprice;
	}

	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public String getCompy() {
		return compy;
	}

	public void setCompy(String compy) {
		this.compy = compy;
	}
}
