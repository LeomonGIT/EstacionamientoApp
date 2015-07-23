package edu.ulima.estacionapp.Servicios.Empresa;

import edu.ulima.estacionapp.Bean.Cliente;
import edu.ulima.estacionapp.Bean.Empresa;

/**.
 */
public class EmpresaController {
    public static Empresa getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(Empresa empresa) {
        EmpresaController.empresa = empresa;
    }

    private static Empresa empresa;
    private static EmpresaController instance;

    public static EmpresaController getInstance(){
        if(instance==null)
            instance=new EmpresaController();
        return instance;
    }
}
