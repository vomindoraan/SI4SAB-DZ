package student.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class District {
	private String name;
	private Integer xPos;
	private Integer yPos;
	private int idCity;
	private int idDistrict;
	private City cityByIdCity;
	private Collection<Package> packagesByIdDistrict;
	private Collection<Package> packagesByIdDistrict_0;
	
	@Basic
	@Column(name = "Name", nullable = false, length = 100)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
	@Column(name = "XPos", nullable = true)
	public Integer getxPos() {
		return xPos;
	}
	
	public void setxPos(Integer xPos) {
		this.xPos = xPos;
	}
	
	@Basic
	@Column(name = "YPos", nullable = true)
	public Integer getyPos() {
		return yPos;
	}
	
	public void setyPos(Integer yPos) {
		this.yPos = yPos;
	}
	
	@Basic
	@Column(name = "IDCity", nullable = false)
	public int getIdCity() {
		return idCity;
	}
	
	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "IDDistrict", nullable = false)
	public int getIdDistrict() {
		return idDistrict;
	}
	
	public void setIdDistrict(int idDistrict) {
		this.idDistrict = idDistrict;
	}
	
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (xPos != null ? xPos.hashCode() : 0);
		result = 31 * result + (yPos != null ? yPos.hashCode() : 0);
		result = 31 * result + idCity;
		result = 31 * result + idDistrict;
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		District district = (District) o;
		
		if (idCity != district.idCity) return false;
		if (idDistrict != district.idDistrict) return false;
		if (name != null ? !name.equals(district.name) : district.name != null) return false;
		if (xPos != null ? !xPos.equals(district.xPos) : district.xPos != null) return false;
		if (yPos != null ? !yPos.equals(district.yPos) : district.yPos != null) return false;
		
		return true;
	}
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "IDCity", referencedColumnName = "IDCity")
	public City getCityByIdCity() {
		return cityByIdCity;
	}
	
	public void setCityByIdCity(City cityByIdCity) {
		this.cityByIdCity = cityByIdCity;
	}
	
	@OneToMany(mappedBy = "districtByIdDistrict1")
	public Collection<Package> getPackagesByIdDistrict() {
		return packagesByIdDistrict;
	}
	
	public void setPackagesByIdDistrict(Collection<Package> packagesByIdDistrict) {
		this.packagesByIdDistrict = packagesByIdDistrict;
	}
	
	@OneToMany(mappedBy = "districtByIdDistrict2")
	public Collection<Package> getPackagesByIdDistrict_0() {
		return packagesByIdDistrict_0;
	}
	
	public void setPackagesByIdDistrict_0(Collection<Package> packagesByIdDistrict_0) {
		this.packagesByIdDistrict_0 = packagesByIdDistrict_0;
	}
}
