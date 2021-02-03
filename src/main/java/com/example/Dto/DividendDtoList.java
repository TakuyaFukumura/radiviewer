/**
 *
 */
package com.example.Dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


/**
 * @author fukumura
 *
 */
@Component
@Scope(value= "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DividendDtoList implements Serializable{

	private List<DividendDto> dividendDtoList = new ArrayList<DividendDto>();

	/**
	 * @param dividendDtoList
	 */
	public DividendDtoList(List<DividendDto> dividendDtoList) {
		super();
		this.dividendDtoList = dividendDtoList;
	}

	/**
	 * @return dividendDtoList
	 */
	public List<DividendDto> getDividendDtoList() {
		return dividendDtoList;
	}

	/**
	 * @param dividendDtoList セットする dividendDtoList
	 */
	public void setDividendDtoList(List<DividendDto> dividendDtoList) {
		this.dividendDtoList = dividendDtoList;
	}
}

