package tests;


import operations.CityOperations;
import operations.CourierOperations;
import operations.CourierRequestOperation;
import operations.DistrictOperations;
import operations.GeneralOperations;
import operations.PackageOperations;
import operations.UserOperations;
import operations.VehicleOperations;

public class TestHandler
{
  private static TestHandler testHandler = null;
  private CityOperations cityOperations;
  private CourierOperations courierOperations;
  private CourierRequestOperation courierRequestOperation;
  private DistrictOperations districtOperations;
  private GeneralOperations generalOperations;
  private UserOperations userOperations;
  private VehicleOperations vehicleOperations;
  private PackageOperations packageOperations;
  
  private TestHandler(CityOperations cityOperations,CourierOperations courierOperations, CourierRequestOperation courierRequestOperation, DistrictOperations districtOperations,  GeneralOperations generalOperations,  UserOperations userOperations, VehicleOperations vehicleOperations, PackageOperations packageOperations)
  {
    this.cityOperations = cityOperations;
    this.courierOperations = courierOperations;
    this.courierRequestOperation = courierRequestOperation;
    this.districtOperations = districtOperations;
    this.generalOperations = generalOperations;
    this.userOperations = userOperations;
    this.vehicleOperations = vehicleOperations;
    this.packageOperations = packageOperations;
  }
  
  public static void createInstance(CityOperations cityOperations, CourierOperations courierOperations, CourierRequestOperation courierRequestOperation, DistrictOperations districtOperations, GeneralOperations generalOperations,  UserOperations userOperations, VehicleOperations vehicleOperations, PackageOperations packageOperations)
  {
    testHandler = new TestHandler(cityOperations, courierOperations, courierRequestOperation, districtOperations, generalOperations, userOperations, vehicleOperations, packageOperations);
  }
  
  static TestHandler getInstance()
  {
    return testHandler;
  }
  
  CityOperations getCityOperations()
  {
    return cityOperations;
  }
  
  CourierOperations getCourierOperations()
  {
    return courierOperations;
  }
  
  CourierRequestOperation getCourierRequestOperation()
  {
    return courierRequestOperation;
  }
  
  DistrictOperations getDistrictOperations()
  {
    return districtOperations;
  }
  
  GeneralOperations getGeneralOperations()
  {
    return generalOperations;
  }
  
  UserOperations getUserOperations()
  {
    return userOperations;
  }
  
  VehicleOperations getVehicleOperations()
  {
    return vehicleOperations;
  }
  
  PackageOperations getPackageOperations()
  {
    return packageOperations;
  }
}
