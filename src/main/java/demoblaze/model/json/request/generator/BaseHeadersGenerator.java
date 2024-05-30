package demoblaze.model.json.request.generator;

import java.util.HashMap;
import java.util.Map;

public class BaseHeadersGenerator {

    public Map<String, String> generateBaseHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Connection", "keep-alive");
        return headers;
    }
}