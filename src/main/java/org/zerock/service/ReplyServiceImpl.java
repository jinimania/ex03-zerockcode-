package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.ReplyDAO;

import java.util.List;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    private ReplyDAO replyDAO;

    @Inject
    private BoardDAO boardDAO;

    @Override
    public List<ReplyVO> listReply(final Integer bno) throws Exception {
        return replyDAO.list(bno);
    }

    @Transactional
    @Override
    public void addReply(final ReplyVO vo) throws Exception {
        replyDAO.create(vo);
        boardDAO.updateReplyCnt(vo.getBno(), 1);
    }

    @Override
    public void modifyReply(final ReplyVO vo) throws Exception {
        replyDAO.update(vo);
    }

    @Transactional
    @Override
    public void removeReply(final Integer rno) throws Exception {
        final int bno = replyDAO.getBno(rno);
        replyDAO.delete(rno);
        boardDAO.updateReplyCnt(bno, -1);
    }

    @Override
    public List<ReplyVO> listReplyPage(final Integer bno, final Criteria cri) throws Exception {
        return replyDAO.listPage(bno, cri);
    }

    @Override
    public int count(final Integer bno) throws Exception {
        return replyDAO.count(bno);
    }
}
