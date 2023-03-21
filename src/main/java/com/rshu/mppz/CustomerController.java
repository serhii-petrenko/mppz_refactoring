
package com.rshu.mppz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
class CustomerController {

	private static final String VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM = "all/createOrUpdateCustomer";

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customers.html")
	public String showCustomerList(@RequestParam(defaultValue = "1") int page, Model model) {
		Customers customerList = new Customers();
		Page<Customer> paginated = findPaginated(page);
		customerList.getCustomerList().addAll(paginated.toList());
		return addPaginationModel(page, paginated, model);

	}

	private String addPaginationModel(int page, Page<Customer> paginated, Model model) {
		List<Customer> customers = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("customerList", customers);
		return "all/customerList";
	}

	private Page<Customer> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return customerRepository.findAll(pageable);
	}

	@GetMapping({ "/customers" })
	public @ResponseBody Customers showResourcesVetList() {
		Customers customers = new Customers();
		customers.getCustomerList().addAll(this.customerRepository.findAll());
		return customers;
	}

	@GetMapping("/customers/{customerId}")
	public ModelAndView showOCustomer(@PathVariable("customerId") int orderId) {
		ModelAndView mav = new ModelAndView("all/customerDetails");
		Customer customer = customerRepository.findById(orderId);
		mav.addObject(customer);
		return mav;
	}

	@GetMapping("/customers/{customerId}/edit")
	public String initUpdateCustomerForm(@PathVariable("customerId") int customerId, Model model) {
		Customer customer = customerRepository.findById(customerId);
		model.addAttribute(customer);
		return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/customers/{customerId}/edit")
	public String processUpdateOwnerForm(Customer customer, BindingResult result,
										 @PathVariable("customerId") int customerId) {
		if (result.hasErrors()) {
			return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
		}

		customer.setCustomerId(customerId);
		customerRepository.save(customer);
		return "redirect:/customers/{customerId}";
	}

	@GetMapping("/customers/new")
	public String initCreationForm(Map<String, Object> model) {
		Customer customer = new Customer();
		model.put("customer", customer);
		return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/customers/new")
	public String processCreationForm(Customer customer, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CUSTOMER_CREATE_OR_UPDATE_FORM;
		}

		customerRepository.save(customer);
		return "redirect:/customers/" + customer.getCustomerId();
	}
}
