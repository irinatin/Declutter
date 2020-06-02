import java.math.BigDecimal;
import java.util.List;

import com.techelevator.model.Stuff;

public interface StuffDAO {
	
	public boolean addNewStuff(Stuff stuffItem,  long familyId);
	
	public List<Stuff> getItemsPerFamily(long userId);
	
	public List<Stuff> getAllItems();
	
	public boolean deleteItem(Stuff stuffItem);
	
	public String findItemByName(Stuff stuffItem);
	
	public BigDecimal findItemByPrice(BigDecimal price);
	
	public List<Stuff> getItemsByCategory(long categoryId);
	
	

}
