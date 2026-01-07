package com.start.nationlflagdown.admin.dto;

public class AdmSearchCond {
	
	private String search;
	
	public AdmSearchCond() {}
	
	public AdmSearchCond(String search, String continent) {
		 this.search = search;
	}
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}

}
