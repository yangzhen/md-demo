package com.md.demo.server.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.md.demo.server.bean.param.TestGetParam;
import com.md.demo.server.bean.vo.ResultMsg;
import com.md.demo.server.bean.vo.TestGetResult;
import com.md.demo.server.common.constant.Constant;
import com.md.demo.server.common.exception.MdException;
import com.md.demo.server.common.util.RES_STATUS;
import com.md.demo.server.service.TestService;

/**
 * 
 * TestController 
 * @author chenchao
 * @date Jul 14, 2015 10:40:02 AM
 *
 */

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	private TestService testService;

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "getDemo", method = RequestMethod.GET)
	@ResponseBody
	public ResultMsg<TestGetResult> getDemo(TestGetParam param,
			HttpServletRequest request) {

		long start = System.currentTimeMillis();

		ResultMsg<TestGetResult> ret = null;

		if (!checkTestParam(param)) {
			ret = new ResultMsg<TestGetResult>(null, RES_STATUS.ERROR_PARAM.code,
					RES_STATUS.ERROR_PARAM.name());
		}

		int id = param.getId();

		String ip = getIpAddr(request);

		String text = null;
		try {
			text = testService.testResult(id);
			TestGetResult data = new TestGetResult();
			data.setResult(text);
			ret = new ResultMsg<TestGetResult>(data, RES_STATUS.SUCCESS.code,
					RES_STATUS.SUCCESS.name());
		} catch (MdException e) {
			ret = new ResultMsg<TestGetResult>(null, e.getStatus().code,
					e.getStatus().name());
		}

		long methodCost = System.currentTimeMillis() - start;

		List<Object> logs = new ArrayList<Object>();
		logs.add(request.getRequestURI());
		logs.add(ip);
		logs.add(200);
		logs.add("SUCCESS");
		logs.add(methodCost);
		logs.add(param.toString());

		logger.info(StringUtils.join(logs, Constant.LOG_SPLIT));

		return ret;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	private boolean checkTestParam(TestGetParam param) {
		return param.getId() >= 0 ? true : false;
	}

	/**
	 * 获取ip地址
	 * @param request
	 * @return ip地址
	 */
	private String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		// tengine config
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("NS-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		int index = ip.indexOf(",");

		if (index > 0) {
			ip = ip.substring(0, index);
		}

		return ip;

	}

}
