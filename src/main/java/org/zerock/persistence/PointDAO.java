package org.zerock.persistence;

/**
 * @author LeeSoohoon
 */
public interface PointDAO {

    void updatePoint(String uid, int point) throws Exception;
}
