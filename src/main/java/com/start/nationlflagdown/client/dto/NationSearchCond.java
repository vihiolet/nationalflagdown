package com.start.nationlflagdown.client.dto;

public class NationSearchCond {
	
	private String search;
	private String continent;
	
	public NationSearchCond() {}

    public NationSearchCond(String search, String continent) {
        this.search = search;
        this.continent = continent;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

}
