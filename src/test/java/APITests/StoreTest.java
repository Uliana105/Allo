package APITests;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreTest {
    String GETResponse = "{\n" +
            "  \"id\": \"12345\",\n" +
            "  \"location\": \"New York, NY\",\n" +
            "  \"buylist\": [\n" +
            "    {\n" +
            "      \"product\": {\n" +
            "        \"id\": \"98765\",\n" +
            "        \"category\": \"Electronics\",\n" +
            "        \"availability\": [\n" +
            "          {\n" +
            "            \"storeId\": \"001\"\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";
    String POSTResponse = "[\n" +
            "  {\n" +
            "    \"id\": \"001\",\n" +
            "    \"items\": [\n" +
            "      {\n" +
            "        \"itemId\": \"12345\",\n" +
            "        \"itemAmount\": \"5\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]";

    @Test
    public void storeTest() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject GETResponseJson = (JSONObject) parser.parse(GETResponse);

        String id = JsonPath.read(GETResponseJson, "$.id");
        String location = JsonPath.read(GETResponseJson, "$.location");
        String storeId = JsonPath.read(GETResponseJson, "$.buylist[0].product.availability[0].storeId");

        JSONObject POSTBody = createPOSTBody(id, location, storeId);

        JSONArray POSTResponseJson = (JSONArray) parser.parse(POSTResponse);

        String itemId = JsonPath.read(POSTResponseJson, "$[0].items[0].itemId").toString().replaceAll("\\[]\"", "");

        Assert.assertEquals(id, itemId, "IDs don't match");
    }

    public JSONObject createPOSTBody(String id, String location, String storeId) {
        JSONObject mainJsonObject = new JSONObject();
        JSONArray storagesJsonArray = new JSONArray();
        JSONObject storagesJsonObject = new JSONObject();

        storagesJsonObject.put("storageId", storeId);
        storagesJsonObject.put("location", location);
        storagesJsonArray.add(storagesJsonObject);

        mainJsonObject.put("id", id);
        mainJsonObject.put("location", location);
        mainJsonObject.put("storages", storagesJsonArray);

        return mainJsonObject;
    }
}
