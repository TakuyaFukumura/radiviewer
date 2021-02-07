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
import com.example.Logic.LineGraphLogic;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/lineGraph")
public class LineGraphController {
	LineGraphLogic lineGraphLogic = new LineGraphLogic();

	@Autowired
	DividendDtoList dividendDtoList;

	@GetMapping
	public String index(Map<String, Object> model) {

		List<DividendDto> contents = dividendDtoList.getDividendDtoList(); // 取得

		String[] year = {"2019","2020","2021"}; // 表示する年を指定

		if (contents != null) {
			String deta = lineGraphLogic.getCartData( contents, year );
			model.put("deta", deta); // html側にデータ送るやつ
		}

		return "lineGraph";
	}
}
