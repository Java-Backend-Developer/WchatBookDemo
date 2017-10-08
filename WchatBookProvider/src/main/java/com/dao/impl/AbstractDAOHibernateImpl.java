package com.dao.impl;

import com.dao.IDAO;
import com.entity.AbstractEntity;
import com.util.StringUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

abstract public class AbstractDAOHibernateImpl implements IDAO {

    @Resource(name = "hibernateTemplate")
    HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("rawtypes")
    abstract protected Class getEntityClass();

    @SuppressWarnings("unchecked")
    protected AbstractEntity get(Long id) {
        return (AbstractEntity) getHibernateTemplate().get(getEntityClass(), id);
    }

    @Override
    public Serializable add(AbstractEntity abstractEntity) {
        // 创建时间
        abstractEntity.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
        // 最近修改时间
        abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        if (null == abstractEntity.getInvalid()) {
            abstractEntity.setInvalid(false);
        }
        if (null == abstractEntity.getStatus() || 0 == abstractEntity.getStatus()) {
            abstractEntity.setStatus(1);
        }
        if (null == abstractEntity.getOrderTag() || 0 == abstractEntity.getOrderTag()) {
            abstractEntity.setOrderTag(1);
        }
       return  getHibernateTemplate().save(abstractEntity);
    }

    @Override
    public boolean saveResultBoolean(AbstractEntity abstractEntity) {
        // 创建时间
        abstractEntity.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
        // 最近修改时间
        abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        if (null == abstractEntity.getInvalid()) {
            abstractEntity.setInvalid(false);
        }
        if (null == abstractEntity.getStatus() || 0 == abstractEntity.getStatus()) {
            abstractEntity.setStatus(1);
        }
        if (null == abstractEntity.getOrderTag() || 0 == abstractEntity.getOrderTag()) {
            abstractEntity.setOrderTag(1);
        }
        getHibernateTemplate().save(abstractEntity);
        long id = abstractEntity.getId();
        return id > 0;
    }
    
    @Override
    public boolean saveAppoint(AbstractEntity abstractEntity) {
        // 创建时间
        abstractEntity.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
        // 最近修改时间
        abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        if (null == abstractEntity.getInvalid()) {
            abstractEntity.setInvalid(true);
        }
        if (null == abstractEntity.getStatus()) {
            abstractEntity.setStatus(0);
        }
        if (null == abstractEntity.getOrderTag() || 0 == abstractEntity.getOrderTag()) {
            abstractEntity.setOrderTag(1);
        }
        getHibernateTemplate().save(abstractEntity);
        long id = abstractEntity.getId();
        return id > 0;
    }

    @Override
    public boolean update(AbstractEntity abstractEntity) {
        // 最近修改时间
        abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        getHibernateTemplate().update(abstractEntity);
        return true;
    }

    @Override
    public boolean remove(Long id) {
        AbstractEntity abstractEntity = get(id);
        abstractEntity.setInvalid(false);
        // 最近修改时间
        abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        getHibernateTemplate().update(abstractEntity);
        return true;
    }

    @Override
    public int delete(Long id) {
        AbstractEntity abstractEntity = get(id);
        getHibernateTemplate().delete(abstractEntity);
        return 1;
    }

    @Override
    public void saveOrUpdate(AbstractEntity abstractEntity) {
        abstractEntity.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
        abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
        getHibernateTemplate().saveOrUpdate(abstractEntity);
    }

    /**
     * 获取模糊查询条件
     * 
     * @param condition
     *            查询条件
     * @return 模糊查询条件（%条件%）
     */
    protected String getFuzzyCondition(String condition) {
        return "%" + condition + "%";
    }

