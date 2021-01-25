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
import com.example.Logic.SampleLogic;

/**
 * @author fukumura
 *
 */
@Controller
@RequestMapping("/sample")
public class SampleController {
	SampleLogic sampleLogic = new SampleLogic();

	@Autowired
	HttpSession session;

	@GetMapping
	public String index(Map<String, Object> model) {
		//内部ファイルを読み込んで
		//session scopeに保存して
		//menu画面に遷移
		List<DividendDto> contents = sampleLogic.readInternalFile();
		session.setAttribute("dividendDtoList", contents);
		return "menu";
	}
}
