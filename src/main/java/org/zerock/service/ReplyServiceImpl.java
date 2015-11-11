package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

import java.util.List;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    private ReplyDAO dao;

    @Override
    public List<ReplyVO> listReply(final Integer bno) throws Exception {
        return dao.list(bno);
    }

    @Override
    public void addReply(final ReplyVO vo) throws Exception {
        dao.create(vo);
    }

    @Override
    public void modifyReply(final ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @Override
    public void removeReply(final Integer rno) throws Exception {
        dao.delete(rno);
    }

    @Override
    public List<ReplyVO> listReplyPage(final Integer bno, final Criteria cri) throws Exception {
        return dao.listPage(bno, cri);
    }

    @Override
    public int count(final Integer bno) throws Exception {
        return dao.count(bno);
    }
}
