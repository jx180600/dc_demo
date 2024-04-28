package bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 各箱型尺寸运价
 *
 * @author anTong
 */
@Data
public class PricesDetailsBean implements Serializable {

    private static final long serialVersionUID = 8069521867703798097L;

    /**
     * 历史价格
     */
    private BigDecimal hisPrice;
    /**
     * 尺寸箱型
     */
    private String sizeType;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 箱型
     */
    private String boxType;
    /**
     * 尺寸
     */
    private String boxSize;
    /**
     * 运价信息id
     */
    private String freightMfeId;
}
