package operations;
import java.math.BigDecimal;
import java.util.List;

public abstract interface VehicleOperations
{
  public abstract boolean insertVehicle(String paramString, int paramInt, BigDecimal paramBigDecimal);
  
  public abstract int deleteVehicles(String... paramVarArgs);
  
  public abstract List<String> getAllVehichles();
  
  public abstract boolean changeFuelType(String paramString, int paramInt);
  
  public abstract boolean changeConsumption(String paramString, BigDecimal paramBigDecimal);
}
