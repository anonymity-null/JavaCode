package com.maki.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* 博客页面的javabean
* */
//对应数据库自动生成表结构
@Entity
//自动生成表
@Table(name="t_blog")
public class Blog {

    @Id                                             //指明该表的主键
    @GeneratedValue                                 //主键的生成策略
    private Long id;
    private String title;                           //标题
    private String content;                         //文本内容
    private String firstPicture;                    //首图
    private String flag;                            //原创/转载/翻译
    private Integer views;                          //浏览次数
    private boolean appreciation;                   //赞赏功能
    private boolean shareStatement;                 //转载声明
    private boolean commentabled;                   //评论功能
    private boolean published;                      //提交状态
    private boolean recommend;                      //是否推荐
    private String description;                     //博客描述

    @Transient                                      //该属性不与数据库进行交互
    private String tagIds;                          //该博客拥有的所有标签的id

    //指定生成的时间对应到数据库中的类型(使用全时间类型：日期+时间)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;                        //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;                        //更新时间

    //多个blog对应一个type（多方为关系的维护方，blog来维护type）
    @ManyToOne
    private Type type;

    //多个blog对应多个tag
    //设置级联操作：当新增一个blog，标签也新增一个进去
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags=new ArrayList<>();

    //多个blog对应一个user
    @ManyToOne
    private User user;

    //一个blog对应多个comment
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments =new ArrayList<>();

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //用于初始化tagIds的数据
    public void initTagIds(){
        this.tagIds=tagsToTagIds(this.getTags());
    }

    //用于将该blog对应的所有标签，转为ids数组   1,2,3
    private String tagsToTagIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids=new StringBuffer();
            boolean flag=false;
            for (Tag tag : tags) {
                if(flag){
                    //第一次不拼接 “，” 号
                    ids.append(",");
                }else{
                    flag=true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views='" + views + '\'' +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
