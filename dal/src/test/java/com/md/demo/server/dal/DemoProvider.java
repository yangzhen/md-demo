/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.md.demo.server.dal;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.md.demo.server.service.DemoService;

public class DemoProvider {

	public static void main(String[] args) throws IOException {
        //com.alibaba.dubbo.container.Main.main(args);
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("dubbo-demo-provider.xml");
		applicationContext.start();
		System.out.println("按任意键退出");
		System.in.read();
		//DemoService service = applicationContext.getBean(DemoService.class);
		//System.out.println(service.sayHello("aaaa"));
	}

}