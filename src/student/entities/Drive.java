package student.entities;

import javax.persistence.*;

@Entity
@IdClass(DrivePK.class)
public class Drive {
	private int idUser;
	private int idPackage;
	private Courier courierByIdUser;
	private Package packageByIdPackage;
	
	@Id
	@Column(name = "IDUser", nullable = false)
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Id
	@Column(name = "IDPackage", nullable = false)
	public int getIdPackage() {
		return idPackage;
	}
	
	public void setIdPackage(int idPackage) {
		this.idPackage = idPackage;
	}
	
	@Override
	public int hashCode() {
		int result = idUser;
		result = 31 * result + idPackage;
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Drive drive = (Drive) o;
		
		if (idUser != drive.idUser) return false;
		if (idPackage != drive.idPackage) return false;
		
		return true;
	}
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "IDUser", referencedColumnName = "IDUser")
	public Courier getCourierByIdUser() {
		return courierByIdUser;
	}
	
	public void setCourierByIdUser(Courier courierByIdUser) {
		this.courierByIdUser = courierByIdUser;
	}
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "IDPackage", referencedColumnName = "IDPackage")
	public Package getPackageByIdPackage() {
		return packageByIdPackage;
	}
	
	public void setPackageByIdPackage(Package packageByIdPackage) {
		this.packageByIdPackage = packageByIdPackage;
	}
}
