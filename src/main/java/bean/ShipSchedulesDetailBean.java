package bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 船程信息
 *
 * @author anTong
 */
@Data
public class ShipSchedulesDetailBean implements Serializable {

    /**
     * 船期名称
     */
    private String shipSchedulesName;
    /**
     * 航线路径
     */
    private String flowPath;
    /**
     * 预计航行时间（天）
     */
    private BigDecimal preTime;
    /**
     * 预计开航时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date etd;
    /**
     * 预计抵港时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endPortEta;
    /**
     * 起始港
     */
    private String startPortName;
    /**
     * 目的港
     */
    private String endPortName;
    /**
     * 干支线
     */
    private String mainBranchLines;
    /**
     * 驳船班次
     */
    private String bargesChedules;
}
