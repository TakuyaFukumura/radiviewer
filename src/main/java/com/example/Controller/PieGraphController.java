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
import com.example.Logic.PieGraphLogic;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/pieGraph")
public class PieGraphController {
	PieGraphLogic pieGraphLogic = new PieGraphLogic();

	@Autowired
	DividendDtoList dividendDtoList;

	@GetMapping
	public String index(Map<String, Object> model) {

		List<DividendDto> contents = dividendDtoList.getDividendDtoList(); // 取得

		if (dividendDtoList != null) {
			String[] deta = pieGraphLogic.getCartData(contents);
			model.put("test", deta); // html側にデータ送るやつ
		}

		return "pieGraph";
	}
}
