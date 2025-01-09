package APITests;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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
            "          },\n" +
            "          {\n" +
            "            \"storeId\": \"002\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"storeId\": \"003\"\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";
    String POSTResponse = "[\n" +
            "  {\n" +
            "    \"id\": \"\",\n" +
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
        List<String> storeIds = JsonPath.read(GETResponseJson, "$.buylist[0].product.availability[*].storeId");

        JSONObject POSTBody;
        JSONArray POSTResponseJson;

        for (String storeId: storeIds) {
            POSTBody = createPOSTBody(id, location, storeId);
            System.out.println(POSTBody);
            System.out.println(replaceInJSONArray(POSTBody, "storages","storageId", "0000"));
            System.out.println(replaceInJSONArray2(POSTBody, "storageId", "0000", "1111"));
//            System.out.println(replaceInJSONArrayUsingJsonPath(POSTBody, "$..storages[0]", "storageId", "1112"));

            POSTResponseJson = (JSONArray) parser.parse(POSTResponse);

            String itemId = JsonPath.parse(POSTResponseJson).read("$[0].items[0].itemId");

            Assert.assertEquals(id, itemId, "IDs don't match");
        }
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

    public JSONObject replaceInJSONArray(JSONObject jsonObject, String arrayName, String key, String newValue) {
        JSONObject object = (JSONObject) ((JSONArray) jsonObject.get(arrayName)).get(0);
        object.put(key, newValue);
        return jsonObject;
    }

    public JSONObject replaceInJSONArray2(JSONObject jsonObject, String key, String oldValue, String newValue) throws ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonObject.toJSONString()
              .replaceAll("\"" + key + "\":\"" + oldValue + "\"", "\"" + key + "\":\"" + newValue + "\""));
    }
}
