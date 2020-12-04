package jorge.rolmap.controller.util;

public class ControllerUtils {

    public static String parseError(Exception e) {
        return "{ \"error\": \"" + e.toString() + "\"}";
    }
}