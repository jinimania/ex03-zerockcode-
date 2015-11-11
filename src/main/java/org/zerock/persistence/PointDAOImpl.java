package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@Repository
public class PointDAOImpl implements PointDAO {

    @Inject
    private SqlSession session;

    private static String namespace = "org.zerock.mapper.PointMapper";

    @Override
    public void updatePoint(final String uid, final int point) throws Exception {

        final HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("uid", uid);
        paramMap.put("point", point);

        session.update(namespace + ".updatePoint", paramMap);
    }
}
