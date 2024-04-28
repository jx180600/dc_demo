package constant;

/**
 * 常量
 *
 * @author anTong
 */
public class BaseConstants {

    /**
     * 安通订舱测试环境URL
     */
    public static String AN_TONG_BASE_URL = "https://tapi.antong56.com:8443/antong-api/api-platform";

    /**
     * 安通订舱 - 登录URL
     */
    public static String AN_TONG_LOGIN_URL = "/api/v2/login";

    /**
     * 安通订舱 - 运价、船期查询
     */
    public static String AN_TONG_PRICES_URL = "/api/freight/v2/listApiPrices";

    /**
     * 安通订舱 - 获取航次箱体保险配置信息
     */
    public static String AN_TONG_BOX_PREMIUM_URL = "/api/booking/v2/listBoxPremiumInfo";

    /**
     * 安通订舱 - 订舱提交
     */
    public static String AN_TONG_SUBMIT_BOOKING_URL = "/api/booking/v2/submitBooking";

    /**
     * 安通订舱 - 获取改单详情
     */
    public static String AN_TONG_GET_CHANGE_BILL_DETAIL_URL = "/api/billChange/v2/getChangeBillDetail";

    /**
     * 安通订舱 - 是否可以改单的校验
     */
    public static String AN_TONG_WHETHER_CHANGE_BILL_URL = "/api/billChange/v2/whetherChangeBill";

    /**
     * 安通订舱 - 改单提交
     */
    public static String AN_TONG_APPLY_SUBMIT_URL = "/api/billChange/v2/applySubmit";

    /**
     * 安通订舱 - appKey
     */
    public static String AN_TONG_APP_KEY = "";

    /**
     * 安通订舱 - appSecret
     */
    public static String AN_TONG_APP_SECRET = "";
}
