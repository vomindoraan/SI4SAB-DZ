package tests;

import java.math.BigDecimal;
import java.util.List;
import operations.GeneralOperations;
import operations.VehicleOperations;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class VehicleOperationsTest
{
  private GeneralOperations generalOperations;
  private VehicleOperations vehicleOperations;
  private TestHandler testHandler;
  
  public VehicleOperationsTest() {}
  
  @Before
  public void setUp()
    throws Exception
  {
    testHandler = TestHandler.getInstance();
    Assert.assertNotNull(testHandler);
    
    vehicleOperations = testHandler.getVehicleOperations();
    Assert.assertNotNull(vehicleOperations);
    
    generalOperations = testHandler.getGeneralOperations();
    Assert.assertNotNull(generalOperations);
    
    generalOperations.eraseAll();
  }
  
  @After
  public void tearDown() throws Exception
  {}
  
  @Test
  public void insertVehicle() {
    String licencePlateNumber = "BG234DU";
    BigDecimal fuelConsumption = new BigDecimal(6.3D);
    int fuelType = 1;
    
    Assert.assertTrue(vehicleOperations.insertVehicle(licencePlateNumber, fuelType, fuelConsumption));
  }
  
  @Test
  public void deleteVehicles() {
    String licencePlateNumber = "BG234DU";
    BigDecimal fuelConsumption = new BigDecimal(6.3D);
    int fuelType = 1;
    
    vehicleOperations.insertVehicle(licencePlateNumber, fuelType, fuelConsumption);
    
    Assert.assertEquals(1L, vehicleOperations.deleteVehicles(new String[] { licencePlateNumber }));
  }
  
  @Test
  public void getAllVehichles() {
    String licencePlateNumber = "BG234DU";
    BigDecimal fuelConsumption = new BigDecimal(6.3D);
    int fuelType = 1;
    
    vehicleOperations.insertVehicle(licencePlateNumber, fuelType, fuelConsumption);
    
    Assert.assertTrue(vehicleOperations.getAllVehichles().contains(licencePlateNumber));
  }
  
  @Test
  public void changeFuelType() {
    String licencePlateNumber = "BG234DU";
    BigDecimal fuelConsumption = new BigDecimal(6.3D);
    int fuelType = 1;
    
    vehicleOperations.insertVehicle(licencePlateNumber, fuelType, fuelConsumption);
    Assert.assertTrue(vehicleOperations.changeFuelType(licencePlateNumber, 2));
  }
  
  @Test
  public void changeConsumption() {
    String licencePlateNumber = "BG234DU";
    BigDecimal fuelConsumption = new BigDecimal(6.3D);
    int fuelType = 1;
    
    vehicleOperations.insertVehicle(licencePlateNumber, fuelType, fuelConsumption);
    Assert.assertTrue(vehicleOperations.changeConsumption(licencePlateNumber, new BigDecimal(7.3D)));
  }
}
