import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.BaseConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

/**
 * 安通登录demo
 * 以测试环境为例（生产环境替换anTongUrl即可）
 *
 * @author anTong
 */
public class LoginDemo {

    /**
     * 登录
     */
    public static JSONObject login() {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, String> params = new HashMap<String, String>();
            params.put("appkey", BaseConstants.AN_TONG_APP_KEY);
            params.put("appsecret", BaseConstants.AN_TONG_APP_SECRET);
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_LOGIN_URL)
                    .header("Content-Type", "application/json")
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .body(JSON.toJSONString(params))
                    .asString();
            System.out.println("登录接口调用返回信息：" + response.getBody());
            jsonObject = JSONObject.parseObject((String) response.getBody());
        } catch (Exception e) {
            // 异常处理 TODO
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        // 1、登录获取token
        JSONObject loginObject = LoginDemo.login();
        boolean loginFlag = loginObject.getBoolean("responseOk");
        if (!loginFlag) {
            // 登录失败处理 TODO
            return;
        }
        String token = loginObject.getString("result");
        System.out.println("获取到的token为：" + token);
    }
}
