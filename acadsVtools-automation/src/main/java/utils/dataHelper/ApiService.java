package utils.dataHelper;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static io.restassured.RestAssured.*;

import org.json.simple.parser.ParseException;
import utils.configLoaders.ConfigUtils;

import java.util.List;

public class ApiService {

    protected String adminXvedToken() throws ParseException {

       String adminCredentails =  ConfigUtils.CONFIG.getProperty("auth.token.credentials");
        JSONParser parser = new JSONParser();
        JSONObject requestParam = (JSONObject) parser.parse(adminCredentails);
        String apiEndPointToken = ConfigUtils.CONFIG.getProperty("post.admin.auth.api");

        Response response =
        given().
                contentType(ContentType.JSON).
                body(requestParam.toString()).
        when().
                post(apiEndPointToken);
        String cookie = null;
        for (Header header : response.getHeaders()) {
            if (header.getName().equals("Set-Cookie")) {
                cookie = header.getValue();
                //System.out.println(cookie);
            }
        }
        return cookie;

    }


    public JsonPath batchDetails(String reqBatchId) throws ParseException {
        ApiService token = new ApiService();
        String apiEndpointBatch = ConfigUtils.CONFIG.getProperty("get.batch.details.api") + reqBatchId;

        Response response =
                given().
//                        pathParam("batchId", reqBatchId).
                        header("cookie",token.adminXvedToken()).
                        when().
                        get(apiEndpointBatch);

        JsonPath jsonPath = response.jsonPath();

        return jsonPath;
    }


    public String batchGroupName(String reqBatchId) throws ParseException {

        JsonPath groupName = batchDetails(reqBatchId);
        return groupName.getString("groupName");

    }

    public List<String> batchWeeklyPlanDay(String reqBatchId) throws ParseException {

        JsonPath weeklyPlanDay = batchDetails(reqBatchId);
        List<String> reqDayofWeeks = weeklyPlanDay.getList("batchSchedule.from.dayOfWeek");

        return reqDayofWeeks;
    }

    public List<String> batchWeeklyPlanHour(String reqBatchId) throws ParseException {

        JsonPath weeklyPlanFrom = batchDetails(reqBatchId);
        List<String> reqweeklyPlanFrom = weeklyPlanFrom.getList("batchSchedule.from.hours");

        return reqweeklyPlanFrom;
    }

    public List<String> batchWeeklyPlanMinutes(String reqBatchId) throws ParseException {
        JsonPath weeklyPlanMinutes = batchDetails(reqBatchId);
        List<String> reqweeklyPlanMinutes = weeklyPlanMinutes.getList("batchSchedule.from.minutes");

        return reqweeklyPlanMinutes;
    }

    public List<String> batchTeachers(String reqBatchId) throws ParseException {
        JsonPath teachers = batchDetails(reqBatchId);
        List<String> reqTeachers = teachers.getList("teachers.userId");

        return reqTeachers;
    }
}
