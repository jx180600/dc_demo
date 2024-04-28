package bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 改单详情
 *
 * @author anTong
 */
@Data
public class ChangeBillDetailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运单号
     */
    private String billNo;
    /**
     * 箱量/尺寸/箱型
     */
    private String boxNum;
    /**
     * 起始港名称
     */
    private String startPortName;
    /**
     * 目的港名称
     */
    private String endPortName;
    /**
     * 箱内单/自备单 0：否；1：是
     */
    private String signInBox;
    /**
     * 运输条款名称
     */
    private String forwardingClause;
    /**
     * 起运港运输类型 DOOR/条款时必填（0 普通、1 双拖、2 短板车、3 自卸车；4 平板车；默认0）
     */
    private String startTruckType;
    /**
     * 目的港运输类型 /DOOR条款时必填（0 普通、1 双拖、2 短板车、3 自卸车；4 平板车；默认0）
     */
    private String endTruckType;
    /**
     * 是否指定目的港车队 0：否；1：是
     */
    private String chkEndTruckSup;
    /**
     * 目的港指定车队 是否指定目的港车队为是时，目的港指定车队字段必填
     */
    private String endTruckSup;
    /**
     * 是否海铁联运 0：否；1：是
     */
    private String seaRailway;
    /**
     * 海铁路径 是否海铁联运为是时，海铁路径字段必填
     */
    private String seaRailwayPath;
    /**
     * 是否压年 0：否；1：是
     */
    private String pressureYears;
    /**
     * 运单备注
     */
    private String description;
    /**
     * 货物名称
     */
    private String goodsName;
    /**
     * 货物类型名称
     */
    private String goodsTypeName;
    /**
     * 包装类型名称
     */
    private String packingTypeName;
    /**
     * 毛重 1、单据上的boxNum（箱型尺寸）包含“*20”，则20尺寸毛重范围值为0~28000之间！ 2、单据上的boxNum（箱型尺寸）包含“*40”或“*45”，则40尺寸或者45尺寸毛重范围值为0~26000之间！
     */
    private BigDecimal roughWeight;
    /**
     * 是否按吨计费（吨）（单箱货物重量） 0：否；1：是
     */
    private String billingByTon;
    /**
     * 货物重量 是否按吨计费为是时，必填，默认0
     */
    private BigDecimal weightOfGoods;
    /**
     * 是否危险品 0：否；1：是
     */
    private String dangerous;
    /**
     * IMDG 是否危险品为是时，IMDG必填
     */
    private String imdg;
    /**
     * 类别 是否危险品为是时，类别必填
     */
    private String dangerType;
    /**
     * 联合国编号 是否危险品为是时，联合国编号必填
     */
    private String unNumber;
    /**
     * 特别说明
     */
    private String dangerRemark;
    /**
     * 危险品说明书 - 附件信息
     */
    private ChangeBillFileBean dangerExplain;
    /**
     * 危险品货物申报单 - 附件信息
     */
    private ChangeBillFileBean dangerDeclare;
    /**
     * 是否冷藏 0：否；1：是
     */
    private String coldStorage;
    /**
     * 是否预冷 0：否；1：是；是否冷藏为是时，是否预冷必填；
     */
    private String preCool;
    /**
     * PTI_OK 0：否；1：是；是否冷藏为是时，PTI_OK必填；
     */
    private String ptiOk;
    /**
     * 拖车是否插电 0：否；1：是；是否冷藏为是时，拖车是否插电必填；
     */
    private String isPlugin;
    /**
     * 要求冷藏温度 1 零上；2 零下；是否冷藏为是时，要求冷藏温度必填；
     */
    private String temperatureType;
    /**
     * 要求冷藏温度数 是否冷藏为是时，要求冷藏温度数必填；
     */
    private BigDecimal temperatureHigh;
    /**
     * 要求冷藏温度类型 1fahrenheit 华氏度；2Centigrade 摄氏度；是否冷藏为是时，要求冷藏温度类型必填；
     */
    private String temperatureUnit;
    /**
     * 通风度 是否冷藏为是时，通风度必填
     */
    private BigDecimal ventilation;
    /**
     * 拖车插电类型 1 全程插电；2 起运港插电；3 目的港插电；拖车是否插电为是时，拖车插电类型必填；
     */
    private String pluginType;
    /**
     * 码头插电类型 1 码头全程插电；2 起运港码头不插电；3 目的港码头不插电；是否冷藏为是时，码头插电类型必填；
     */
    private String dockPluginType;
    /**
     * 忽略
     */
    private String coolRemark;
    /**
     * 冷藏品保函 - 附件信息 是否冷藏为是时，冷藏品保函必填；
     */
    private ChangeBillFileBean guarantee;
    /**
     * 是否疑是危品
     */
    private String isLikeDangerous;
    /**
     * 疑是危险品文件 - 附件信息 是否疑是危品为是时，疑是危险品文件必填；可多份；
     */
    private List<ChangeBillFileBean> isLikeDangerousFileInp;
    /**
     * 是否上传附件
     */
    private String isUploadAttachment;
    /**
     * 附件文件 - 附件信息 是否上传附件为是时，附件文件必填；可多份；
     */
    private List<ChangeBillFileBean> isUploadAttachmentFileInp;
    /**
     * 保险声明价值（万元）
     */
    private BigDecimal declaredValue;
    /**
     * 理赔通知电话
     */
    private String cellPhoneNumber;
    /**
     * 是否购买航次箱体保险 0：否；1：是
     */
    private String isBuyBoxInsurance;
    /**
     * 离港时间 yyyy-mm-dd hh24:mi:ss；离港时间有值时，不允许修改是否购买航次箱体保险；
     */
    private String atd;
    /**
     * 收货人公司名称；运输条款为/CY（到港）的，公司必须在安通系统有备案；运输条款/DOOR（到门）的，如收货人为个人，公司须与收货人名称一致
     */
    private String recCompany;
    /**
     * 收货人名称，请填写真实姓名，勿使用'先生','小姐'之类的称呼。地址不可出现单引号。
     */
    private String recLinkman;
    /**
     * 收货省份名称，运输条款为/DOOR（到门）的，该字段必填
     */
    private String recProvinceName;
    /**
     * 收货城市名称，运输条款为/DOOR（到门）的，该字段必填
     */
    private String recCityName;
    /**
     * 收货区县名称，运输条款为/DOOR（到门）的，该字段必填
     */
    private String recRegionName;
    /**
     * 收货街道名称，运输条款为/DOOR（到门）的，该字段必填
     */
    private String recStreetName;
    /**
     * 收货区域范围名称
     */
    private String recStreetRangeName;
    /**
     * 收货详细地址
     */
    private String recDetailAddress;
    /**
     * 收货人手机号码
     */
    private String recMobile;
    /**
     * 发货人公司名称
     */
    private String sedCompany;
    /**
     * 发货人名称，请填写真实姓名，勿使用'先生','小姐'之类的称呼。地址不可出现单引号。
     */
    private String sedLinkman;
    /**
     * 发货省份名称，运输条款为DOOR/（到门）的，该字段必填
     */
    private String sedProvinceName;
    /**
     * 发货城市名称，运输条款为DOOR/（到门）的，该字段必填
     */
    private String sedCityName;
    /**
     * 发货区县名称，运输条款为DOOR/（到门）的，该字段必填
     */
    private String sedRegionName;
    /**
     * 发货街道名称，运输条款为DOOR/（到门）的，该字段必填
     */
    private String sedStreetName;
    /**
     * 发货区域范围名称
     */
    private String sedStreetRangeName;
    /**
     * 发货详细地址
     */
    private String sedDetailAddress;
    /**
     * 发货人手机号码
     */
    private String sedMobile;
    /**
     * 箱体保险配置编号
     */
    private String boxPremiumConfigNo;
    /**
     * 订舱时间 yyyy-MM-dd
     */
    private String ladingTime;
    /**
     * 是否soc 0 否；1 是；
     */
    private String soc;
    /**
     * 空重 E 空箱；F 重箱；
     */
    private String emptyFull;
    /**
     * 尺寸
     */
    private String boxSize;
    /**
     * 箱型
     */
    private String boxType;
}
