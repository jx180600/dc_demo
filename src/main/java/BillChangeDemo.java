import bean.BoxPremiumInfoBean;
import bean.ChangeBillDetailBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.BaseConstants;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

/**
 * 安通改单demo
 * 以测试环境为例（生产环境替换anTongUrl即可）
 *
 * @author anTong
 */
public class BillChangeDemo {

    /**
     * 获取改单详情
     */
    public static JSONObject getBillChangeDetail(String token) {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, String> params = new HashMap<String, String>();
            // 运单号
            params.put("billNo", "APSOUKK240000081");
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_GET_CHANGE_BILL_DETAIL_URL)
                    // 将登录返回的token设置到请求头中
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .body(JSON.toJSONString(params))
                    .asString();
            System.out.println("获取改单详情接口调用返回信息：" + response.getBody());
            jsonObject = JSONObject.parseObject((String) response.getBody());
        } catch (Exception e) {
            // 异常处理 TODO
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 是否可以改单的校验
     */
    public static JSONObject whetherChangeBill(String token) {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, String> params = new HashMap<String, String>();
            // 运单号
            params.put("billNo", "APSOUKK240000081");
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_WHETHER_CHANGE_BILL_URL)
                    // 将登录返回的token设置到请求头中
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .body(JSON.toJSONString(params))
                    .asString();
            System.out.println("是否可以改单的校验接口调用返回信息：" + response.getBody());
            jsonObject = JSONObject.parseObject((String) response.getBody());
        } catch (Exception e) {
            // 异常处理 TODO
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 改单提交
     */
    public static JSONObject applySubmit(String token) {
        JSONObject jsonObject = null;
        try {
            // 此处为安通参数 可用实体类替代
            Map<String, Object> params = new HashMap<String, Object>();
            // 运单号 必填
            params.put("billNo", "APSOUKK240000081");
            // 变更原因名称，通过改单原因列表接口（/api/billChange/v2/listChangeReason）获取 必填
            params.put("changeReasonName", "代理原因-更改港口");
            // 变更原因备注
            params.put("changeRemark", "改单测试");
            // 目的港名称 必填
            params.put("endPortName", "广澳");
            // 是否箱内签收单（传0、1），0 否；1 是；默认0
            params.put("signInBox", "0");
            // 运输条款名称 必填
            params.put("forwardingClause", "CY/CY");
            // 是否指定目的港车队 0：否；1：是；默认0
            params.put("chkEndTruckSup", null);
            // 目的港指定车队名称，目的港运输条款为DOOR可指定目的港车队，获取目的港置指定车队（/api/booking/v2/getDestinationFleet）返回的supplierName字段
            params.put("endTruckSup", null);
            // 起始港运输类型，条款为DOOR/*必填。0 普通；1 双拖；2 短板车；3 自卸车；4 平板车；
            params.put("startTruckType", null);
            // 目的港运输类型，条款为*/DOOR必填。0 普通；1 双拖；2 短板车；3 自卸车；4 平板车；
            params.put("endTruckType", null);
            // 是否海铁联运，通过海铁路径列表接口（/api/billChange/v2/listSeaRailwayPath）获取，有值时，可修改（0：否；1：是；默认0）
            params.put("seaRailwayPath", null);
            // 海铁联运路径（是否海铁联运为是时，海铁路径字段必填）
            params.put("seaRailwayPath", null);
            // 是否压年（传0、1），0：否，1：是；默认0
            params.put("pressureYears", "0");
            // 运单备注
            params.put("description", null);
            // 货物名称 必填
            params.put("goodsName", "玉米");
            // 拼箱货物名称 -- 货物名称为“拼箱”时，拼箱货物必填，多个用英文逗号隔开
            params.put("entryGoodsNames", null);
            // 包装类型名称 必填
            params.put("packingTypeName", "吨袋");
            // 毛重 1、单据上的boxNum包含“20”，则20尺寸毛重范围值为0~28000之间！ 2、单据上的oxNum包含“40”或“*45”，则40尺寸或者45尺寸毛重范围值为0~26000之间！ 必填
            params.put("roughWeight", 26000);
            // 是否按吨计费（吨）（单箱货物重量）（0：否；1：是；默认0）
            params.put("billingByTon", "0");
            // 货物重量（是否按吨计费为是时，必填，默认0）
            params.put("cellPhoneNumber", null);
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
            // 是否冷藏品订舱（冷藏品箱型为RH）（传0、1），0 否；1 是；默认0；
            params.put("coldStorage", "0");
            // 拖车是否插电（传0、1），0 否；1 是；冷藏品必填
            params.put("isPlugin", null);
            // 是否预冷（传0、1），0 否；1 是；冷藏品必填
            params.put("preCool", null);
            // PIT_OK（传0、1），0 否；1 是；冷藏品必填
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
            // 是否疑似危险品，0 否；1 是；根据货物信息列表接口（/api/baseData/v2/getGoodsList）的isLikeDangerous字段判断，为是（1）时，该字段为1并且疑是危险品文件字段（likeDangerousFile）必填；
            params.put("isLikeDangerous", "0");
            // 是否上传附件（传0、1）；0：否；1：是；根据货物信息列表接口（/api/baseData/v2/getGoodsList）的isUploadAttachment字段判断，为是（1）时，该字段为1并且附件字段（attachment）必填
            params.put("isUploadAttachment", "0");
            // 理赔电话 必填
            params.put("cellPhoneNumber", "13395039213");
            // 保险声明价值；必填；不得低于计算保险费接口（/api/booking/v2/calculationPremium）返回的货物最低保险声明价值
            params.put("declaredValue", 7);
            // 是否购买航次箱体保险（传0、1），0：否，1：是；默认0
            params.put("isBuyBoxInsurance", "1");
            // 箱体保险配置编号；是否购买航次箱体保险为是时，该字段必填；获取航次箱体保险配置信息接口（/api/booking/v2/listBoxPremiumInfo）返回的boxPremiumNo字段
            params.put("boxPremiumConfigNo", "hcxtx09");
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
            HttpResponse response = Unirest.post(BaseConstants.AN_TONG_BASE_URL + BaseConstants.AN_TONG_APPLY_SUBMIT_URL)
                    // 将登录返回的token设置到请求头中
                    .header("token", token)
                    // 设置连接超时时间（单位毫秒）
                    .connectTimeout(60000)
                    // 设置读取超时时间（单位毫秒）
                    .socketTimeout(60000)
                    .fields(params)
                    // 文件设置 按实际场景设值
//                    // 附件，支持多附件上传
//                    .field("isUploadAttachmentFileInp", new ArrayList<File>())
//                    // 冷藏品特别说明，单附件上传，冷藏品必填
//                    .field("guarantee", File)
//                    // 危险品说明书，单附件上传，危险品必填
//                    .field("dangerExplain", File)
//                    // 危险品货物申报单，单附件上传，危险品必填
//                    .field("dangerDeclare", File)
//                    // 疑是危险品文件上传；多附件上传，货物为疑似危险品时必填
//                    .field("isLikeDangerousFileInp", new ArrayList<File>())
                    .asString();
            System.out.println("改单提交接口调用返回信息：" + response.getBody());
            jsonObject = JSONObject.parseObject((String) response.getBody());
        } catch (Exception e) {
            // 异常处理 TODO
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        // 改单流程demo；必须调用的接口为"运单改单 - 是否可以改单的校验"、"运单改单 - 获取改单详情"、"获取航次箱体保险配置信息"、"改单提交"接口
        // 1、登录获取token
        JSONObject loginObject = LoginDemo.login();
        boolean loginFlag = loginObject.getBoolean("responseOk");
        if (!loginFlag) {
            // 登录失败处理 TODO
            return;
        }
        String token = loginObject.getString("result");
        // 2、获取改单详情
        JSONObject billChangeDetailObject = BillChangeDemo.getBillChangeDetail(token);
        ChangeBillDetailBean changeBillDetailBean = billChangeDetailObject.getJSONObject("result").getJSONObject("detail").toJavaObject(ChangeBillDetailBean.class);
        // 3、根据实际情况获取航次箱体保险配置信息，确认是否需要强制购买航次箱体保险（改单时，已出运、空箱、soc均不可购买航次箱体保险）
        JSONObject listBoxPremiumInfoObject = BoxPremiumInfoDemo.listBoxPremiumInfo(token, changeBillDetailBean.getLadingTime());
        boolean listBoxPremiumInfoFlag = listBoxPremiumInfoObject.getBoolean("responseOk");
        if (!listBoxPremiumInfoFlag) {
            // 获取航次箱体保险配置信息失败处理 TODO
            return;
        }
        BoxPremiumInfoBean boxPremiumInfoBean = listBoxPremiumInfoObject.getJSONObject("result").toJavaObject(BoxPremiumInfoBean.class);
        // 如果是否强制购买航次箱体保险为true，则必须传入要购买的航次箱体保险编号，否则根据实际情况填写
        String boxPremiumNo = null;
        if (boxPremiumInfoBean.getCompulsoryBuy()) {
            // 必须购买的情况则获取对应的航次箱体保险编号，填入改单参数当中（模拟取第一条进行购买）
            boxPremiumNo = boxPremiumInfoBean.getData().get(0).getBoxPremiumNo();
        }
        // 4、校验是否可以改单（初次改单时调用）
        JSONObject whetherChangeBillObject = BillChangeDemo.whetherChangeBill(token);
        boolean whetherChangeBillFlag = whetherChangeBillObject.getBoolean("responseOk");
        if (!whetherChangeBillFlag) {
            // 不可改单处理 TODO
            return;
        }
        // 5、改单提交
        JSONObject applySubmitObject = BillChangeDemo.applySubmit(token);
        boolean applySubmitFlag = applySubmitObject.getBoolean("responseOk");
        if (!applySubmitFlag) {
            // 改单提交失败处理 TODO
            return;
        }
        // 改单成功
    }
}
