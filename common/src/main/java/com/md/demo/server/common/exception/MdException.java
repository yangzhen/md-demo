package com.md.demo.server.common.exception;

import com.md.demo.server.common.util.RES_STATUS;

/**
 * 
 * MdException 
 * @author chenchao
 * @date Jul 14, 2015 8:22:16 PM
 *
 */
public class MdException extends RuntimeException {

	private static final long serialVersionUID = 2195068675053227207L;

	private RES_STATUS status;

	public MdException() {
		super();
	}

	public MdException(String message, RES_STATUS status) {
		super(message);
		this.status = status;
	}

	public RES_STATUS getStatus() {
		return status;
	}

	public void setStatus(RES_STATUS status) {
		this.status = status;
	}

}
