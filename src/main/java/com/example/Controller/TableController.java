/**
 *
 */
package com.example.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Dto.DividendDto;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/table")
public class TableController {

	@Autowired
	HttpSession session;

	@GetMapping
	public String index(Map<String, Object> model) {
		@SuppressWarnings("unchecked")
		List<DividendDto> dividendDtoList = (List<DividendDto>) session.getAttribute("dividendDtoList");

		model.put("contents", dividendDtoList); // html側にデータ送るやつ

		return "table";
	}
}
