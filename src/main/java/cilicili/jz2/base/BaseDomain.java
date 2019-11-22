package cilicili.jz2.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @Date:2019/11/22
 * @Author:lc
 */
public class BaseDomain implements Serializable {

    private Date createDate;

    private Date updateDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
