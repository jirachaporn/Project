import java.util.Dictionary;
import java.util.Hashtable;

    // รหัสผ่านของ admin
public class UserPassDefault implements Backend{

    Dictionary<String, String> dictionary = new Hashtable<>();

    UserPassDefault() {
        dictionary.put("pum", "111"); 
        dictionary.put("itti", "222");
    }

    @Override
    public Dictionary GetValueOfDic(){
        return dictionary;
    }  
}
