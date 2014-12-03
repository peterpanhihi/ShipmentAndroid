package ku.shipment.server.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shipments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shipments {

	@XmlElement(name = "shipment")
	private List<Shipment> shipments;

	public List<Shipment> getContacts() {
		return shipments;
	}

	public void setProducts(List<Shipment> shipments) {
		this.shipments = shipments;
	}
}
