package com.hwan.order.controller.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.hwan.order.domain.Customer;
import com.hwan.order.model.CustomerCondition;
import com.hwan.order.model.CustomerModel;
import com.hwan.order.service.CustomerService;

@Secured("ROLE_USER")
@Controller
@RequestMapping(value="/")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	
	@RequestMapping(value="/edit.hwan", method=RequestMethod.GET)
	public String edit(Model model) {
		CustomerModel customer = new CustomerModel();
		model.addAttribute("customer", customer);
		return "edit";
	}
		
	@RequestMapping(value="/edit.hwan", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("customer") CustomerModel model, BindingResult bindingResult, Locale locale) {
		if(bindingResult.hasErrors()){
			return "edit";
		}
		try {
			String message = messageSource.getMessage("customer.enroll", 
					new Object[] {
							model.getName(), model.getAddress(), model.getEmail()
					}, locale);
			logger.info(message);			
			customerService.saveCustomer(model.buildDomain());
		} catch (Exception e) {
			return "forward:/error.hwan";
		}		
		return "result";
	}
	
	@RequestMapping(value="/list.hwan", method=RequestMethod.GET)
	@ModelAttribute("customers")
	public List<CustomerModel> list(){
		List<Customer> customers = customerService.getCustomers();
		List<CustomerModel> customerModels = convert(customers);
		return customerModels;
	}
	
	@RequestMapping(value="/error.hwan", method={RequestMethod.GET, RequestMethod.POST})
	public String error(Model model) {
		return "error";
	}
	
	@RequestMapping(value="/search.hwan", method=RequestMethod.GET)
	@ModelAttribute("customers")
	public List<CustomerModel> search(@ModelAttribute("customerCondition") CustomerCondition customerCondition){
		if(null == customerCondition.getName()){
			return null;
		}
		List<Customer> customers = customerService.getCustomersByName(customerCondition.getName());
		List<CustomerModel> customerModels = convert(customers);
		return customerModels;
	}
	
	private List<CustomerModel> convert(List<Customer> customers) {
		List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
		for(Customer customer : customers){
			CustomerModel customerModel = new CustomerModel();
			customerModel.buildModel(customer);
			customerModels.add(customerModel);
		}
		return customerModels;
	}
	
}
