package com.codi.base.dao.v2;

import com.codi.base.util.BizAssert;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseDaoImpl<E> extends SqlSessionDaoSupport implements BaseDao<E> {
    public static final String MAPPER_INSERTBATCH = "insertBatch";
    public static final String MAPPER_DELETEBATCH = "deleteBatch";
    public static final String MAPPER_UPDATEBATCH = "updateBatch";

    private final String className;

    @SuppressWarnings("unchecked")
	public BaseDaoImpl() {
        this.className = this.getClass().getInterfaces()[0].getName();
    }

    @Autowired
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int delete(String statement, Object parameter) {
        BizAssert.notEmpty(statement, "Property statement is required");
        return getSqlSession().delete(statement, parameter);
    }

    @Override
    public int deleteBatch(List<?> list) {
        return deleteBatch(generateStatement(MAPPER_DELETEBATCH), list);
    }

    @Override
    public int deleteBatch(String statement, List<?> list) {
        BizAssert.notEmpty(statement, "Property statement is required");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlSession sqlSession = getSqlSession();
        for (int i = 0; i < list.size(); i++) {
            sqlSession.delete(statement, list.get(i));
        }
        return list.size();
    }

    @Override
    public List<E> findList(String statement, Object parameter) {
        BizAssert.notEmpty(statement, "Property statement is required");
        return getSqlSession().selectList(statement, parameter);
    }

    @Override
    public E getObject(String statement, Object parameter) {
        BizAssert.notEmpty(statement, "Property statement is required");
        return getSqlSession().selectOne(statement, parameter);
    }

    @Override
    public int insert(String statement, Object parameter) {
        BizAssert.notEmpty(statement, "Property statement is required");
        return getSqlSession().insert(statement, parameter);
    }

    @Override
    public int insertBatch(List<?> list) {
        return insertBatch(generateStatement(MAPPER_INSERTBATCH), list);
    }

    @Override
    public int insertBatch(String statement, List<?> list) {
        BizAssert.notEmpty(statement, "Property statement is required");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlSession sqlSession = getSqlSession();
        for (int i = 0; i < list.size(); i++) {
            sqlSession.insert(statement, list.get(i));
        }
        return list.size();
    }

    @Override
    public int update(String statement, Object parameter) {
        BizAssert.notEmpty(statement, "Property statement is required");
        return getSqlSession().update(statement, parameter);
    }

    @Override
    public int updateBatch(List<?> list) {
        return updateBatch(generateStatement(MAPPER_UPDATEBATCH), list);
    }

    @Override
    public int updateBatch(String statement, List<?> list) {
        BizAssert.notEmpty(statement, "Property statement is required");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        SqlSession sqlSession = getSqlSession();
        for (int i = 0; i < list.size(); i++) {
            sqlSession.update(statement, list.get(i));
        }
        return list.size();
    }

    /**
     * 生成statement
     *
     * @param mapperId
     *            :sqlmap配置文件sql语句id
     * @return
     */
    protected String generateStatement(String mapperId) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(".").append(mapperId);

        return sb.toString();
    }

    protected String generateBaseStatement(String mapperId) {
        return com.codi.base.domain.SampleVO.class.getName() + "." + mapperId;
    }

	@Override
	public List<E> findList(String statement, Object parameter, RowBounds rowBounds) {
		return getSqlSession().selectList(statement, parameter, rowBounds);
	}
}
