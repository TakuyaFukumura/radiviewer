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
	DividendDtoList dividendDtoList;

	@GetMapping
	public String index(Map<String, Object> model) {

		// 内部ファイルを読み込んで
		List<DividendDto> contents = sampleLogic.readInternalFile();

		// session scopeに保存して
		dividendDtoList.setDividendDtoList(contents);

		// menu画面に遷移
		return "menu";
	}
}
