package org.springframework.samples.mvc.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/response", method=RequestMethod.GET)
public class ResponseController {

	@RequestMapping("/annotation")
	public @ResponseBody String responseBody() {
		return "The String ResponseBody";
	}

	@RequestMapping("/charset/accept")
	public @ResponseBody String responseAcceptHeaderCharset() {
		return "我是中国人 (\"Hello world!\" in Chinese)";
	}

	/**
	 * \u6211\u662f\u4e2d\u56fd\u4eba
	 * 是 我是中国人 的UNICODE字符
	 * @return
	 */
	@RequestMapping(value="/charset/produce", produces="text/plain;charset=UTF-8")
	public @ResponseBody String responseProducesConditionCharset() {
		return "\u6211\u662f\u4e2d\u56fd\u4eba (\"Hello world!\" in Chinese)";
	}

	@RequestMapping("/entity/status")
	public ResponseEntity<String> responseEntityStatusCode() {
		return new ResponseEntity<String>("The String ResponseBody with custom status code (403 Forbidden)",
				HttpStatus.FORBIDDEN);
	}

	@RequestMapping("/entity/headers")
	public ResponseEntity<String> responseEntityCustomHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>("The String ResponseBody with custom header Content-Type=text/plain",
				headers, HttpStatus.OK);
	}

}
