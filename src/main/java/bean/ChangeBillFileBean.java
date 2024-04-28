package bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 附件信息
 *
 * @author anTong
 */
@Data
public class ChangeBillFileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件真实名称
     */
    private String realFileName;
    /**
     * 安通文件名称 - 删除使用
     */
    private String anTongFileName;

    public ChangeBillFileBean(String realName, String anTongName) {
        this.realFileName = realName;
        this.anTongFileName = anTongName;
    }
}
