package com.zking.entity;

public class BlogData {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog_data.data_id
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    private Integer dataId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog_data.copy_from
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    private String copyFrom;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_blog_data.content
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog_data.data_id
     *
     * @return the value of t_blog_data.data_id
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    public Integer getDataId() {
        return dataId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog_data.data_id
     *
     * @param dataId the value for t_blog_data.data_id
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog_data.copy_from
     *
     * @return the value of t_blog_data.copy_from
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    public String getCopyFrom() {
        return copyFrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog_data.copy_from
     *
     * @param copyFrom the value for t_blog_data.copy_from
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    public void setCopyFrom(String copyFrom) {
        this.copyFrom = copyFrom == null ? null : copyFrom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_blog_data.content
     *
     * @return the value of t_blog_data.content
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_blog_data.content
     *
     * @param content the value for t_blog_data.content
     *
     * @mbg.generated Fri Jul 28 19:44:21 CST 2017
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}