package gisq.opengis.CISDataSource;

/**
 * Created by ljw 2020/8/12
 */
public enum  EnumSDBType {

    SDBUnSign(-1),
    SDBOracle(0),
    SDBSqlServer(1),
    SDBAccess(2),
    SDBShapeFile(3),
    SDBGDB(4),
    SDBExcel(5),
    GISService(6),
    ThematicLayers(7),
    SDBPostgreSQL(8);

    private Integer type;

    private EnumSDBType(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
