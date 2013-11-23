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

	@RequestMapping(value = "/annotation")
	public @ResponseBody String responseBody() {
		return "The String ResponseBody我是谁啊";
	}

	@RequestMapping("/charset/accept")
	public @ResponseBody String responseAcceptHeaderCharset() {
		return "我是中国人 (\"Hello world!\" in Chinese)";
	}

	/**
	 * @return
	 */
	@RequestMapping(value="/charset/produce")
	public @ResponseBody String responseProducesConditionCharset() {
		return "我是中国人 (\"Hello world!\" in Chinese)";
	}

	@RequestMapping("/entity/status")
	public ResponseEntity<String> responseEntityStatusCode() {
		return new ResponseEntity<String>("我是中国人The String ResponseBody with custom status code (403 Forbidden)",
				HttpStatus.FORBIDDEN);
	}

	@RequestMapping("/entity/headers")
	public ResponseEntity<String> responseEntityCustomHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>("我是中国人The String ResponseBody with custom header Content-Type=text/plain",
				headers, HttpStatus.OK);
	}

}
