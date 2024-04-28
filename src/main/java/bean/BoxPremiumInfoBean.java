package bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 航次箱体保险配置信息
 *
 * @author anTong
 */
@Data
public class BoxPremiumInfoBean implements Serializable {

    private static final long serialVersionUID = -8019497150785212783L;

    /**
     * 是否强制购买航次箱体保险 true/false
     */
    private Boolean compulsoryBuy;

    /**
     * 航次箱体保险配置明细
     */
    private List<BoxPremiumInfoDetailBean> data;
}
