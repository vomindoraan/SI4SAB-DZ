package student.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Vehicle {
	public static final int FUEL_TYPE_LPG = 0;
	public static final int FUEL_TYPE_DIESEL = 1;
	public static final int FUEL_TYPE_GASOLINE = 2;
	
	private Integer fuelType;
	private BigDecimal fuelConsumption;
	private String plateNumber;
	private int idVehicle;
	private Collection<Courier> couriersByIdVehicle;
	private Collection<CourierRequest> courierRequestsByIdVehicle;
	
	@Basic
	@Column(name = "FuelType", nullable = true)
	public Integer getFuelType() {
		return fuelType;
	}
	
	public void setFuelType(Integer fuelType) {
		this.fuelType = fuelType;
	}
	
	@Basic
	@Column(name = "FuelConsumption", nullable = true, precision = 3)
	public BigDecimal getFuelConsumption() {
		return fuelConsumption;
	}
	
	public void setFuelConsumption(BigDecimal fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	
	@Basic
	@Column(name = "PlateNumber", nullable = false, length = 100)
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "IDVehicle", nullable = false)
	public int getIdVehicle() {
		return idVehicle;
	}
	
	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}
	
	@Override
	public int hashCode() {
		int result = fuelType != null ? fuelType.hashCode() : 0;
		result = 31 * result + (fuelConsumption != null ? fuelConsumption.hashCode() : 0);
		result = 31 * result + (plateNumber != null ? plateNumber.hashCode() : 0);
		result = 31 * result + idVehicle;
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Vehicle vehicle = (Vehicle) o;
		
		if (idVehicle != vehicle.idVehicle) return false;
		if (fuelType != null ? !fuelType.equals(vehicle.fuelType) : vehicle.fuelType != null) return false;
		if (fuelConsumption != null ? !fuelConsumption.equals(vehicle.fuelConsumption)
		                            : vehicle.fuelConsumption != null) {
			return false;
		}
		if (plateNumber != null ? !plateNumber.equals(vehicle.plateNumber) : vehicle.plateNumber != null) return false;
		
		return true;
	}
	
	@OneToMany(mappedBy = "vehicleByIdVehicle")
	public Collection<Courier> getCouriersByIdVehicle() {
		return couriersByIdVehicle;
	}
	
	public void setCouriersByIdVehicle(Collection<Courier> couriersByIdVehicle) {
		this.couriersByIdVehicle = couriersByIdVehicle;
	}
	
	@OneToMany(mappedBy = "vehicleByIdVehicle")
	public Collection<CourierRequest> getCourierRequestsByIdVehicle() {
		return courierRequestsByIdVehicle;
	}
	
	public void setCourierRequestsByIdVehicle(Collection<CourierRequest> courierRequestsByIdVehicle) {
		this.courierRequestsByIdVehicle = courierRequestsByIdVehicle;
	}
}
