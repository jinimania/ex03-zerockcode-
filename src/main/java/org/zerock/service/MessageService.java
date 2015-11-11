package org.zerock.service;

import org.zerock.domain.MessageVO;

/**
 * @author LeeSoohoon
 */
public interface MessageService {

    void addMessage(MessageVO vo) throws Exception;

    MessageVO readMessage(String uid, Integer mno) throws Exception;
}
