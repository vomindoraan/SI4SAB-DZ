package student.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class TransportOffer {
	private int idTransportOffer;
	private Integer idUser;
	private Integer idPackage;
	private BigDecimal percentage;
	private Courier courierByIdUser;
	private Package packageByIdPackage;
	
	@Id
	@Column(name = "IDTransportOffer", nullable = false)
	public int getIdTransportOffer() {
		return idTransportOffer;
	}
	
	public void setIdTransportOffer(int idTransportOffer) {
		this.idTransportOffer = idTransportOffer;
	}
	
	@Basic
	@Column(name = "IDUser", nullable = true)
	public Integer getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	@Basic
	@Column(name = "IDPackage", nullable = true)
	public Integer getIdPackage() {
		return idPackage;
	}
	
	public void setIdPackage(Integer idPackage) {
		this.idPackage = idPackage;
	}
	
	@Basic
	@Column(name = "Percentage", nullable = true, precision = 3)
	public BigDecimal getPercentage() {
		return percentage;
	}
	
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public int hashCode() {
		int result = idTransportOffer;
		result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
		result = 31 * result + (idPackage != null ? idPackage.hashCode() : 0);
		result = 31 * result + (percentage != null ? percentage.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		TransportOffer that = (TransportOffer) o;
		
		if (idTransportOffer != that.idTransportOffer) return false;
		if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
		if (idPackage != null ? !idPackage.equals(that.idPackage) : that.idPackage != null) return false;
		if (percentage != null ? !percentage.equals(that.percentage) : that.percentage != null) return false;
		
		return true;
	}
	
	@ManyToOne
	@JoinColumn(name = "IDUser", referencedColumnName = "IDUser")
	public Courier getCourierByIdUser() {
		return courierByIdUser;
	}
	
	public void setCourierByIdUser(Courier courierByIdUser) {
		this.courierByIdUser = courierByIdUser;
	}
	
	@ManyToOne
	@JoinColumn(name = "IDPackage", referencedColumnName = "IDPackage")
	public Package getPackageByIdPackage() {
		return packageByIdPackage;
	}
	
	public void setPackageByIdPackage(Package packageByIdPackage) {
		this.packageByIdPackage = packageByIdPackage;
	}
}
