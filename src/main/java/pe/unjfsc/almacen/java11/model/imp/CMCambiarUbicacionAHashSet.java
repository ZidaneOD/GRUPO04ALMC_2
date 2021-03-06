package pe.unjfsc.almacen.java11.model.imp;

import conexion.ConMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.unjfsc.almacen.java11.entity.CEUbicacionAlmacen;
import pe.unjfsc.almacen.java11.model.CICambioAlmacen;

public class CMCambiarUbicacionAHashSet implements CICambioAlmacen<CEUbicacionAlmacen> {

    private static final Logger LOG = LoggerFactory.getLogger(CEUbicacionAlmacen.class);

    @Override
    public void saveAlmacenCIC(CEUbicacionAlmacen objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_insert_ubicacion(?,?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setString(1, objObjeto.getNombUbic());
        cs.setInt(2, objObjeto.getIdDistrito());
        cs.execute();
    }

    @Override
    public void modificarAlmacenCIC(CEUbicacionAlmacen objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_update_ubicacion(?,?,?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setInt(1, objObjeto.getIdUbicacion());
        cs.setString(2, objObjeto.getNombUbic());
        cs.setInt(3, objObjeto.getIdDistrito());
        cs.execute();
    }

    @Override
    public void eliminarAlmacenCIC(CEUbicacionAlmacen objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_delete_ubicacion(?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setInt(1, objObjeto.getIdUbicacion());
        cs.execute();
    }

    @Override
    public ResultSet buscar(Object objObject) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String nombre = "%" + objObject + "%";
        String sql = "select * from vubicacion where DIRECCIÓN like ?";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public ResultSet mostrar() throws Exception {
        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "select * from vubicacion;";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();
        return rs;

    }
    
    public ResultSet buscaPorCodigo(Object objObject) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String nombre = "%" + objObject + "%";
        String sql = "select * from vubicacion where DIRECCIÓN like ?";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}
