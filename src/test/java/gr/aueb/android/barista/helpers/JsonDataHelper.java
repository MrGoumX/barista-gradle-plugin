package gr.aueb.android.barista.helpers;


public class JsonDataHelper {

    public static final String GEOFIX_JSON = "{\"type\":\"GeoFix\",\"delay\":\"0\",\"sessionToken\":\"1\",\"latitude\":1.0,\"longitude\":2.0}";
    public static final String WM_SIZE_JSON = "{\"type\":\"WmSize\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"width\":"+ConstantValues.width+",\"height\":"+ConstantValues.height+",\"reset\":false,\"unit\":\"DPI\"}";
    public static final String WM_SIZE_JSON_2 ="{\"type\":\"WmSize\",\"delay\":\"0\",\"sessionToken\":\"8ba3eee6-51e6-4d8a-8e5f-1243746ad475\",\"height\":600,\"reset\":false,\"unit\":\"DPI\",\"width\":500}";
    public static final String DENSITY_JSON = "{\"type\":\"WmDensity\",\"delay\":\"0\",\"sessionToken\":\"3\",\"density\":"+ConstantValues.density+"}";
    public static final String RESET_JSON = "{\"type\":\"WmSizeReset\",\"delay\":\"0\",\"sessionToken\":\"1\"}";
    public static final String BATTERY_LVL_JSON = "{\"type\":\"BatteryLevel\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"level\":"+ConstantValues.level+"}";
    public static final String GRANT_JSON = "{\"type\":\"PmGrant\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"permission\":\""+ConstantValues.permission+"\"}";
    public static final String REVOKE_JSON = "{\"type\":\"PmRevoke\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"permission\":\""+ConstantValues.permission+"\"}";
    public static final String BATTERY_CHARGE_JSON = "{\"type\":\"BatteryCharge\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"plugged\":"+ConstantValues.isPlugged+"}";
    public static final String WIFI_JSON = "{\"type\":\"SvcWifi\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"enabled\":"+ConstantValues.wifiEnable+"}";
    public static final String DATA_JSON = "{\"type\":\"SvcData\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"enabled\":"+ConstantValues.dataEnable+"}";
    public static final String ORIENTATION_JSON = "{\"type\":\"SetOrientation\",\"delay\":\"0\",\"sessionToken\":\""+ConstantValues.sessionToken+"\",\"orientation\":"+ConstantValues.orientation_90+"}";

}
