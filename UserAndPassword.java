import java.util.Dictionary;
import java.util.Hashtable;

public class UserAndPassword implements Backend{

    Dictionary<String, String> dictionary = new Hashtable<>();

    UserAndPassword() {
        dictionary.put("pum", "111");
        dictionary.put("itti", "222");
    }

    @Override
    public Dictionary GetValueOfDic(){
        return dictionary;
    }

    @Override
    public void WriteFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'WriteFile'");
    }
}
