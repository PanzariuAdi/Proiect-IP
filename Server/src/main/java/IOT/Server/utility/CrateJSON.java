package IOT.Server.utility;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrateJSON {
    static public String fromMap(Map<String,Object> objectMap){
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keySet = objectMap.keySet();
        stringBuffer.append("{");
        for(String key: keySet){
            stringBuffer.append("\"");
            stringBuffer.append(key);
            stringBuffer.append("\"");
            stringBuffer.append(":");
            stringBuffer.append("\"");
            stringBuffer.append(objectMap.get(key).toString());
            stringBuffer.append("\"");
            stringBuffer.append(",");
        }
        String str = stringBuffer.substring(0,stringBuffer.length()-1);
        stringBuffer = new StringBuffer(str);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
    static public String fromListOfMapsWithKey(List<Map<String, Object>> mapList){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        stringBuffer.append("\"size\":\""+mapList.size()+"\",");
        int currentSize = 0;
        for(Map<String,Object> objectMap : mapList){
            for(String key : objectMap.keySet()){
                stringBuffer.append("\"");
                stringBuffer.append(key);
                stringBuffer.append(currentSize);
                stringBuffer.append("\"");
                stringBuffer.append(":");
                stringBuffer.append("\"");
                stringBuffer.append(objectMap.get(key).toString());
                stringBuffer.append("\"");
                stringBuffer.append(",");
            }
            currentSize++;
        }
        String str = stringBuffer.substring(0,stringBuffer.length()-1);
        stringBuffer = new StringBuffer(str);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

}
