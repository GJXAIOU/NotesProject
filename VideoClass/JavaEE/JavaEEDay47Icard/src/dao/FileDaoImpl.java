package dao;

import pojo.IdInfo;
import utils.BaseDao;

import java.util.ArrayList;
import java.util.List;

/** 实现完成数据库操作
 * @author GJXAIOU
 * @create 2019-08-14-19:21
 */
public class FileDaoImpl extends BaseDao implements FileDaoInterface {

    @Override
    public boolean insert(IdInfo idInfo) {

        String sql = "insert into idcardinfo values(null, ?, ?, ?)";

        List list = new ArrayList<>();
        list.add(idInfo.getFront());
        list.add(idInfo.getBack());
        list.add(idInfo.getIdnumber());

        super.updateCurrent(sql, list.toArray());
        return false;
    }

    @Override
    public List<IdInfo> getAll() {

        String sql = "select * from idcardinfo";
        List<IdInfo> idInfos = super.inquiryCurrent(sql, null, IdInfo.class);
        return idInfos ;
    }
}
