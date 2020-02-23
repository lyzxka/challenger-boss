package com.rancii.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.VO.TableField;
import com.rancii.entity.VO.TableVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hantw on 2017/12/25.
 * todo: 数据库操作表
 */
@Repository("tableDao")
public interface TableDao {

    List<TableVO> listAll(String database);

    List<TableVO> listPage(Page<TableVO> page, @Param(value = "name") String name, @Param(value = "database") String database);

    Integer selectTableCount(String database);

    Integer existTable(Map<String, String> params);

    Integer existTableField(Map<String, Object> map);

    void creatTable(Map<String, Object> map);

    void addColumn(Map<String, Object> map);

    void updateColumnSameName(Map<String, Object> map);

    void updateColumnDiffName(Map<String, Object> map);

    void dropTable(@Param("tableName") String tableName);

    void dropTableField(Map<String, Object> map);

    List<TableField> selectFields(Map<String, Object> map);

    TableVO selectDetailTable(Map<String, String> map);

    List<TableField> selectFields(Page<TableField> objectPage, Map<String, Object> map);

    void changeTableName(Map<String, Object> map);

    void changeTableComment(Map<String, Object> map);
}
