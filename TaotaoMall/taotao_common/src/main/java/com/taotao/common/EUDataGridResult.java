package com.taotao.common;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-10-06-14:51
 */
public class EUDataGridResult {
    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }


}
