package pe.unjfsc.almacen.java11.model.imp;

import conexion.ConMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.unjfsc.almacen.java11.entity.CEEmpaqueProducto;
import pe.unjfsc.almacen.java11.model.CICambioAlmacen;

public class CMCambiarEmpaqueProductoHashSet implements CICambioAlmacen<CEEmpaqueProducto> {

    private static final Logger LOG = LoggerFactory.getLogger(CEEmpaqueProducto.class);

    @Override
    public void saveAlmacenCIC(CEEmpaqueProducto objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_insert_empaque(?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setString(1, objObjeto.getNombEmpa());
        cs.execute();

    }

    @Override
    public void modificarAlmacenCIC(CEEmpaqueProducto objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_update_empaque(?,?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setInt(1, objObjeto.getIdEmpaque());
        cs.setString(2, objObjeto.getNombEmpa());
        cs.execute();
    }

    @Override
    public void eliminarAlmacenCIC(CEEmpaqueProducto objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_delete_empaque(?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setInt(1, objObjeto.getIdEmpaque());
        cs.execute();
    }

    @Override
    public ResultSet buscar(Object objObject) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String nombre = "%" + objObject + "%";
        String sql = "select * from vempaque where empaque like ?";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        return rs;

    }

    public ResultSet mostrar() throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "select * from vempaque";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();
        return rs;

    }

}
