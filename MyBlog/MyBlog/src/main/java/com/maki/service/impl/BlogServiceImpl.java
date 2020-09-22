package com.maki.service.impl;

import com.maki.dao.BlogDao;
import com.maki.myexception.PageNotFoundException;
import com.maki.po.Blog;
import com.maki.po.Type;
import com.maki.service.BlogService;
import com.maki.util.MarkdownUtils;
import com.maki.util.MyBeanUtils;
import com.maki.util.MyComparator;
import com.maki.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogDao.getOne(id);
    }

    /*
     * 获取一个blog对象，但是该对象中的博客内容是String类型，Markdown格式的字符串
     * ##... #title等内容会原样显示在页面上
     * 需要将这些数据转为HTML
     * */
    @Transactional
    @Override
    public Blog getBlogConvertContent(Long id) {
        Blog blog = blogDao.getOne(id);
        if (blog == null) {
            throw new PageNotFoundException("该博客不存在!!!");
        }
        //若直接修改使用dao获取的blog对象，JPA可能会将修改后的该对象保存回数据库
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);

        //将Markdown格式的字符串转为HTML
        String content = b.getContent();
        String html = MarkdownUtils.markdownToHtmlExtensions(content);
        b.setContent(html);

        //每获取一次blog，views+1
        blogDao.updateViews(id);
        return b;
    }

    /*
     * 查询所有符合某一标签属性的blog
     * */
    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //blog表与tag表之间有个中间表进行关联
                Join join = root.join("tags");
                return cb.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        //获取blog表中的所有的年份
        List<String> years = blogDao.findGroupYears();
        //根据年份，获取该年份下所有的blog，存入map集合
        Map<String, List<Blog>> map = new TreeMap<>(new MyComparator());
        for (String year : years) {
            map.put(year, blogDao.findByYear(year));
        }

        return map;
    }


    @Override
    public Long countBlog() {
        //所有的博客数量
        return blogDao.count();
    }


    /*
     * (分页+条件)查询
     * 可以在dao层继承JpaSpecificationExecutor<Blog>接口
     * 调用findAll方法，创建Specification对象
     * */
    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                /*这里用来写组合查询的条件
                 * root：要查询的对象是Blog，通过root对象可以获取表中的字段
                 * criteriaQuery：查询条件的容器，可以把查询条件放这里
                 * criteriaBuilder：设置具体某一个条件的表达式
                 * */

                //这里存放组合的条件
                List<Predicate> predicates = new ArrayList<>();
                //标题的非空判断
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    //若标题非空，将标题内容放入查询条件中 相当于 ...like title = %blog.getTitle()%
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                //分类的非空判断
                if (blog.getTypeId() != null) {
                    //若分类非空，将分类信息放入查询条件中 相当于 ... type_id = blog.getTypeId()
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                //是否推荐
                if (blog.isRecommend()) {
                    //若推荐选项被选中，将该信息放入查询条件中 相当于 ... recommend = true
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                //相当于sql语句中的where，需要接收一个数组（该数组里存放的是组合的条件）
                CriteriaQuery<?> where = cq.where(predicates.toArray(new Predicate[predicates.size()]));
                System.out.println(where);
                return null;
            }
        }, pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        //若blog的id为null，说明是新增了一个blog
        if (blog.getId() == null) {
            //在保存一个blog对象时，需要初始化一些数据
            blog.setCreateTime(new Date());             //初始化创建时间
            blog.setUpdateTime(new Date());             //初始化更新时间
            blog.setViews(0);                           //初始化访问次数
            return blogDao.save(blog);
        } else {
            //若blog的id不为null，说明是修改一个blog
            blog.setUpdateTime(new Date());             //初始化更新时间
            return updateBlog(blog.getId(), blog);
        }

    }

    /*
     * 更新的时候，若前端页面传来的blog中有null字段
     * 则该字段不进行更新，沿用要更新的blog对象之前的属性值
     * */
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        //查询到要更新的blog
        Blog b = blogDao.getOne(id);
        if (b == null) {
            throw new PageNotFoundException("该博客不存在!!!");
        } else {
            //将blog对象b中的属性值，替换为前端页面传来的blog对象的属性值（除了值为null的属性）
            BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
            return blogDao.save(b);
        }
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    /*
     * 查询size个被推荐的博客，以更新时间排序
     * */
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        //设置排序方式：按Blog对象中的updateTime属性降序排序
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        //pageable：说明分页查询条件 只查询第一页，查询size个数据，按sort排序
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogDao.findTop(pageable);
    }

    /*
     * 根据字符串进行全局搜索，分页的形式
     * */
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogDao.findByQuery(query, pageable);
    }


}
