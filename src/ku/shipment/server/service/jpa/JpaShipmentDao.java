package ku.shipment.server.service.jpa;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ku.shipment.server.entity.Shipment;
import ku.shipment.server.service.ShipmentDao;


public class JpaShipmentDao implements ShipmentDao{
	private final EntityManager em;

	/**
	 * constructor with injected EntityManager to use.
	 * 
	 * @param em
	 *            an EntityManager for accessing JPA services.
	 */
	public JpaShipmentDao(EntityManager em) {
		this.em = em;
	}


	/**
	 * @see contact.service.ContactDao#find(long)
	 */
	@Override
	public Shipment find(long id) {
		return em.find(Shipment.class, id);
	}

	/**
	 * @see contact.service.ContactDao#findAll()
	 */
	@Override
	public List<Shipment> findAll() {
		Query query = em.createQuery("SELECT c FROM Shipment c");
		List<Shipment> products = query.getResultList();
		return Collections.unmodifiableList(products);
	}
	
}
