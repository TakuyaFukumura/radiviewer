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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	@PostMapping
	public String index(@RequestParam("csv_file")MultipartFile csv_file, Map<String, Object> model) {
		if(csv_file != null) {
			List<DividendDto> contents = tableLogic.fileContents(csv_file);
			model.put("contents", contents); // html側にデータ送るやつ
			session.setAttribute("dividendDtoList", contents);
		}
		return "table";
	}
}