    /**
     * 根据参数修改
     * 
     * @Description
     * @param hql
     * @param list
     * @return
     * @author gwb
     * @date 2015年12月17日 下午3:19:36
     */
    public boolean updateByParam(String hql, List<Object> list) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql);
        for (int i = 0; i < list.size(); i++) {
            query.setParameter(i, list.get(i));
        }
        query.executeUpdate();
        return true;
    }

    /**
     * 根据参数查询一个对象
     * 
     * @Description
     * @param hql
     * @param list
     * @return
     * @author gwb
     * @date 2015年12月17日 下午3:19:49
     */
    public Object queryByParam(String hql, List<Object> list) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql);
        for (int i = 0; i < list.size(); i++) {
            query.setString(i, list.get(i).toString());
        }
        return query.uniqueResult();
    }

    /**
     * this.getHibernateTemplate().getSessionFactory().getCurrentSession()
     * 
     * 分页查询
     * 
     * @Description
     * @param hql
     *            -----------------------hql
     * @param hqlResultParameter
     *            -----------------------要查询的字段，可以为NULL
     * @param ls
     *            -----------------------查询参数
     * @param entity
     *            -----------------------查询对象，必须继承AbstractEntity
     * @param falgMap
     *            -----------------------true或false----ture表示转换成map，false不进行转换
     * @return
     * @author gwb
     * @date 2015年12月12日 上午9:37:48
     */
    protected List<?> findByPageHql(String hql, String hqlResultParameter, List<Object> ls, AbstractEntity entity,
            Boolean falgMap) {
        // 查询数据总数
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hqlResultParameter + hql.toString() + " )");
        if (falgMap) {
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i) + "");
        }
        query.setFirstResult(entity.getStart());
        query.setMaxResults(entity.getPageSize());
        query.list();
        return query.list();
    }

    /**
     * this.getHibernateTemplate().getSessionFactory().getCurrentSession()
     * 查询分页总数 通过
     * 
     * @Description
     * @param hql
     * @param ls
     * @return
     * @author gwb
     * @date 2015年12月12日 下午1:19:33
     */
    protected Long countHql(String hql, List<Object> ls) {
        // 查询数据总数
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query queryCount = session.createQuery("select count(*) " + hql);
        for (int i = 0; i < ls.size(); i++) {
            queryCount.setParameter(i, ls.get(i));
        }
        Long count = (Long) (queryCount.uniqueResult());
        return count;
    }
    
    protected Long countHqlAll(String hql, Object ls) {
        // 查询数据总数
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query queryCount = session.createQuery("select count(*) " + hql);
        queryCount.setParameter(0, ls);
        Long count = (Long) (queryCount.uniqueResult());
        return count;
    }

    /**
     * HibernateCallback ---查询分页
     * 
     * @Description
     * @param hql
     * @param hqlResultParameter
     * @param ls
     * @param entity
     * @param aliasToEntity
     *            true或false----ture表示转换成map，false不进行转换
     * @return
     * @author gwb
     * @date 2015年12月12日 下午3:22:29
     */
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    // public List<?> findByPageCallBack(String hql, String hqlResultParameter,
    // List<Object> ls, AbstractEntity entity,
    // Boolean aliasToEntity) {
    // public List<?> findByPageCallBack(final String finalHql, final String
    // finalhqlResultParameter,
    // final List<Object> finalLs, final AbstractEntity finalentity, final
    // Boolean finalaliasToEntity) {
    // List list = getHibernateTemplate().execute(new HibernateCallback() {//
    // 实现HibernateCallback接口必须实现的方法
    // public List doInHibernate(Session session) throws HibernateException {//
    // 执行Hibernate分页查询
    // String hql = "";
    // if (StringUtil.notEmpty(finalhqlResultParameter)) {
    // hql = finalhqlResultParameter + finalHql;
    // } else {
    // hql = finalHql;
    // }
    // Query query = session.createQuery(hql);// 为hql语句传入参数
    // for (int i = 0; i < finalLs.size(); i++) {
    // query.setParameter(i, finalLs.get(i));
    // }
    // query.setFirstResult(finalentity.getStart());
    // query.setMaxResults(finalentity.getPageSize());
    // if (finalaliasToEntity) {
    // query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    // }
    // return query.list();
    // }
    // });
    // return list;
    // }
    /**
     * HibernateCallback ---查询分页总数
     * 
     * @Description
     * @param hql
     * @param
     * @param ls
     * @param
     * @param
     * @return
     * @author gwb
     * @date 2015年12月12日 下午1:40:11
     */
    public Long findByPageCallBackCount(final String hql, final List<Object> ls) {
        // 通过一个HibernateCallback对象来执行查询
        // final String finalHql = "select count(*)" + hql;
        // final List<Object> finalLs = ls;
        Long count = getHibernateTemplate().execute(new HibernateCallback<Long>() {// 实现HibernateCallback接口必须实现的方法
                    public Long doInHibernate(Session session) throws HibernateException {// 执行Hibernate分页查询
                        Query queryCount = session.createQuery(" select count(1)" + hql);// 为hql语句传入参数
                        if (ls != null) {
                            for (int i = 0; i < ls.size(); i++) {
                                queryCount.setParameter(i, ls.get(i));
                            }
                        }
                        return (Long) queryCount.uniqueResult();
                    }
                });
        return count;
    }
    
    public Long MathCallBackLong(final String hql, final List<Object> ls) {
        // 通过一个HibernateCallback对象来执行查询
        // final String finalHql = "select count(*)" + hql;
        // final List<Object> finalLs = ls;
        Long count = getHibernateTemplate().execute(new HibernateCallback<Long>() {// 实现HibernateCallback接口必须实现的方法
                    public Long doInHibernate(Session session) throws HibernateException {// 执行Hibernate分页查询
                        Query queryCount = session.createQuery(hql);// 为hql语句传入参数
                        if (ls != null) {
                            for (int i = 0; i < ls.size(); i++) {
                                queryCount.setParameter(i, ls.get(i));
                            }
                        }
                        return (Long) queryCount.uniqueResult();
                    }
                });
        return count;
    }
    
    
    public Long findByCount_re(final String hql, final List<Object> ls) {
        // 通过一个HibernateCallback对象来执行查询
        // final String finalHql = "select count(*)" + hql;
        // final List<Object> finalLs = ls;
        Long count = getHibernateTemplate().execute(new HibernateCallback<Long>() {// 实现HibernateCallback接口必须实现的方法
                    public Long doInHibernate(Session session) throws HibernateException {// 执行Hibernate分页查询
                        Query queryCount = session.createQuery(" select count(DISTINCT reservationMobile)" + hql);// 为hql语句传入参数
                        if (ls != null) {
                            for (int i = 0; i < ls.size(); i++) {
                                queryCount.setParameter(i, ls.get(i));
                            }
                        }
                        return (Long) queryCount.uniqueResult();
                    }
                });
        return count;
    }

    /**
     * 
     * 
     * SQL this.getHibernateTemplate().getSessionFactory().getCurrentSession()
     * 
     * 分页查询
     * 
     * @Description
     * @param sql
     *            -----------------------sql ----
     * @param hqlResultParameter
     *            -----------------------要查询的字段，可以为NULL
     * @param ls
     *            -----------------------查询参数
     * @param entity
     *            -----------------------查询对象，必须继承AbstractEntity
     * @param aliasToEntity
     *            -----------------------true或false----ture表示转换成map，false不进行转换
     * @return
     * @author gwb
     * @date 2015年12月12日 上午9:37:48
     */
    protected List<?> findByPageSql(String sql, String hqlResultParameter, List<Object> ls, AbstractEntity entity,
            boolean aliasToEntity) {

        // 查询数据总数
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        String sq = null;
        if (hqlResultParameter != null) {
            sq = hqlResultParameter + sql;
        } else {
            sq = sql;
        }
        Query query = session.createSQLQuery(sq);
        if (aliasToEntity) {
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        for (int i = 0; i < ls.size(); i++) {
            query.setString(i, ls.get(i) + "");
        }
        query.setFirstResult(entity.getStart());
        query.setMaxResults(entity.getPageSize());
        return query.list();
    }
    
    

    /**
     * SQL this.getHibernateTemplate().getSessionFactory().getCurrentSession()
     * 查询分页总数 通过
     * 
     * @Description
     * @param Sql
     * @param ls
     * @return
     * @author gwb
     * @date 2015年12月12日 下午1:19:33
     */
    protected Long countSql(String Sql, List<Object> ls) {
        // 查询数据总数
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query queryCount = session.createSQLQuery(Sql);
        for (int i = 0; i < ls.size(); i++) {
            queryCount.setParameter(i, ls.get(i));
        }
        Long count = Long.parseLong(queryCount.uniqueResult().toString());
        return count;
    }

    /**
     * HibernateCallback ---查询分页
     * 
     * @Description
     * @param
     * @param
     * @param
     * @param
     * @param
     *            <p>
     *            Transformers.aliasToBean(GesStoreVO.class) ----------对应的Vo
     *            <p>
     *            Transformers.ALIAS_TO_ENTITY_MAP; ---------------map
     *            <p>
     *            null ----------------------------------------不进行转换
     * @return
     * @author gwb
     * @date 2015年12月12日 下午3:22:29
     */
    // public List<?> findByPageCallBack(String hql, String hqlResultParameter,
    // List<Object> ls, AbstractEntity entity,
    // Boolean aliasToEntity) {
    public List<?> findByPageCallBack(final String finalHql, final String finalhqlResultParameter,
            final List<Object> finalLs, final AbstractEntity finalentity, final ResultTransformer finalaliasToEntity) {
        List<?> list = getHibernateTemplate().execute(new HibernateCallback<List<?>>() {// 实现HibernateCallback接口必须实现的方法
                    public List<?> doInHibernate(Session session) throws HibernateException {// 执行Hibernate分页查询
                        String hql = "";
                        if (StringUtil.notEmpty(finalhqlResultParameter)) {
                            hql = finalhqlResultParameter + finalHql;
                        } else {
                            hql = finalHql;
                        }
                        Query query = session.createQuery(hql);// 为hql语句传入参数
                        if (finalLs != null) {
                            for (int i = 0; i < finalLs.size(); i++) {
                                query.setParameter(i, finalLs.get(i));
                            }
                        }
                        if (null != finalentity.getStart()) {
                            query.setFirstResult(finalentity.getStart());
                        }
                        if (null != finalentity.getPageSize()) {
                            query.setMaxResults(finalentity.getPageSize());
                        }
                        if (finalaliasToEntity != null) {
                            query.setResultTransformer(finalaliasToEntity);
                        }
                        return query.list();
                    }
                });
        return list;
    }

    /**
     * 查询list列表
     * 
     * @Description
     * @param finalHql
     * @param finalhqlResultParameter
     * @param finalLs
     * @param finalentity
     * @param finalaliasToEntity
     * @return
     * @author gwb
     * @date 2015年12月17日 上午10:00:58
     */
    public List<?> findByListCallBack(final String finalHql, final String finalhqlResultParameter,
            final List<Object> finalLs, final ResultTransformer finalaliasToEntity) {
        List<?> list = getHibernateTemplate().execute(new HibernateCallback<List<?>>() {// 实现HibernateCallback接口必须实现的方法
                    public List<?> doInHibernate(Session session) throws HibernateException {// 执行Hibernate分页查询
                        String hql = "";
                        if (StringUtil.notEmpty(finalhqlResultParameter)) {
                            hql = finalhqlResultParameter + finalHql;
                        } else {
                            hql = finalHql;
                        }
                        Query query = session.createQuery(hql);// 为hql语句传入参数

                        if (finalLs != null) {
                            for (int i = 0; i < finalLs.size(); i++) {
                                query.setParameter(i, finalLs.get(i));
                            }
                        }
                        if (finalaliasToEntity != null) {
                            query.setResultTransformer(finalaliasToEntity);
                        }
                        return query.list();
                    }
                });
        return list;
    }

    /**
     * 批量删除
     * 
     * @Description
     * @param ids
     *            ID列表
     * @return 删除条数
     * @author guojianbin
     * @date 2015年12月17日
     */
    public int delBatchByID(List<Long> idList) {

        // 生成删除语句
        StringBuilder hql = new StringBuilder();
        hql.append("update ");
        hql.append(getEntityClass().getSimpleName());
        hql.append(" set invalid = true, updatedDatetime = :updatedDatetime where id in (:id_list) ");

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql.toString());
        // 修改时间
        query.setParameter("updatedDatetime", new Timestamp(System.currentTimeMillis()));
        // ID列表
        query.setParameterList("id_list", idList);

        return query.executeUpdate();
    }

    public void evict(AbstractEntity entity) {
        this.getHibernateTemplate().evict(entity);
    }

    /**
     * 批量插入
     * 
     * @Description
     * @param entityList
     *            实体列表
     * @return 插入条数
     * @author guojianbin
     * @date 2015年12月29日
     */
    public Integer batchAdd(final List<? extends AbstractEntity> entityList) {

        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {// 实现HibernateCallback接口必须实现的方法
                    public Integer doInHibernate(Session session) throws HibernateException {
                        for (int i = 0; i < entityList.size(); i++) {
                            AbstractEntity abstractEntity = entityList.get(i);
                            // 创建时间
                            abstractEntity.setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
                            // 最近修改时间
                            abstractEntity.setUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
                            if (null == abstractEntity.getInvalid()) {
                                abstractEntity.setInvalid(false);
                            }
                            if (null == abstractEntity.getStatus() || 0 == abstractEntity.getStatus()) {
                                abstractEntity.setStatus(1);
                            }
                            if (null == abstractEntity.getOrderTag() || 0 == abstractEntity.getOrderTag()) {
                                abstractEntity.setOrderTag(1);
                            }
                            session.save(entityList.get(i));
                            if (i % 10 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                        return entityList.size();
                    }
                });
    }

    /**
     * 原生sql查询
     * @author vachel.wang
     * @param sql
     * @param ls
     * @return
     */
    protected List<?> findBySql(String sql , List<Object> ls) {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        for(int i=0;i<ls.size();i++){
            Object param = ls.get(i);
            sqlQuery.setParameter(i,param);
        }
        return sqlQuery.list();
    }

}
