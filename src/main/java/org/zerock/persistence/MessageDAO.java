package org.zerock.persistence;

import org.zerock.domain.MessageVO;

/**
 * @author LeeSoohoon
 */
public interface MessageDAO {

    void create(MessageVO vo) throws Exception;

    MessageVO readMessage(Integer mid) throws Exception;

    void updateState(Integer mid) throws Exception;
}
