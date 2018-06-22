package student.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {
	private String username;
	private String lastName;
	private Integer packagesSent;
	private String firstName;
	private Boolean isAdmin;
	private int idUser;
	private String password;
	private Courier courierByIdUser;
	private CourierRequest courierRequestByIdUser;
	private Collection<Package> packagesByIdUser;
	
	@Basic
	@Column(name = "Username", nullable = false, length = 100)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Basic
	@Column(name = "LastName", nullable = false, length = 100)
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Basic
	@Column(name = "PackagesSent", nullable = true)
	public Integer getPackagesSent() {
		return packagesSent;
	}
	
	public void setPackagesSent(Integer packagesSent) {
		this.packagesSent = packagesSent;
	}
	
	@Basic
	@Column(name = "FirstName", nullable = false, length = 100)
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Basic
	@Column(name = "IsAdmin", nullable = true)
	public Boolean getAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "IDUser", nullable = false)
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Basic
	@Column(name = "Password", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (packagesSent != null ? packagesSent.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
		result = 31 * result + idUser;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		User user = (User) o;
		
		if (idUser != user.idUser) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (packagesSent != null ? !packagesSent.equals(user.packagesSent) : user.packagesSent != null) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (isAdmin != null ? !isAdmin.equals(user.isAdmin) : user.isAdmin != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		
		return true;
	}
	
	@OneToOne(mappedBy = "userByIdUser")
	public Courier getCourierByIdUser() {
		return courierByIdUser;
	}
	
	public void setCourierByIdUser(Courier courierByIdUser) {
		this.courierByIdUser = courierByIdUser;
	}
	
	@OneToOne(mappedBy = "userByIdUser")
	public CourierRequest getCourierRequestByIdUser() {
		return courierRequestByIdUser;
	}
	
	public void setCourierRequestByIdUser(CourierRequest courierRequestByIdUser) {
		this.courierRequestByIdUser = courierRequestByIdUser;
	}
	
	@OneToMany(mappedBy = "userByIdUser")
	public Collection<Package> getPackagesByIdUser() {
		return packagesByIdUser;
	}
	
	public void setPackagesByIdUser(Collection<Package> packagesByIdUser) {
		this.packagesByIdUser = packagesByIdUser;
	}
}
