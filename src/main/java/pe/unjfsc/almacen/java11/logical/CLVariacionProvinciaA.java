package pe.unjfsc.almacen.java11.logical;

import java.util.HashSet;
import pe.unjfsc.almacen.java11.entity.CEProvinciaAlmacen;

public class CLVariacionProvinciaA {

    public Object[][] convertHashSetArray(HashSet<CEProvinciaAlmacen> poHsData) {

        String[][] aDataResponse = new String[poHsData.size()][3];
        int iRow = 0;
        for (CEProvinciaAlmacen oItem : poHsData) {
            aDataResponse[iRow][0] = String.valueOf(oItem.getIdProvincia());
            aDataResponse[iRow][1] = oItem.getNombProvincia();
            aDataResponse[iRow][1] = String.valueOf(oItem.getIdDepartamento());
            iRow++;
        }
        return aDataResponse;
    }
}
