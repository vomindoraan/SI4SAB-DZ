package operations;


import java.util.List;

public abstract interface DistrictOperations
{
  public abstract int insertDistrict(String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int deleteDistricts(String... paramVarArgs);
  
  public abstract boolean deleteDistrict(int paramInt);
  
  public abstract int deleteAllDistrictsFromCity(String paramString);
  
  public abstract List<Integer> getAllDistrictsFromCity(int paramInt);
  
  public abstract List<Integer> getAllDistricts();
}
