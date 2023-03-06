package com.products.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.products.entity.Products;
import com.products.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductRepository productRepository;
	
	
	
//	@GetMapping("/")
//	public String index() {
//		return "index";
//	}
	
	@GetMapping("/addform")
	public String addForm() {
		return "add";
	}
	
	@GetMapping("/editform/{id}")
	public String editForm(@PathVariable long id) {
		return "edit";
	}
	
	
	@PostMapping("/addProducts")
	public String addProducts(@ModelAttribute Products products, HttpSession session) {
		
		productRepository.save(products);
		session.setAttribute("msg", "Product Added Successfully.");
		
		return "redirect:/addform";
	}
	
	
	 @GetMapping("/")
	    public String getAllProducts(Model model){
	        model.addAttribute("index",productRepository.findAll());
	        return "index";
	    }
	
	
	@GetMapping("/editProducts/{id}")
	public ModelAndView getProductById(@PathVariable long id, HttpSession session) {
		
		ModelAndView mav = new ModelAndView("edit");
		Products products = productRepository.findById(id).get();
		mav.addObject("products", products);
//		session.setAttribute("msg", "Record Updated Succussfully");
		return mav;
	}
	
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute Products products) {
		productRepository.save(products);
		return "redirect:/";
	}
	
	

	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable long id) {
		productRepository.deleteById(id);
		return "redirect:/";
	}


	
	
	
	
	
	
	
	
	// CRUD by API
	
	
	
//	
//	
//	@GetMapping("/products")
//	public List<Products> getAllProcuts(){
//		return productRepository.findAll();
//	}
//	
//	@GetMapping("/products/{id}")
//	public Optional<Products> getProductById(@PathVariable long id) {
//			return productRepository.findById(id);		
//	}
//	
//	@PostMapping("/addProducts")
//	public Products addProducts(@RequestBody Products products) {
//		return productRepository.save(products);
//	}
//	
//	@DeleteMapping("/deleteProduct/{id}")
//	public Optional<String> deleteProductById(@PathVariable long id) {
//		if(!productRepository.existsById(id)) {
//			return Optional.ofNullable(id+" is Not Present. Please Enter the Valid ID");
//		}else {
//			productRepository.deleteById(id);
//			return Optional.ofNullable(" ID "+id+" Deleted");
//		}
//		
//	}
//	
//	
//	@PutMapping("/updateProduct/{id}")
//	public Products updateProductById(@PathVariable long id, @RequestBody Products products){
//		
//		//get the product details which u want to update
//		String productName = products.getProductName();
//		String price = products.getPrice();
//		String quantity = products.getQuantity();
//		String desc = products.getDescription();
//		
//		// get the product by id. which u want to update
//		Products prod = productRepository.findById(id).get();
//		
//		//Set the product details...
//		prod.setProductName(productName);
//		prod.setDescription(desc);
//		prod.setPrice(price);
//		prod.setQuantity(quantity);
//		return productRepository.save(prod);
//	}
	
}
