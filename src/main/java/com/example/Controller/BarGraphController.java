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

	@GetMapping
	public String index(Map<String, Object> model) {

		@SuppressWarnings("unchecked")
		List<DividendDto> dividendDtoList = (List<DividendDto>) session.getAttribute("dividendDtoList"); // 取得

		if (dividendDtoList != null) {
			String[] deta = barGraphLogic.getCartData(dividendDtoList);
			model.put("contents", deta); // html側にデータ送るやつ
		}

		return "barGraph";
	}
}
