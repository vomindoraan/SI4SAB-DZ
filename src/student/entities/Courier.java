package student.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Courier {
	public static final int STATUS_NOT_DRIVING = 0;
	public static final int STATUS_DRIVING = 1;
	
	private Integer status;
	private Integer deliveries;
	private int idUser;
	private BigDecimal profit;
	private Integer idVehicle;
	private User userByIdUser;
	private Vehicle vehicleByIdVehicle;
	private Collection<Drive> drivesByIdUser;
	private Collection<TransportOffer> transportOffersByIdUser;
	
	@Basic
	@Column(name = "Status", nullable = true)
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Basic
	@Column(name = "Deliveries", nullable = true)
	public Integer getDeliveries() {
		return deliveries;
	}
	
	public void setDeliveries(Integer deliveries) {
		this.deliveries = deliveries;
	}
	
	@Id
	@Column(name = "IDUser", nullable = false)
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Basic
	@Column(name = "Profit", nullable = true, precision = 3)
	public BigDecimal getProfit() {
		return profit;
	}
	
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	
	@Basic
	@Column(name = "IDVehicle", nullable = true)
	public Integer getIdVehicle() {
		return idVehicle;
	}
	
	public void setIdVehicle(Integer idVehicle) {
		this.idVehicle = idVehicle;
	}
	
	@Override
	public int hashCode() {
		int result = status != null ? status.hashCode() : 0;
		result = 31 * result + (deliveries != null ? deliveries.hashCode() : 0);
		result = 31 * result + idUser;
		result = 31 * result + (profit != null ? profit.hashCode() : 0);
		result = 31 * result + (idVehicle != null ? idVehicle.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Courier courier = (Courier) o;
		
		if (idUser != courier.idUser) return false;
		if (status != null ? !status.equals(courier.status) : courier.status != null) return false;
		if (deliveries != null ? !deliveries.equals(courier.deliveries) : courier.deliveries != null) return false;
		if (profit != null ? !profit.equals(courier.profit) : courier.profit != null) return false;
		if (idVehicle != null ? !idVehicle.equals(courier.idVehicle) : courier.idVehicle != null) return false;
		
		return true;
	}
	
	@OneToOne
	@JoinColumn(name = "IDUser", referencedColumnName = "IDUser", nullable = false)
	public User getUserByIdUser() {
		return userByIdUser;
	}
	
	public void setUserByIdUser(User userByIdUser) {
		this.userByIdUser = userByIdUser;
	}
	
	@ManyToOne
	@JoinColumn(name = "IDVehicle", referencedColumnName = "IDVehicle")
	public Vehicle getVehicleByIdVehicle() {
		return vehicleByIdVehicle;
	}
	
	public void setVehicleByIdVehicle(Vehicle vehicleByIdVehicle) {
		this.vehicleByIdVehicle = vehicleByIdVehicle;
	}
	
	@OneToMany(mappedBy = "courierByIdUser")
	public Collection<Drive> getDrivesByIdUser() {
		return drivesByIdUser;
	}
	
	public void setDrivesByIdUser(Collection<Drive> drivesByIdUser) {
		this.drivesByIdUser = drivesByIdUser;
	}
	
	@OneToMany(mappedBy = "courierByIdUser")
	public Collection<TransportOffer> getTransportOffersByIdUser() {
		return transportOffersByIdUser;
	}
	
	public void setTransportOffersByIdUser(Collection<TransportOffer> transportOffersByIdUser) {
		this.transportOffersByIdUser = transportOffersByIdUser;
	}
}
