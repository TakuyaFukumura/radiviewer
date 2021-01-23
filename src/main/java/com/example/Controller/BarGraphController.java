/**
 *
 */
package com.example.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Dto.DividendDto;
import com.example.Logic.BarGraphLogic;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/barGraph")
public class BarGraphController {
	BarGraphLogic barGraphLogic = new BarGraphLogic();

	@Autowired
	HttpSession session;

	/*
	 * 参考ページ
	 * https://qiita.com/nvtomo1029/items/316c5e8fe5d0cd92339c
	 * https://qiita.com/misskabu/items/81fa2c774f92c63125b5
	 */
	@PostMapping
	public String index( Map<String, Object> model) {

		@SuppressWarnings("unchecked")
		List<DividendDto> dividendDtoList = (List<DividendDto>) session.getAttribute("dividendDtoList");  // 取得

		//session.invalidate(); // クリア

		if(dividendDtoList != null) {
			String[] deta = barGraphLogic.getCartData(dividendDtoList);
			model.put("contents", deta); // html側にデータ送るやつ
		}

		return "barGraph";
	}
}
