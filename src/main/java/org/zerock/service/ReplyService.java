package org.zerock.service;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;

/**
 * @author LeeSoohoon
 */
public interface ReplyService {

    List<ReplyVO> listReply(Integer bno) throws Exception;

    void addReply(ReplyVO vo) throws Exception;

    void modifyReply(ReplyVO vo) throws Exception;

    void removeReply(Integer rno) throws Exception;

    List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;

    int count(Integer bno) throws Exception;
}
