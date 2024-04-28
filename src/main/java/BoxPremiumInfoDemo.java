import bean.BoxPremiumInfoBean;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.BaseConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

/**
 * 安通获取航次箱体保险配置信息demo
 * 以测试环境为例（生产环境替换anTongUrl即可）
 *
 * @author anTong
 */
public class BoxPremiumInfoDemo {

    /**
     * 获取航次箱体保险配置信息
     */
    public static JSONObject listBoxPremiumInfo(String token, String bookingTime) {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, String> params = new HashMap<String, String>();
            // 尺寸 必填
            params.put("boxSize", "20");
            // 货物名称 必填
            params.put("goodsName", "玉米");
            // 订舱时间 必填；yyyy-MM-dd格式；订舱保存、提交、计费时，取当前时间；其余情况取单据的订舱时间；
            params.put("bookingTime", bookingTime);
            // 箱型 必填
            params.put("boxType", "GP");
            // 是否soc 0 否；1 是；无特殊情况传0；
            params.put("soc", "0");
            // 空重 无特殊情况传F；
            params.put("emptyFull", "F");
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_BOX_PREMIUM_URL)
                    // 将登录返回的token设置到请求头中
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .body(JSON.toJSONString(params))
                    .asString();
            System.out.println("获取航次箱体保险配置信息接口调用返回信息：" + response.getBody());
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
        // 3、根据实际情况获取航次箱体保险配置信息，确认是否需要强制购买航次箱体保险
        JSONObject listBoxPremiumInfoObject = BoxPremiumInfoDemo.listBoxPremiumInfo(token, DateUtil.today());
        boolean listBoxPremiumInfoFlag = listBoxPremiumInfoObject.getBoolean("responseOk");
        if (!listBoxPremiumInfoFlag) {
            // 获取航次箱体保险配置信息失败处理 TODO
            return;
        }
        BoxPremiumInfoBean boxPremiumInfoBean = listBoxPremiumInfoObject.getJSONObject("result").toJavaObject(BoxPremiumInfoBean.class);
        // 航次箱体保险配置信息获取成功
    }
}
