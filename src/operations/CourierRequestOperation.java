package operations;

import java.util.List;

public abstract interface CourierRequestOperation
{
  public abstract boolean insertCourierRequest(String paramString1, String paramString2);
  
  public abstract boolean deleteCourierRequest(String paramString);
  
  public abstract boolean changeVehicleInCourierRequest(String paramString1,String paramString2);
  
  public abstract List<String> getAllCourierRequests();
  
  public abstract boolean grantRequest(String paramString);
}
