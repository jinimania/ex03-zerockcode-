package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession session;

    private static String namespace = "org.zerock.mapper.BoardMapper";

    @Override
    public void create(final BoardVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public BoardVO read(final Integer bno) throws Exception {
        return session.selectOne(namespace + ".read", bno);
    }

    @Override
    public void update(final BoardVO vo) throws Exception {
        session.update(namespace + ".update", vo);
    }

    @Override
    public void delete(final Integer bno) throws Exception {
        session.delete(namespace + ".delete", bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return session.selectList(namespace + ".listAll");
    }

    @Override
    public List<BoardVO> listPage(int page) throws Exception {
        if (page <= 0) {
            page = 1;
        }
        page = (page - 1) * 10;

        return session.selectList(namespace + ".listPage", page);
    }

    @Override
    public List<BoardVO> listCriteria(final Criteria cri) throws Exception {
        return session.selectList(namespace + ".listCriteria", cri);
    }

    @Override
    public int countPaging(final Criteria cri) throws Exception {
        return session.selectOne(namespace + ".countPaging", cri);
    }

    @Override
    public List<BoardVO> listSearch(final SearchCriteria cri) throws Exception {
        return session.selectList(namespace + ".listSearch", cri);
    }

    @Override
    public int listSearchCount(final SearchCriteria cri) throws Exception {
        return session.selectOne(namespace + ".listSearchCount", cri);
    }

    @Override
    public void updateReplyCnt(final Integer bno, final int amount) throws Exception {
        final HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("bno", bno);
        paramMap.put("amount", amount);

        session.update(namespace + ".updateReplyCnt", paramMap);
    }

    @Override
    public void updateViewCnt(final Integer bno) throws Exception {
        session.update(namespace + ".updateViewCnt", bno);
    }

    @Override
    public void addAttach(final String fullName) throws Exception {
        session.insert(namespace + ".addAttach", fullName);
    }
}
