package student.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class DrivePK implements Serializable {
	private int idUser;
	private int idPackage;

	@Column(name = "IDUser", nullable = false)
	@Id
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "IDPackage", nullable = false)
	@Id
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

		DrivePK drivePK = (DrivePK) o;

		if (idUser != drivePK.idUser) return false;
		if (idPackage != drivePK.idPackage) return false;

		return true;
	}
}
