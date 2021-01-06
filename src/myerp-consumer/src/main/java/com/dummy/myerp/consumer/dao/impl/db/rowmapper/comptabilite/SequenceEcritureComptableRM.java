package com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite;

import com.dummy.myerp.consumer.dao.impl.db.dao.ComptabiliteDaoImpl;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link RowMapper} de {@link SequenceEcritureComptable}
 */
public class SequenceEcritureComptableRM implements RowMapper<SequenceEcritureComptable>{

    @Override
    public SequenceEcritureComptable mapRow(ResultSet pRS, int pRowNum) throws SQLException {
        SequenceEcritureComptable vBean = new SequenceEcritureComptable();
        vBean.setJournalCode(pRS.getString(ComptabiliteDaoImpl.dbColumn.JOURNAL_CODE.getValue()));
        vBean.setAnnee(pRS.getInt(ComptabiliteDaoImpl.dbColumn.ANNEE.getValue()));
        vBean.setJournalCode(pRS.getString(ComptabiliteDaoImpl.dbColumn.JOURNAL_CODE.getValue()));
        return vBean;
    }
}
