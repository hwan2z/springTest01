package com.hwan.order.controller.product;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hwan.order.model.ProductModel;
import com.hwan.order.service.ProductService;

@Controller
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/edit.hwan", method=RequestMethod.GET)
	public String edit(Model model){
		ProductModel product = new ProductModel();
		model.addAttribute("product", product);
		return "edit";
	}
	
	@RequestMapping(value="edit.hwan", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("product") ProductModel model, BindingResult bindingResult,
								@RequestParam(value="image", required=false) MultipartFile image,
								HttpServletRequest request){
		if(bindingResult.hasErrors()){
			return "edit";
		}
		
		try {
			if(!image.isEmpty()){
				if(!image.getContentType().equals("image/jpeg")){
					throw new ImageUploadException("JPEG 이미지만 선택해주세요.");
				}
				String webRootPath = request.getSession().getServletContext().getRealPath("/");
				String filePath = webRootPath + "resources/upload/product/" + image.getOriginalFilename();
				File file = new File(filePath);
				FileUtils.writeByteArrayToFile(file, image.getBytes());
				logger.info("업로드 파일 : " + filePath);
			}
		} catch (Exception e) {
			bindingResult.reject(e.getMessage());
			return "edit";
		}
		productService.saveProduct(model.buildDomain());		
		return "result";
	}

}
