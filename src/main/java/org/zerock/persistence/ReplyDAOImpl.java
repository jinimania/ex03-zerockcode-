package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Inject
    private SqlSession session;

    private static String namespace = "org.zerock.mapper.ReplyMapper";

    @Override
    public List<ReplyVO> list(final Integer bno) throws Exception {
        return session.selectList(namespace + ".list", bno);
    }

    @Override
    public void create(final ReplyVO vo) throws Exception {
        session.insert(namespace + ".create", vo);
    }

    @Override
    public void update(final ReplyVO vo) throws Exception {
        session.update(namespace + ".update", vo);
    }

    @Override
    public void delete(final Integer rno) throws Exception {
        session.delete(namespace + ".delete", rno);
    }

    @Override
    public List<ReplyVO> listPage(final Integer bno, final Criteria cri) throws Exception {

        final HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("bno", bno);
        paramMap.put("cri", cri);

        return session.selectList(namespace + ".listPage", paramMap);
    }

    @Override
    public int count(final Integer bno) throws Exception {
        return session.selectOne(namespace + ".count", bno);
    }

    @Override
    public int getBno(final Integer rno) throws Exception {
        return session.selectOne(namespace + ".getBno", rno);
    }
}
