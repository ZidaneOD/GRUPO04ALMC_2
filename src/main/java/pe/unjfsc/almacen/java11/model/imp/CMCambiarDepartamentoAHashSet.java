package pe.unjfsc.almacen.java11.model.imp;

import conexion.ConMySQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.unjfsc.almacen.java11.entity.CEDepartamentoAlmacen;
import pe.unjfsc.almacen.java11.model.CICambioAlmacen;

public class CMCambiarDepartamentoAHashSet implements CICambioAlmacen<CEDepartamentoAlmacen> {

    private static final Logger LOG = LoggerFactory.getLogger(CEDepartamentoAlmacen.class);

    @Override
    public void saveAlmacenCIC(CEDepartamentoAlmacen objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_insert_departamento(?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setString(1, objObjeto.getNombDepa());
        cs.execute();
    }

    @Override
    public void modificarAlmacenCIC(CEDepartamentoAlmacen objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_update_departamento(?,?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setInt(1, objObjeto.getidDepartamento());
        cs.setString(2, objObjeto.getNombDepa());
        cs.execute();

    }

    @Override
    public void eliminarAlmacenCIC(CEDepartamentoAlmacen objObjeto) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "CALL sp_delete_departamento(?);";
        CallableStatement cs = cn.prepareCall(sql);
        cs.setInt(1, objObjeto.getidDepartamento());
        cs.execute();
    }

    @Override
    public ResultSet buscar(Object objObject) throws Exception {

        Connection cn = ConMySQL.getInstance().getConnection();
        String nombre = "%" + objObject + "%";
        String sql = "select * from vdepartamento where departamento like ?";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        return rs;

    }
   
     public ResultSet mostrar() throws Exception {
        Connection cn = ConMySQL.getInstance().getConnection();
        String sql = "select * from vdepartamento";
        PreparedStatement ps = cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();
        return rs;

    }

}
