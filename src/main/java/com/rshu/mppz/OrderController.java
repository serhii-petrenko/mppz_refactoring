
package com.rshu.mppz;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/orders/{orderId}")
    public ModelAndView showOrder(@PathVariable("orderId") int orderId) {
        ModelAndView mav = new ModelAndView("all/orderDetails");
        Order order = orderRepository.findById(orderId);
        mav.addObject(order);
        return mav;
    }

    @GetMapping("/orders/new/{customerId}")
    public String initCreationForm(Customer customer, ModelMap model) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(customer.getCustomerId()));
        model.put("order", order);
        List<Product> products = productRepository.findAll();
        model.put("selectedItems", products);
        return "all/createNewOrder";
    }

    @PostMapping("/orders/new")
    public String processCreationForm(Order order, BindingResult result, ModelMap model, @RequestParam(value = "products", required = false) List<Integer> products) {
        if (CollectionUtils.isEmpty(products)) {
            result.rejectValue("products", "empty_product_list", "order should contain products");
        }

        List<Product> productList = productRepository.findAll().stream()
                .filter(product -> products.contains(product.getProductId()))
                .toList();
        order.setProducts(productList);
        orderRepository.save(order);
        return "redirect:/customers/{customerId}";
    }

}
