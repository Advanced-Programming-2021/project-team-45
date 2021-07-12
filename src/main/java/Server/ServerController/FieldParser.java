package Server.ServerController;

import com.gilecode.yagson.YaGson;

public class FieldParser {
    public Object[] getObjects(String request) {
        String json = request.split("\n")[3];
        YaGson yaGson = new YaGson();
        return yaGson.fromJson(json, Object[].class);
    }

    public String getAnswer(Object answer) {
        YaGson yaGson = new YaGson();
        return answer.getClass().getName() + "\n"
                + yaGson.toJson(answer);
    }
}
