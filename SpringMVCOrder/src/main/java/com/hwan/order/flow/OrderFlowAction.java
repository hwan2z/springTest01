package com.hwan.order.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hwan.order.domain.Customer;
import com.hwan.order.model.CustomerModel;
import com.hwan.order.service.CustomerService;

@Component
public class OrderFlowAction {
	private static final Logger logger = LoggerFactory.getLogger(OrderFlowAction.class);
	
	@Autowired
	private CustomerService customerService;
	
	public enum Result {success, failure}
	
	public boolean checkBlackList(String name){
		if("홍딜동".equals(name)){
			return true;
		}else{
			return false;
		}
	}
	
	public Result addCustomer(CustomerModel customer) {
		logger.info(customer.getName() + " : " + customer.getAddress());
		try{
			customerService.saveCustomer(customer.buildDomain());
			return Result.success;
		}catch(Exception e) {
			return Result.failure;
		}
	}
	
	public CustomerModel getCustomer(String name) throws CustomerNotFoundException {
		if("김일".equals(name))
			throw new CustomerNotFoundException("김일 고객이 없습니다.");
		Customer customer =  customerService.getCustomerByName(name);
		CustomerModel model = new CustomerModel();
		model.buildModel(customer);
		return model;
	}
}
