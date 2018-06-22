package operations;

import java.math.BigDecimal;
import java.util.List;

public abstract interface CourierOperations
{
  public abstract boolean insertCourier(String paramString1, String paramString2);
  
  public abstract boolean deleteCourier(String paramString);
  
  public abstract List<String> getCouriersWithStatus(int paramInt);
  
  public abstract List<String> getAllCouriers();
  
  public abstract BigDecimal getAverageCourierProfit(int paramInt);
}
