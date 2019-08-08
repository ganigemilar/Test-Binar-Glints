package com.backendglints.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.backendglints.model.request.LoginRequest;
import com.backendglints.model.request.ProductRequest;
import com.backendglints.model.request.SignUpRequest;
import com.backendglints.model.response.LoginResponse;
import com.backendglints.model.response.ProductResponse;
import com.backendglints.model.response.SignUpResponse;
import com.backendglints.service.ApiManagementService;
import com.backendglints.service.TestBinarService;
import com.backendglints.service.ApiManagementService.ApiVersion;

@Controller
public class TestBinarController {
	@Autowired
	private TestBinarService testBinarService;
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("loginForm", new LoginRequest());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute LoginRequest login) {
		try {
			LoginResponse authLogin = testBinarService.login(login);
			if (authLogin != null)
				return "redirect:/dashboard";
			return "redirect:/login";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/signup")
	public String signUp(Model model) throws IOException {
		model.addAttribute("signupForm", new SignUpRequest());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUp(@ModelAttribute SignUpRequest signUp){
		try {
			SignUpResponse resSignUp = testBinarService.signUp(signUp);
			if (resSignUp != null) {
				if (resSignUp.getStatus().equalsIgnoreCase("OK")) {
					return "redirect:/login";
				}
			}
			return "redirect:/signup";
		} catch (Exception e) {
			return "redirect:/signup";
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) throws IOException {
		if (ApiManagementService.getSession() != null) {
			ProductResponse products = testBinarService.getAllProducts();
			
			if (products != null) {
				if (products.getResult() != null) {
					model.addAttribute("products", products.getResult());
				}
			}
			addProduct(model);
			//updateProduct(model, "90");
			model.addAttribute("product", new ProductResponse().new Result());
			
			return "dashboard";
		}
		return "redirect:/login";
	}
	
	@GetMapping("logout")
	public String logout() {
		ApiManagementService.setSession(null);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/products")
	public String getAllProducts() throws IOException {
		return "dashboard";
	}
	
	@GetMapping("/product/add")
	public String addProduct(Model model) {
		model.addAttribute("productForm", new ProductRequest());
		return "redirect:/dashboard";
	}
	
	@PostMapping("/product/add/submit")
	public String submitAddProduct(@ModelAttribute ProductRequest product) throws IOException {
		ProductResponse response = testBinarService.addProduct(product);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/product/update/{id}")
	public String updateProduct(Model model, @PathVariable("id") String id) throws IOException {
		ProductResponse product = testBinarService.getProductById(id);
		model.addAttribute("product", product.getResult().get(0));
		return "redirect:/dashboard";
	}
	
	@PutMapping("/product/update/submit")
	public String submitUpdateProduct(@ModelAttribute ProductRequest product, @ModelAttribute("id") String id) throws IOException {
		ProductResponse resProd = testBinarService.updateProduct(id, product);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(Model model, @PathVariable("id") String id) throws IOException {
		ProductResponse product = testBinarService.getProductById(id);
		model.addAttribute("product", product);
		return "redirect:/dashboard";
	}
	
	@DeleteMapping("/product/delete/{id}/submit")
	public String submitDeleteProduct(@PathVariable("id") String id) throws IOException {
		ProductResponse product = testBinarService.deleteProductById(id);
		return "redirect:/dashboard";
	}
}
