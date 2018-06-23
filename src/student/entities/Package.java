package student.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Package {
	public static final int TYPE_LETTER = 0;
	public static final int TYPE_STANDARD = 1;
	public static final int TYPE_FRAGILE = 2;
	
	public static final int DELIVERY_STATUS_CREATED = 0;
	public static final int DELIVERY_STATUS_ACCEPTED = 1;
	public static final int DELIVERY_STATUS_DISPATCHED = 2;
	public static final int DELIVERY_STATUS_DELIVERED = 3;
	
	private int idPackage;
	private Integer idDistrict1;
	private Integer idDistrict2;
	private Integer idUser;
	private Integer type;
	private BigDecimal weight;
	private Integer deliveryStatus;
	private Timestamp timeAccepted;
	private BigDecimal deliveryPrice;
	private Collection<Drive> drivesByIdPackage;
	private District districtByIdDistrict1;
	private District districtByIdDistrict2;
	private User userByIdUser;
	private Collection<TransportOffer> transportOffersByIdPackage;
	
	@Id
	@GeneratedValue
	@Column(name = "IDPackage", nullable = false)
	public int getIdPackage() {
		return idPackage;
	}
	
	public void setIdPackage(int idPackage) {
		this.idPackage = idPackage;
	}
	
	@Basic
	@Column(name = "IDDistrict1", nullable = true)
	public Integer getIdDistrict1() {
		return idDistrict1;
	}
	
	public void setIdDistrict1(Integer idDistrict1) {
		this.idDistrict1 = idDistrict1;
	}
	
	@Basic
	@Column(name = "IDDistrict2", nullable = true)
	public Integer getIdDistrict2() {
		return idDistrict2;
	}
	
	public void setIdDistrict2(Integer idDistrict2) {
		this.idDistrict2 = idDistrict2;
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
	@Column(name = "Type", nullable = true)
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Basic
	@Column(name = "Weight", nullable = true, precision = 3)
	public BigDecimal getWeight() {
		return weight;
	}
	
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	@Basic
	@Column(name = "DeliveryStatus", nullable = true)
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}
	
	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	@Basic
	@Column(name = "TimeAccepted", nullable = true)
	public Timestamp getTimeAccepted() {
		return timeAccepted;
	}
	
	public void setTimeAccepted(Timestamp timeAccepted) {
		this.timeAccepted = timeAccepted;
	}
	
	@Basic
	@Column(name = "DeliveryPrice", nullable = true, precision = 3)
	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}
	
	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	
	@Override
	public int hashCode() {
		int result = idPackage;
		result = 31 * result + (idDistrict1 != null ? idDistrict1.hashCode() : 0);
		result = 31 * result + (idDistrict2 != null ? idDistrict2.hashCode() : 0);
		result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (weight != null ? weight.hashCode() : 0);
		result = 31 * result + (deliveryStatus != null ? deliveryStatus.hashCode() : 0);
		result = 31 * result + (timeAccepted != null ? timeAccepted.hashCode() : 0);
		result = 31 * result + (deliveryPrice != null ? deliveryPrice.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Package aPackage = (Package) o;
		
		if (idPackage != aPackage.idPackage) return false;
		if (idDistrict1 != null ? !idDistrict1.equals(aPackage.idDistrict1) : aPackage.idDistrict1 != null)
			return false;
		if (idDistrict2 != null ? !idDistrict2.equals(aPackage.idDistrict2) : aPackage.idDistrict2 != null)
			return false;
		if (idUser != null ? !idUser.equals(aPackage.idUser) : aPackage.idUser != null) return false;
		if (type != null ? !type.equals(aPackage.type) : aPackage.type != null) return false;
		if (weight != null ? !weight.equals(aPackage.weight) : aPackage.weight != null) return false;
		if (deliveryStatus != null ? !deliveryStatus.equals(aPackage.deliveryStatus)
		                           : aPackage.deliveryStatus != null) {
			return false;
		}
		if (timeAccepted != null ? !timeAccepted.equals(aPackage.timeAccepted) : aPackage.timeAccepted != null) {
			return false;
		}
		if (deliveryPrice != null ? !deliveryPrice.equals(aPackage.deliveryPrice) : aPackage.deliveryPrice != null) {
			return false;
		}
		
		return true;
	}
	
	@OneToMany(mappedBy = "packageByIdPackage")
	public Collection<Drive> getDrivesByIdPackage() {
		return drivesByIdPackage;
	}
	
	public void setDrivesByIdPackage(Collection<Drive> drivesByIdPackage) {
		this.drivesByIdPackage = drivesByIdPackage;
	}
	
	@ManyToOne
	@JoinColumn(name = "IDDistrict1", referencedColumnName = "IDDistrict")
	public District getDistrictByIdDistrict1() {
		return districtByIdDistrict1;
	}
	
	public void setDistrictByIdDistrict1(District districtByIdDistrict1) {
		this.districtByIdDistrict1 = districtByIdDistrict1;
	}
	
	@ManyToOne
	@JoinColumn(name = "IDDistrict2", referencedColumnName = "IDDistrict")
	public District getDistrictByIdDistrict2() {
		return districtByIdDistrict2;
	}
	
	public void setDistrictByIdDistrict2(District districtByIdDistrict2) {
		this.districtByIdDistrict2 = districtByIdDistrict2;
	}
	
	@ManyToOne
	@JoinColumn(name = "IDUser", referencedColumnName = "IDUser")
	public User getUserByIdUser() {
		return userByIdUser;
	}
	
	public void setUserByIdUser(User userByIdUser) {
		this.userByIdUser = userByIdUser;
	}
	
	@OneToMany(mappedBy = "packageByIdPackage")
	public Collection<TransportOffer> getTransportOffersByIdPackage() {
		return transportOffersByIdPackage;
	}
	
	public void setTransportOffersByIdPackage(Collection<TransportOffer> transportOffersByIdPackage) {
		this.transportOffersByIdPackage = transportOffersByIdPackage;
	}
}
