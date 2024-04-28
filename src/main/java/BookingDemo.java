import bean.BoxPremiumInfoBean;
import bean.ListPricesBean;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.BaseConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安通订舱demo
 * 以测试环境为例（生产环境替换anTongUrl即可）
 *
 * @author anTong
 */
public class BookingDemo {

    /**
     * 运价、船期查询
     */
    public static JSONObject listPrices(String token) {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, String> params = new HashMap<String, String>();
            // 起始港名称 运输条款为“港到港“或“港到门“时必填
            params.put("startPortName", "上海");
            // 目的港名称 运输条款为“港到港“或“门到港“时必填
            params.put("endPortName", "广澳");
            // 空重 F 重箱；E 空箱；如无特殊情况，默认传F；
            params.put("emptyFull", "F");
            // 应付类型 0 客户应付；1 全程应付；如无特殊情况，默认传0；
            params.put("isQueryEndPortFee", "0");
            // 发货省份名称 运输条款为“门到港”或“门到门”时必填
            params.put("sedProvinceName", null);
            // 发货城市名称 运输条款为“门到港”或“门到门”时必填
            params.put("sedCityName", null);
            // 发货区县名称 运输条款为“门到港”或“门到门”时必填
            params.put("sedRegionName", null);
            // 发货街道名称 运输条款为“门到港”或“门到门”时选填，填值时，匹配出的运价越准确
            params.put("sedStreetName", null);
            // 发货区域范围名称 运输条款为“门到港”或“门到门”时选填，填值时，匹配出的运价越准确
            params.put("sedStreetRangeName", null);
            // 收货省份名称 运输条款为“港到门”或“门到门”时必填
            params.put("recProvinceName", null);
            // 收货城市名称 运输条款为“港到门”或“门到门”时必填
            params.put("recCityName", null);
            // 收货区县名称 运输条款为“港到门”或“门到门”时必填
            params.put("recRegionName", null);
            // 收货街道名称 运输条款为“港到门”或“门到门”时选填，填值时，匹配出的运价越准确
            params.put("recStreetName", null);
            // 收货区域范围名称 运输条款为“港到门”或“门到门”时选填，填值时，匹配出的运价越准确
            params.put("recStreetRangeName", null);
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_PRICES_URL)
                    // 将登录返回的token设置到请求头中
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .body(JSON.toJSONString(params))
                    .asString();
            System.out.println("运价、船期查询接口调用返回信息：" + response.getBody());
            jsonObject = JSONObject.parseObject((String) response.getBody());
        } catch (Exception e) {
            // 异常处理 TODO
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 订舱提交
     */
    public static JSONObject submitBooking(String token) {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, Object> params = new HashMap<String, Object>();
            // 订单号保存后生成，非必填，但保存与提交时如果有订单号需要传入，否则默认生成新订单。
            params.put("bookingNo", null);
            // 起始港名称，运价、船期查询（/api/freight/v2/listApiPrices）返回的startPortName 必填
            params.put("startPortName", "上海");
            // 目的港名称，运价、船期查询（/api/freight/v2/listApiPrices）返回的endPortName 必填
            params.put("endPortName", "广澳");
            // 船期ID，运价、船期查询（/api/freight/v2/listApiPrices）返回的shipSchedulesId，如果接口返回的shipSchedulesId字段为空，则该字段可放空
            params.put("shipSchedulesId", "vjAAAcCAHYCCq9xE");
            // 航线路径，运价、船期查询（/api/freight/v2/listApiPrices）返回的flowPath
            params.put("flowPath", "上海-广澳");
            // 运输条款名称 必填
            params.put("forwardingClause", "CY/CY");
            // 目的港指定车队名称，目的港运输条款为DOOR可指定目的港车队，获取目的港置指定车队（/api/booking/v2/getDestinationFleet）返回的supplierName字段
            params.put("endTruckSup", null);
            // 起始港运输类型，条款为DOOR/*必填。0 普通；1 双拖；2 短板车；3 自卸车；4 平板车；
            params.put("startTruckType", null);
            // 目的港运输类型，条款为*/DOOR必填。0 普通；1 双拖；2 短板车；3 自卸车；4 平板车；
            params.put("endTruckType", null);
            // 海铁联运路径，运价、船期查询（/api/freight/v2/listApiPrices）返回的返回的seaRailwayPath，如果接口返回的seaRailwayPath字段为空，则该字段可放空
            params.put("seaRailwayPath", null);
            // 货物名称，为拼箱时，拼箱货物名称（goodsNames）必填
            params.put("goodsName", "玉米");
            // 拼箱货物名称
            params.put("goodsNames", null);
            // 是否上传附件（传0、1）；0：否；1：是；根据货物信息列表接口（/api/baseData/v2/getGoodsList）的isUploadAttachment字段判断，为是（1）时，该字段为1并且附件字段（attachment）必填
            params.put("isUploadAttachment", "0");
            // 包装类型名称 必填
            params.put("packingType", "吨袋");
            // 是否箱内签收单（传0、1），0 否；1 是；默认0
            params.put("signInBox", "0");
            // 毛重 必填
            params.put("roughWeight", 26000);
            // 理赔电话 必填
            params.put("cellPhoneNumber", "13395039213");
            // 订单备注
            params.put("description", null);
            // 保险声明价值；必填；不得低于计算保险费接口（/api/booking/v2/calculationPremium）返回的货物最低保险声明价值
            params.put("declaredValue", 7);
            // 是否购买航次箱体保险（传0、1），0：否，1：是；默认0
            params.put("buyBoxInsurance", "1");
            // 箱体保险配置编号；是否购买航次箱体保险为是时，该字段必填；获取航次箱体保险配置信息接口（/api/booking/v2/listBoxPremiumInfo）返回的boxPremiumNo字段
            params.put("boxPremiumConfigNo", "hcxtx09");
            // 优惠劵抵扣（传0、1），0：否，1：是；默认0；为1时，优惠券编号（couponNo）必填
            params.put("isCouponDeduct", "0");
            // 优惠劵编号
            params.put("couponNo", null);
            // 积分抵扣（传0、1），0：否，1：是；默认0
            params.put("isPointsDeduct", "0");
            // 是否压年（传0、1），0：否，1：是；默认0
            params.put("pressureYears", "0");
            // 箱类型 必填
            params.put("boxType", "GP");
            // 空重（传E、F），空箱 E；重箱 F；无特殊情况传F；
            params.put("emptyFull", "F");
            // 是否SOC箱（传0、1），0：否，1：是；无特殊情况传0；
            params.put("isSoc", "0");
            // 箱尺寸 必填
            params.put("boxSize", "20");
            // 订舱箱个数 必填
            params.put("count", 3);
            // 单箱运价，运价、船期查询（/api/freight/v2/listApiPrices）返回的freightDetails分录里的price字段；必填
            params.put("price", 2530);
            // 出口港起指定车队箱量
            params.put("boxNum", null);
            // 出口港起指定车队名称，获取出口办单授权车队接口（/api/booking/v2/getExitFleetList）返回的supplierName字段
            params.put("supplierName", null);
            // 发货人公司名称
            params.put("sedCompany", "泉州安通物流有限公司");
            // 发货人名称，请填写真实姓名，勿使用'先生','小姐'之类的称呼。地址不可出现单引号。
            params.put("sedLinkman", "yyy");
            // 发货人手机号码
            params.put("sedMobile", "13395039213");
            // 发货省份名称，运输条款为DOOR/*（门到）的，该字段必填
            params.put("sedProvinceName", null);
            // 发货城市名称，运输条款为DOOR/*（门到）的，该字段必填
            params.put("sedCityName", null);
            // 发货区县名称，运输条款为DOOR/*（门到）的，该字段必填
            params.put("sedRegionName", null);
            // 发货街道名称，运输条款为DOOR/*（门到）的，该字段必填
            params.put("sedStreetName", null);
            // 发货区域范围名称
            params.put("sedStreetRangeName", null);
            // 发货详细地址
            params.put("sedDetailAddress", null);
            // 收货人公司名称；运输条款为*/CY（到港）的，公司必须在安通系统有备案；运输条款*/DOOR（到门）的，如收货人为个人，公司须与收货人名称一致
            params.put("recCompany", "广州市广盛运输有限公司");
            // 收货人名称，请填写真实姓名，勿使用'先生','小姐'之类的称呼。地址不可出现单引号。
            params.put("recLinkman", "yyy");
            // 收货人手机号码
            params.put("recMobile", "13395039213");
            // 收货省份名称，运输条款为*/DOOR（到门）的，该字段必填
            params.put("recProvinceName", null);
            // 收货城市名称，运输条款为*/DOOR（到门）的，该字段必填
            params.put("recCityName", null);
            // 收货区县名称，运输条款为*/DOOR（到门）的，该字段必填
            params.put("recRegionName", null);
            // 收货街道名称，运输条款为*/DOOR（到门）的，该字段必填
            params.put("recStreetName", null);
            // 收货区域范围名称
            params.put("recStreetRangeName", null);
            // 收货详细地址
            params.put("recDetailAddress", null);
            // 是否冷藏品订舱（冷藏品箱型为RH）（传0、1），0 否；1 是；默认0；
            params.put("isColdStorage", "0");
            // 拖车是否插电（传0、1），0 否；1 是；
            params.put("isPlugin", null);
            // 是否预冷（传0、1），0 否；1 是；
            params.put("preCool", null);
            // PIT_OK（传0、1），0 否；1 是；
            params.put("ptiOk", null);
            // 冷藏温度类型，冷藏品必填（传1、2）：1 零上；2 零下；
            params.put("temperatureType", null);
            // 冷藏品温度单位，冷藏品必填（传1fahrenheit、2Centigrade ）：2Centigrade 摄氏度；1fahrenheit 华氏度；
            params.put("temperatureHigh", null);
            // 冷藏品通风度，冷藏品必填
            params.put("ventilation", null);
            // 冷藏品拖车插电类型，冷藏品必填（传1、2、3）：1 全程插电；2 起运港插电；3 目的港插电；
            params.put("pluginType", null);
            // 冷藏品码头插电类型，冷藏品必填（传1、2、3）：1 码头全程插电；2 起运港码头不插电；3 目的港码头不插电；
            params.put("dockPluginType", null);
            // 是否危险品，0 否；1 是；根据货物信息列表接口（/api/baseData/v2/getGoodsList）的isDangerous字段判断，为是（1）时，该字段为1；
            params.put("isDangerous", "0");
            // 危险品IMDG，危险品必填
            params.put("imdg", null);
            // 危险品类别，危险品必填
            params.put("dangerType", null);
            // 联合国编号，危险品必填
            params.put("unNumber", null);
            // 危险品必特别说明
            params.put("dangerRemark", null);
            // 是否疑似危险品，0 否；1 是；根据货物信息列表接口（/api/baseData/v2/getGoodsList）的isLikeDangerous字段判断，为是（1）时，该字段为1并且疑是危险品文件字段（likeDangerousFile）必填；
            params.put("isLikeDangerous", "0");
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_SUBMIT_BOOKING_URL)
                    // 将登录返回的token设置到请求头中
                    .header("token", token)
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .fields(params)
                    // 文件设置 按实际场景设值
//                    // 附件，支持多附件上传
//                    .field("attachment", new ArrayList<File>())
//                    // 冷藏品特别说明，单附件上传，冷藏品必填
//                    .field("guarantee", File)
//                    // 危险品说明书，单附件上传，危险品必填
//                    .field("dangerExplain", File)
//                    // 危险品货物申报单，单附件上传，危险品必填
//                    .field("dangerDeclare", File)
//                    // 疑是危险品文件上传；多附件上传，货物为疑似危险品时必填
//                    .field("likeDangerousFile", new ArrayList<File>())
                    .asString();
            System.out.println("订舱提交接口调用返回信息：" + response.getBody());
            jsonObject = JSONObject.parseObject((String) response.getBody());
        } catch (Exception e) {
            // 异常处理 TODO
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        // 订舱流程demo；完成订舱单据提交，必须调用的接口为"运价、船期查询"、"获取航次箱体保险配置信息"、"订舱提交"接口
        // 1、登录获取token
        JSONObject loginObject = LoginDemo.login();
        boolean loginFlag = loginObject.getBoolean("responseOk");
        if (!loginFlag) {
            // 登录失败处理 TODO
            return;
        }
        String token = loginObject.getString("result");
        // 2、运价、船期查询
        JSONObject listPricesObject = BookingDemo.listPrices(token);
        boolean listPricesFlag = listPricesObject.getBoolean("responseOk");
        if (!listPricesFlag) {
            // 运价、船期查询失败处理 TODO
            return;
        }
        // 模拟取返回数据的第一条进行订舱
        List<ListPricesBean> listPricesResult = listPricesObject.getJSONArray("result").toJavaList(ListPricesBean.class);
        ListPricesBean pricesBean = listPricesResult.get(0);
        // 3、根据实际情况获取航次箱体保险配置信息，确认是否需要强制购买航次箱体保险
        JSONObject listBoxPremiumInfoObject = BoxPremiumInfoDemo.listBoxPremiumInfo(token, DateUtil.today());
        boolean listBoxPremiumInfoFlag = listBoxPremiumInfoObject.getBoolean("responseOk");
        if (!listBoxPremiumInfoFlag) {
            // 获取航次箱体保险配置信息失败处理 TODO
            return;
        }
        BoxPremiumInfoBean boxPremiumInfoBean = listBoxPremiumInfoObject.getJSONObject("result").toJavaObject(BoxPremiumInfoBean.class);
        // 如果是否强制购买航次箱体保险为true，则必须传入要购买的航次箱体保险编号，否则根据实际情况填写
        String boxPremiumNo = null;
        if (boxPremiumInfoBean.getCompulsoryBuy()) {
            // 必须购买的情况则获取对应的航次箱体保险编号，填入订舱参数当中（模拟取第一条进行购买）
            boxPremiumNo = boxPremiumInfoBean.getData().get(0).getBoxPremiumNo();
        }
        // 4、订舱提交
        JSONObject submitBookingObject = BookingDemo.submitBooking(token);
        boolean submitBookingFlag = submitBookingObject.getBoolean("responseOk");
        if (!submitBookingFlag) {
            // 订舱提交失败处理 TODO
            return;
        }
        // 订舱成功
    }
}
