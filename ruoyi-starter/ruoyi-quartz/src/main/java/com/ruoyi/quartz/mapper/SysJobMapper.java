package com.ruoyi.quartz.mapper;

import java.util.List;

import com.ruoyi.quartz.domain.entity.SysJobEntity;
import com.ruoyi.quartz.domain.query.SysJobQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调度任务信息 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysJobMapper {
    /**
     * 查询调度任务日志集合
     *
     * @param query 查询信息
     * @return 操作日志集合
     */
    List<SysJobEntity> selectJobList(SysJobQuery query);

    /**
     * 查询所有调度任务
     *
     * @return 调度任务列表
     */
    List<SysJobEntity> selectJobAll();

    /**
     * 通过调度ID查询调度任务信息
     *
     * @param jobId 调度ID
     * @return 角色对象信息
     */
    SysJobEntity selectJobById(String jobId);

    /**
     * 通过调度ID删除调度任务信息
     *
     * @param jobId 调度ID
     * @return 结果
     */
    int deleteJobById(String jobId);

    /**
     * 批量删除调度任务信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteJobByIds(String[] ids);

    /**
     * 修改调度任务信息
     *
     * @param job 调度任务信息
     * @return 结果
     */
    int updateJob(SysJobEntity job);

    /**
     * 新增调度任务信息
     *
     * @param job 调度任务信息
     * @return 结果
     */
    int insertJob(SysJobEntity job);
}
