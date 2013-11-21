package org.springframework.samples.mvc.data;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class RequestDataController {

	/**
	 * @RequestParam String foo = @RequestParam(required=true) String foo
	 * 要求request中必须包含参数foo，否则404
	 * 
	 * @RequestParam(required=false) String foo时，request可不包含此参数
	 * @param foo
	 * @return
	 */
	@RequestMapping(value="param", method=RequestMethod.GET)
	public @ResponseBody String withParam(@RequestParam(required=false) String foo) {
		return "Obtained 'foo' query parameter value '" + foo + "'";
	}

	/**
	 * 从request中获取参数来填充javabean对象，且也可以填充@RequestParam 标识的参数
	 * @param bean
	 * @param param2
	 * @return
	 */
	@RequestMapping(value="group", method=RequestMethod.GET)
	public @ResponseBody String withParamGroup(JavaBean bean,@RequestParam String param2) {
		return "Obtained parameter group " + bean + " & other param2 :"+param2;
	}

	/**
	 * 我们经常遇到路径中有点"."，而点是特殊字符，比如.html, .do等等，所以Spring MVC默认是把最后一个.以后的信息当作文件后缀
	 * 
	 * 要想改变spring这种默认的行为，接收所有的数据,改成这样	 value="path/{var:.*}"
	 * @param var
	 * @return
	 */
	@RequestMapping(value="path/{var}", method=RequestMethod.GET)
	public @ResponseBody String withPathVariable(@PathVariable String var) {
		return "Obtained 'var' path variable value '" + var + "'";
	}

	@RequestMapping(value="{path}/simple", method=RequestMethod.GET)
	public @ResponseBody String withMatrixVariable(@PathVariable String path, @MatrixVariable String foo) {
		return "Obtained matrix variable 'foo=" + foo + "' from path segment '" + path + "'";
	}

	@RequestMapping(value="{path1}/{path2}", method=RequestMethod.GET)
	public @ResponseBody String withMatrixVariablesMultiple (
			@PathVariable String path1, @MatrixVariable(value="foo", pathVar="path1") String foo1,
			@PathVariable String path2, @MatrixVariable(value="foo", pathVar="path2") String foo2) {

		return "Obtained matrix variable foo=" + foo1 + " from path segment '" + path1
				+ "' and variable 'foo=" + foo2 + " from path segment '" + path2 + "'";
	}

	@RequestMapping(value="header", method=RequestMethod.GET)
	public @ResponseBody String withHeader(@RequestHeader String Accept) {
		return "Obtained 'Accept' header '" + Accept + "'";
	}

	@RequestMapping(value="cookie", method=RequestMethod.GET)
	public @ResponseBody String withCookie(@CookieValue String openid_provider) {
		return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
	}

	/**
	 * 返回中文乱码问题的解决方式：
	 * 	--ajax请求时加上contentType: "text/plain;charset=UTF-8"
	 * 	--且@RequestMapping 中加上produces="text/plain;charset=UTF-8"
	 * @param body
	 * @return
	 */
	@RequestMapping(value="body", method=RequestMethod.POST)
	public @ResponseBody String withBody(@RequestBody String body) {
		return "Posted request body '" + body + "'";
	}

	@RequestMapping(value="entity", method=RequestMethod.POST)
	public @ResponseBody String withEntity(HttpEntity<String> entity) {
		return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
	}

}
