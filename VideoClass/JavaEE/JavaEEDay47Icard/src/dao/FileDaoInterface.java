package dao;

/**
 * @author GJXAIOU
 * @create 2019-08-14-18:58
 */

import pojo.IdInfo;

import java.util.List;

/**
 * 和数据库相关的思路：
 * 1.思考创建数据库和表以及表的结构
 * 1.1 需要几张表，每张表的字段  -> 这里需要1张表，表字段为4个，分别为：id, front, back, idnumber
 * 1.2 字段类型  -> 这里id为数字，其余均为字符
 * 1.3 为字段添加约束、默认值、长度等等
 *
 * 2. 思考需求中需要哪些功能
 * 2.1 文件保存需要插入数据： insert into idcardinfo values(null, f, b,num)
 * 2.2 文件查询：select * from idcardinfo
 *
 */
public  interface FileDaoInterface {

    /**
     * 用于插入数据
     * @param idInfo IdInfo对象
     * @return 是否插入成功
     */
    boolean insert(IdInfo idInfo);

    /**
     * 用于查询方法
     * @return IdInfo对象的列表
     */
    List<IdInfo> getAll();


}
