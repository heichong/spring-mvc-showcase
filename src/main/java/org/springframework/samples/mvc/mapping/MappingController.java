package org.springframework.samples.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MappingController {

	@RequestMapping("/mapping/path")
	public @ResponseBody String byPath() {
		return "Mapped by path!";
	}

	/**
	 * 支持这种格式：/mapping/path/123
	 * 不支持：/mapping/path/123/121(这个被 value="/mapping/path/**" 支持)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/mapping/path/*", method=RequestMethod.GET)
	public @ResponseBody String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	@RequestMapping(value="/mapping/method", method=RequestMethod.GET)
	public @ResponseBody String byMethod() {
		return "Mapped by path + method";
	}

	/**
	 * request中必须包含foo参数，参数名不区分大小写
	 * @return
	 */
	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="foo")
	public @ResponseBody String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}

	/**
	 * request中必须不包含foo参数，参数名不区分大小写
	 * @return
	 */
	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="!foo")
	public @ResponseBody String byParameterNegation() {
		return "Mapped by path + method + not presence of query parameter!";
	}

	/**
	 * request header中必须包含参数键值对：FooHeader=foo，参数名不区分大小写
	 * @return
	 */
	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="FooHeader=foo")
	public @ResponseBody String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="!FooHeader")
	public @ResponseBody String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}

	/**
	 * request中只能包含javaBean中属性的参数，不能包含多余的参数，否则会报404错。
	 * 去掉@RequestBody注释则不会报错
	 * consumes=MediaType.APPLICATION_JSON_VALUE 代表客户端请求时：contentType: "application/json"
	 * @param javaBean
	 * @return
	 */
	@RequestMapping(value="/mapping/consumes", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String byConsumes(@RequestBody JavaBean javaBean) {
		return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
	}

	/**
	 * 以下两个方法都一样，一个返回json字符串,一个返回xml字符串，返回的字符串是根据produces来判断。
	 * 
	 * ----------------------------------------------------
	 * 同时也可以通过 /mapping/produces.xml(json)调用下面各自的方法
	 * /mapping/produces.xml调用最后一个方法
	 * /mapping/produces.json调用当前的方法
	 * 
	 * ----------------------------------------------------
	 * 如果把下面两个方法删除，添加一个方法：
	 * @RequestMapping(value="/mapping/produces", method=RequestMethod.GET)
		public @ResponseBody JavaBean byProducesJson() {
			return new JavaBean();
		}
	 * /mapping/produces.xml	返回xml字符串
	 * /mapping/produces.json	返回json字符串
	 * /mapping/produces & Accept=application/xml	返回xml字符串
	 * /mapping/produces & Accept=application/json	返回json字符串
	 * ----------------------------------------------------
	 * 
	 * 
	 * @return
	 */
	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JavaBean byProducesJson() {
		return new JavaBean();
	}

	@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody JavaBean byProducesXml() {
		return new JavaBean();
	}

}
