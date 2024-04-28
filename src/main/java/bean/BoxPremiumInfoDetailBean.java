package bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 航次箱体保险配置明细
 *
 * @author anTong
 */
@Data
public class BoxPremiumInfoDetailBean implements Serializable {

    private static final long serialVersionUID = -8019497150785212783L;

    /**
     * 航次箱体保险编号
     */
    private String boxPremiumNo;
    /**
     * 航次箱体保险名称
     */
    private String boxPremiumName;
    /**
     * 航次箱体保险描述
     */
    private String boxPremiumDescription;
    /**
     * 航次箱体保险应付保费金额
     */
    private BigDecimal amount;
}
