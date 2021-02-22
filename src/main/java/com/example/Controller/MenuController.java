/**
 *
 */
package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Dto.DividendDto;
import com.example.Dto.DividendDtoList;
import com.example.Logic.MenuLogic;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	MenuLogic menuLogic = new MenuLogic();

	@Autowired
	DividendDtoList dividendDtoList;

	@PostMapping
	public String index(@RequestParam("csv_file") MultipartFile csvFile, Map<String, Object> model) {
		List<DividendDto> contents = menuLogic.fileContents(csvFile);
		dividendDtoList.setDividendDtoList(contents); // session beanに入れる
		if(CollectionUtils.isEmpty(contents)) { //データがあるかどうかチェック
			return "redirect:/"; // TOP画面にリダイレクト
		}
		return "menu";
	}

	@GetMapping
	public String index(Map<String, Object> model) {
		List<DividendDto> contents = dividendDtoList.getDividendDtoList(); // sessionから取り出す
		if(CollectionUtils.isEmpty(contents)) {
			return "redirect:/";
		} //本当はフィルターで実装したい
		return "menu";
	}
}
