package operations;

import java.util.List;

public abstract interface UserOperations
{
  public abstract boolean insertUser(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract int declareAdmin(String paramString);
  
  public abstract Integer getSentPackages(String... paramVarArgs);
  
  public abstract int deleteUsers( String... paramVarArgs);
  
  public abstract List<String> getAllUsers();
}
