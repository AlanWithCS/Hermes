package onlineShop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override 
	// add a product to the product database
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) session.close();
		}	
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) session.close();
		}	
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// find the product by productID
			Product product = session.get(Product.class, productId);
			session.delete(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (session != null) session.close();
		}	
	}

	@Override
	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			// find the product by productID
			Product product = session.get(Product.class, productId);
			session.getTransaction().commit();
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}	
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		try (Session session = sessionFactory.openSession()){
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> root = query.from(Product.class);
			query.select(root);
			products = session.createQuery(query).getResultList();
			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
