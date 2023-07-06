package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.entity.SysOperLogEntity;
import com.ruoyi.system.domain.query.SysOperLogQuery;

/**
 * 操作日志 服务层
 *
 * @author ruoyi
 */
public interface ISysOperLogService {
    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLogEntity operLog);

    /**
     * 查询系统操作日志集合
     *
     * @param query 查询条件
     * @return 操作日志集合
     */
    List<SysOperLogEntity> selectOperLogList(SysOperLogQuery query);

    /**
     * 批量删除系统操作日志
     *
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    int deleteOperLogByIds(String[] operIds);

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    SysOperLogEntity selectOperLogById(String operId);

    /**
     * 清空操作日志
     */
    void cleanOperLog();
}
