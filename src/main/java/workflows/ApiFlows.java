package workflows;

import extensions.ApiActions;
import io.qameta.allure.Step;
import utilities.CommonOps;

public class ApiFlows extends CommonOps {

/*
    @Step ("Business flow : get the current total count")
    public static String getCurrentCount () {
        response = ApiActions.get(getData("API_GET") + "/search");
        return  ApiActions.extractFromJSON(response,"totalCount");

    }

    @Step ("Business flow : return specific property from Json File")
    public static String getTeamProperties (String jpath,String teamName) {
        response = ApiActions.get(getData("API_GET") + teamName);
        return  ApiActions.extractFromJSON(response,jpath);
    }

    @Step ("Business flow : create new team")
    public static void postTeam (String name,String email) {
        params.put("name",name);
        params.put("email",email);
        ApiActions.post(params,getData("API_POST"));
    }

    @Step ("Business flow : update existing team")
    public static void updateTeam (String name,String email,String id) {
        params.put("name",name);
        params.put("email",email);
        ApiActions.put(params,getData("API_PUT") + "/" + id);
    }

    @Step ("Business flow : delete existing team")
    public static void DeleteTeam (String id) {
        ApiActions.delete(getData("API_DELETE"), id);
    }

*/

}
