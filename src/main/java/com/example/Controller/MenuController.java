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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Dto.DividendDto;
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
	HttpSession session;

	@PostMapping
	public String index(@RequestParam("csv_file") MultipartFile csv_file, Map<String, Object> model) {
		List<DividendDto> contents = menuLogic.fileContents(csv_file);
		session.setAttribute("dividendDtoList", contents);
		return "menu";
	}

	@GetMapping
	public String indexGet(Map<String, Object> model) {
		//データがあるかどうかチェック
		return "menu";
	}
}
