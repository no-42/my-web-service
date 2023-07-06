package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.BaseMapper;
import com.ruoyi.system.domain.entity.SysPostEntity;
import com.ruoyi.system.domain.query.SysPostQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 岗位信息 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPostEntity> {
    /**
     * 查询岗位数据集合
     *
     * @param query 查询数据
     * @return 岗位数据集合
     */
    List<SysPostEntity> selectPostList(SysPostQuery query);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPostEntity> selectPostAll();

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    SysPostEntity selectPostById(String postId);

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    List<String> selectPostListByUserId(String userId);

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    List<SysPostEntity> selectPostsByUserName(String userName);

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    int deletePostById(String postId);

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    int deletePostByIds(String[] postIds);

    /**
     * 修改岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int updatePost(SysPostEntity post);

    /**
     * 新增岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int insertPost(SysPostEntity post);

    /**
     * 校验岗位名称
     *
     * @param postName 岗位名称
     * @return 结果
     */
    SysPostEntity checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     *
     * @param postCode 岗位编码
     * @return 结果
     */
    SysPostEntity checkPostCodeUnique(String postCode);
}
