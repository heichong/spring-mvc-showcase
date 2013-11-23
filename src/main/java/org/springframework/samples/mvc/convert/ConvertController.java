package org.springframework.samples.mvc.convert;

import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/convert")
public class ConvertController {

	@RequestMapping("primitive")
	public @ResponseBody String primitive(@RequestParam Integer value) {
		return "Converted primitive " + value;
	}

	// requires Joda-Time on the classpath
	@RequestMapping("date/{value}")
	public @ResponseBody String date(@PathVariable @DateTimeFormat(iso=ISO.DATE) Date value) {
		return "Converted date " + value;
	}

	@RequestMapping("collection")
	public @ResponseBody String collection(@RequestParam Collection<Integer> values) {
		return "Converted collection " + values;
	}

	@RequestMapping("formattedCollection")
	public @ResponseBody String formattedCollection(@RequestParam @DateTimeFormat(iso=ISO.DATE) Collection<Date> values) {
		return "Converted formatted collection " + values;
	}
	//	http://127.0.0.1:8010/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip
	@RequestMapping("bean")
	public @ResponseBody String bean(JavaBean bean) {
		return "Converted " + bean;
	}

	@RequestMapping("value")
	public @ResponseBody String valueObject(@RequestParam SocialSecurityNumber value) {
		return "Converted value object " + value;
	}

	/**
	 * 在MaskFormatter格式中，有多种替代符，其中#代表数字，*可以是任何字符， 详如下：

		#	任何有效数字，使用 Character.isDigit。
		'	转义字符，用于避开任何具有特殊格式的字符。
		U	任何字符 (Character.isLetter)。将所有小写字母映射为大写。
		L	任何字符 (Character.isLetter)。将所有大写字母映射为小写。
		A	任何字符或数字（Character.isLetter 或 Character.isDigit）
		?	任何字符 (Character.isLetter)。
		*	所有字符。
		H	任何十六进制字符（0-9、a-f 或 A-F）。
	 * @param value
	 * @return
	 */
	@RequestMapping("custom")
	public @ResponseBody String customConverter(@RequestParam @MaskFormat("###-##-####") String value) {
		return "Converted '" + value + "' with a custom converter";
	}

}
