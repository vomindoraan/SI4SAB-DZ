package student.entities;

import javax.persistence.*;

@Entity
public class CourierRequest {
	private int idUser;
	private int idVehicle;
	private User userByIdUser;
	private Vehicle vehicleByIdVehicle;
	
	@Id
	@Column(name = "IDUser", nullable = false)
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Basic
	@Column(name = "IDVehicle", nullable = false)
	public int getIdVehicle() {
		return idVehicle;
	}
	
	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}
	
	@Override
	public int hashCode() {
		int result = idUser;
		result = 31 * result + idVehicle;
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		CourierRequest that = (CourierRequest) o;
		
		if (idUser != that.idUser) return false;
		if (idVehicle != that.idVehicle) return false;
		
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
	@JoinColumn(name = "IDVehicle", referencedColumnName = "IDVehicle", nullable = false)
	public Vehicle getVehicleByIdVehicle() {
		return vehicleByIdVehicle;
	}
	
	public void setVehicleByIdVehicle(Vehicle vehicleByIdVehicle) {
		this.vehicleByIdVehicle = vehicleByIdVehicle;
	}
}
