/**
 *
 */
package com.example.Logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fukumura
 *
 */
class MenuLogicTest {

	@Autowired
	MenuLogic menuLogic;

	/**
	 * {@link com.example.Logic.MenuLogic#fileContents(org.springframework.web.multipart.MultipartFile)} のためのテスト・メソッド。
	 */
	@Test
	void testFileContents() {
		assertTrue(true); //csvの読み込みテストをする必要がある
	}

}
