package com.hwan.order.controller.product;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration("productConfiguration")
@EnableWebMvc
public class ServletConfiguration extends WebMvcConfigurationSupport {
	
	/*
	@Bean
	public ViewResolver internalViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/product/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(2);
		return viewResolver;
	}
	*/
	/* UrlBasedViewResolver
	@Bean
	public ViewResolver urlViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/product/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setOrder(1);
		return viewResolver;
	}
	 */
	/* ResourceBundleViewResolver
	@Bean
	public ViewResolver resourceViewResolver() {
		ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
		viewResolver.setOrder(1);
		return viewResolver;
	}
	 */
	/* XmlViewResolver
	@Autowired
	ServletContext servletContext;
	@Bean
	public ViewResolver xmlViewResolver() {
		XmlViewResolver viewResolver = new XmlViewResolver();
		Resource resource = new ServletContextResource(servletContext, 
		"/WEB-INF/views/product/views.xml");
		viewResolver.setLocation(resource);
		viewResolver.setOrder(1);
		return viewResolver;
	}
	*/
	/* BeanNameViewResolver
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		viewResolver.setOrder(1);
		return viewResolver;
	}
	@Bean
	public View edit() {
		JstlView view = new JstlView();
		view.setUrl("/WEB-INF/views/product/edit.jsp");
		return view;
	}
	@Bean
	public View list() {
		JstlView view = new JstlView();
		view.setUrl("/WEB-INF/views/product/list.jsp");
		return view;
	}
	@Bean
	public View result() {
		JstlView view = new JstlView();
		view.setUrl("/WEB-INF/views/product/result.jsp");
		return view;
	}
	 */
}
