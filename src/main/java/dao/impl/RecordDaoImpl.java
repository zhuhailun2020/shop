package dao.impl;

import dao.RecordDao;
import entity.Record;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class RecordDaoImpl implements RecordDao {
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void insertRecord(Record record) {
        String sql = "insert into `tab_record` values(?, ?, ?, ?)";
        template.update(sql, record.getUid(), record.getGid(), record.getTime(), record.getNumber());
    }
}
