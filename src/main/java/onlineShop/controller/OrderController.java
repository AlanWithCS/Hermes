package onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import onlineShop.model.Cart;
import onlineShop.model.Customer;
import onlineShop.model.SalesOrder;
import onlineShop.service.CartService;
import onlineShop.service.SalesOrderService;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@RequestMapping(value = "/order/{cartId}", method = RequestMethod.GET)
	public String createOrder(@PathVariable("cartId") int cartId) {
		SalesOrder order = new SalesOrder();
		Cart cart = cartService.getCartById(cartId);
		order.setCart(cart);
		Customer customer = cart.getCustomer();
		order.setCustomer(customer);
		order.setBillingAddress(customer.getBillingAddress());
		order.setShippingAddress(customer.getShippingAddress());
		salesOrderService.addSalesOrder(order);
		return "redirect:/checkout?cartId="+cartId;
	}
	
}
