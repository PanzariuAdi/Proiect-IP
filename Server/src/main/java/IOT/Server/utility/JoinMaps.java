package IOT.Server.utility;

import java.util.HashMap;
import java.util.Map;

public class JoinMaps {
    public static Map<String,Object> joinMaps(Map<String,Object> map1, Map<String,Object> map2){
        Map<String,Object> result = new HashMap<>(map1);
        map2.keySet().forEach(x->{result.put(x,map2.get(x));});
        return result;
    }
}
