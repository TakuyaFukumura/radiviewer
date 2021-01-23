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
import com.example.Logic.TableLogic;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/table")
public class TableController {
	TableLogic tableLogic = new TableLogic();

	@Autowired
	HttpSession session;

	/*
	 * 参考ページ
	 * https://qiita.com/nvtomo1029/items/316c5e8fe5d0cd92339c
	 * https://qiita.com/misskabu/items/81fa2c774f92c63125b5
	 */
	@GetMapping
	public String index( Map<String, Object> model ) {
		@SuppressWarnings("unchecked")
		List<DividendDto> dividendDtoList = (List<DividendDto>) session.getAttribute("dividendDtoList");

		model.put("contents", dividendDtoList); // html側にデータ送るやつ

		return "table";
	}
}
