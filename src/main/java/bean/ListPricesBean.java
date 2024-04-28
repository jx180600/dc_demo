package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 运价、船期
 *
 * @author anTong
 */
@Data
public class ListPricesBean implements Serializable {

    private static final long serialVersionUID = 8069521867703798097L;
    /**
     * 海铁路径
     */
    private String seaRailwayPath;
    /**
     * 起始港名称
     */
    private String startPortName;
    /**
     * 目的港名称
     */
    private String endPortName;
    /**
     * 运价生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;
    /**
     * 运价失效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;
    /**
     * 运价类型：1COMMON 正常；2SPECIAL 特价；3GROUP 团购；
     */
    private String priceType;
    /**
     * 每月计划开航次数(次)
     */
    private String frequency;
    /**
     * 空箱or重箱 E：空箱，F：重箱
     */
    private String emptyFull;
    /**
     * 币别编码
     */
    private String currencyCode;
    /**
     * 是否联运包干价
     */
    private BigDecimal isTransportPrice;
    /**
     * 船期ID 订单保存、提交、计费时使用
     */
    private String shipSchedulesId;
    /**
     * 船期名称
     */
    private String shipSchedulesName;
    /**
     * 船名（英文）
     */
    private String shipEnglishName;
    /**
     * 航线路径 订单保存、提交、计费时使用
     */
    private String flowPath;
    /**
     * 箱路径
     */
    private String boxPath;
    /**
     * 当前港口预计抵港时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date eta;
    /**
     * 当前港口预计开航时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date etd;
    /**
     * 目的港口预计抵港时间 yyyy-MM-dd HH:mm:ss
     */
    private String endPortEta;
    /**
     * 20尺标箱剩余量
     */
    private int stock20;
    /**
     * 40尺标箱剩余量
     */
    private int stock40;
    /**
     * 航次
     */
    private String voyage;
    /**
     * 干线路径
     */
    private String mainFlowPath;
    /**
     * 总耗时
     */
    private String totalVoyageTime;
    /**
     * 各箱型尺寸运价
     */
    private List<PricesDetailsBean> freightDetails;
    /**
     * 船程信息
     */
    private List<ShipSchedulesDetailBean> flowPathDetail;
}
