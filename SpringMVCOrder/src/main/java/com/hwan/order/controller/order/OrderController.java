package com.hwan.order.controller.order;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class OrderController {
	
	/*
	 * 프로퍼티 에디터 : CustomDateEditor
	 * 프로퍼티 에디터는 JavaBeans 표준이기 때문에 몇가지 단점이있다
	 * 스레드에 안정적이지 않다, 그래서 스피링에서 제공하는 컨버터를 사용한다
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
	}
	
	
	
	
	
}
