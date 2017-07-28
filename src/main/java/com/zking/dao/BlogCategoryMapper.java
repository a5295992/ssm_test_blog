package com.zking.dao;

import com.zking.entity.BlogCategory;
import com.zking.entity.BlogCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    long countByExample(BlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int deleteByExample(BlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int insert(BlogCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int insertSelective(BlogCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    List<BlogCategory> selectByExample(BlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    BlogCategory selectByPrimaryKey(Integer categoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int updateByExampleSelective(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int updateByExample(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int updateByPrimaryKeySelective(BlogCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_blog_category
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    int updateByPrimaryKey(BlogCategory record);
}