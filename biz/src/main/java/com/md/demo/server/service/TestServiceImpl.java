package com.md.demo.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.md.demo.server.dal.dao.TestDAO;

/**
 * 
 * TestServiceImpl
 * 
 * @author chenchao
 * @date Jul 14, 2015 3:34:59 PM
 *
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDAO testDAO;

	@Override
	public String testResult(int id) {
		return testDAO.selectTest(id).getName();
	}

}
