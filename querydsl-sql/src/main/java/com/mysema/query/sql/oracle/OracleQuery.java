/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 *
 */
package com.mysema.query.sql.oracle;

import java.sql.Connection;

import com.mysema.query.DefaultQueryMetadata;
import com.mysema.query.QueryMetadata;
import com.mysema.query.QueryFlag.Position;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.Expr;
import com.mysema.query.types.expr.EBoolean;

/**
 * OracleQuery provides Oracle specific extensions to the base SQL query type
 *
 * @author tiwe
 * @version $Id$
 */
public class OracleQuery extends AbstractSQLQuery<OracleQuery> {    

    private static final String CONNECT_BY = "\nconnect by ";

    private static final String CONNECT_BY_NOCYCLE_PRIOR = "\nconnect by nocycle prior ";

    private static final String CONNECT_BY_PRIOR = "\nconnect by prior ";

    private static final String ORDER_SIBLINGS_BY = "\norder siblings by ";

    private static final String START_WITH = "\nstart with ";

    public OracleQuery(Connection conn, SQLTemplates patterns) {
        super(conn, patterns, new DefaultQueryMetadata());
    }

    protected OracleQuery(Connection conn, SQLTemplates patterns, QueryMetadata metadata) {
        super(conn, patterns, metadata);
    }

    public OracleQuery connectByPrior(EBoolean cond) {
        return addFlag(Position.BEFORE_ORDER, CONNECT_BY_PRIOR, cond);
    }

    public OracleQuery connectBy(EBoolean cond) {
        return addFlag(Position.BEFORE_ORDER, CONNECT_BY, cond);
    }

    public OracleQuery connectByNocyclePrior(EBoolean cond) {
        return addFlag(Position.BEFORE_ORDER, CONNECT_BY_NOCYCLE_PRIOR, cond);
    }

    public <A> OracleQuery startWith(EBoolean cond) {
        return addFlag(Position.BEFORE_ORDER, START_WITH, cond);
    }

    public OracleQuery orderSiblingsBy(Expr<?> path) {
        return addFlag(Position.BEFORE_ORDER, ORDER_SIBLINGS_BY, path);
    }
    
    // TODO : connect by root

    // TODO : connect by iscycle

    // TODO : connect by isleaf (pseudocolumn)

    // TODO : sys connect path
}
