package onlineShop.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Authorities;
import onlineShop.model.Cart;
import onlineShop.model.Customer;
import onlineShop.model.User;

@Repository
// repository is a kind of component
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory sessionFactory; // create a sessionFactory to connect to database
	
	public void addCustomer(Customer customer) {
		// add a customer to the database
		customer.getUser().setEnabled(true); // the customer is active
		
		Authorities authorities = new Authorities();
		authorities.setAuthorities("ROLE_USER");
		authorities.setEmailId(customer.getUser().getEmailId());
		
		Cart cart = new Cart();
		customer.setCart(cart);
		cart.setCustomer(customer);
		
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(authorities);
			session.save(customer);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// get the customer 
	public Customer getCustomerByUserName(String userName) {
		// TODO Auto-generated method stub
		User user = null;
		try (Session session = sessionFactory.openSession()) {
			// use criteria to search
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class); // start from root
			
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("emailId"), userName));
			user = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			return user.getCustomer();
		}
		return null;
	}
	
}

