/**
 *
 */
package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Dto.DividendDto;
import com.example.Dto.DividendDtoList;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/table")
public class TableController {

	@Autowired
	DividendDtoList dividendDtoList;

	@GetMapping
	public String index(Map<String, Object> model) {
		List<DividendDto> contents = dividendDtoList.getDividendDtoList();

		model.put("contents", contents); // html側にデータ送るやつ

		return "table";
	}
}
