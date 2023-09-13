package utils.dataHelper;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static io.restassured.RestAssured.*;

import org.json.simple.parser.ParseException;
import utils.configLoaders.ConfigUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiService  {

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


    public Map<String, Object> teacher1Cookie(String teacherId) throws ParseException {

        String teacher1Credentails =  ConfigUtils.CONFIG.getProperty("auth."+teacherId+".credentials");
        JSONParser parser = new JSONParser();
        JSONObject requestParam = (JSONObject) parser.parse(teacher1Credentails);
        String apiEndPointToken = ConfigUtils.CONFIG.getProperty("post.admin.auth.api");

        Response response =
                given().
                        contentType(ContentType.JSON).
                        body(requestParam.toString()).
                        when().
                        post(apiEndPointToken);
        String teacher1Cookie = null;
        for (Header header : response.getHeaders()) {
            if (header.getName().equals("Set-Cookie")) {
                teacher1Cookie = header.getValue();
            }
        }

        long teacherUserId = response.jsonPath().get("userId");

        Map<String, Object> teacherDetails = new HashMap<>();
        teacherDetails.put("teacherCookie",teacher1Cookie);
        teacherDetails.put("teacherUserId",teacherUserId);

        return teacherDetails;

    }

    public Map<String, Long> teacher1SlotMarking(String teacherName, long currentTimeMillisWithExtra2hrs , long currentTimeMillisWithExtra2_30hrs) throws ParseException {

//        long currentTimeMillis = System.currentTimeMillis();
//        long currentTimeMillisWithExtra2hrs = currentTimeMillis + (2 * 60 * 60 * 1000);
//        long currentTimeMillisWithExtra2_30hrs = currentTimeMillis + (2 * 60 * 60 * 1000) + (30 * 60 * 1000);

        Map<String , Long> requiredCurrentMillies = new HashMap<>();

        JSONObject payload = new JSONObject();
        payload.put("timeZone","Asia/Calcutta");

        JSONArray slotArray = new JSONArray();

        JSONObject solt1 = new JSONObject();
        solt1.put("slotType","SINGLE_SLOT");
        solt1.put("slotStartTime",currentTimeMillisWithExtra2hrs);
        solt1.put("slotEndTime",currentTimeMillisWithExtra2_30hrs);

        slotArray.put(solt1);

        payload.put("slots",slotArray);

        String apiEndPointToken = ConfigUtils.CONFIG.getProperty("post.teacher.slotmark.api")+teacher1Cookie(teacherName).get("teacherUserId");

        Response response = given().
                header("cookie", teacher1Cookie(teacherName).get("teacherCookie")).
                header("content-type", "application/json").
                body(payload.toString()).
                when().
                post(apiEndPointToken);

        int apiStatusCode = response.getStatusCode();
        if(apiStatusCode==200){
            boolean apiResponse = response.jsonPath().get("success");
//            System.out.println("apiResponse : "  + apiResponse);
            if (apiResponse){
                requiredCurrentMillies.put("requiredSlotStartTime" , currentTimeMillisWithExtra2hrs);
                requiredCurrentMillies.put("requiredSlotEndTime", currentTimeMillisWithExtra2_30hrs);
                return requiredCurrentMillies;
            }
            currentTimeMillisWithExtra2hrs += (30 * 60 * 1000);
            currentTimeMillisWithExtra2_30hrs += (30 * 60 * 1000);
            requiredCurrentMillies =teacher1SlotMarking(teacherName, currentTimeMillisWithExtra2hrs, currentTimeMillisWithExtra2_30hrs);
        }
        if (apiStatusCode==400){

            String  apiErrorResponse = response.jsonPath().get("errorMessage");
            if(apiErrorResponse.equalsIgnoreCase("Requested slot already exist")){
                currentTimeMillisWithExtra2hrs += (30 * 60 * 1000);
                currentTimeMillisWithExtra2_30hrs += (30 * 60 * 1000);
                requiredCurrentMillies = teacher1SlotMarking(teacherName, currentTimeMillisWithExtra2hrs, currentTimeMillisWithExtra2_30hrs);
            }
        }
        return requiredCurrentMillies;
    }

    public String batchFetchingWithGroupName(String reqBatchCourseId, String reqBatchGroupName) throws ParseException {

        String apiEndPointUrl = ConfigUtils.CONFIG.getProperty("post.batches.api");
        ApiService token = new ApiService();

        Response apiResponse =
                given().
                        queryParam("courseId", reqBatchCourseId).
                        queryParam("start", "0").
                        queryParam("size", "10").
                        header("cookie", token.adminXvedToken().trim()).
                        header("content-type", "application/json").
                        when().
                        get(apiEndPointUrl);

        JsonPath wholeResponse = apiResponse.jsonPath();

        List<String> groupNames = wholeResponse.get("list.groupName");
        List<String> batchIds = wholeResponse.getList("list.id");
        //String list1 = wholeResponse.get("list.groupName[0]");

        String BatchId = null;
        for (int Index = 0; Index < groupNames.size(); Index++) {
            String groupName = groupNames.get(Index);
            if (groupName.equalsIgnoreCase(reqBatchGroupName)) {
                BatchId = batchIds.get(Index);
            }
        }
        return BatchId;
    }

}
