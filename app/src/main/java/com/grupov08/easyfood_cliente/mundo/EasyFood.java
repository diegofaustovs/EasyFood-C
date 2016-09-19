package com.grupov08.easyfood_cliente.mundo;

import java.util.ArrayList;

/**
 * Created by concol on 18/09/2016.
 */
public class EasyFood {

    private static EasyFood instancia;

    private ArrayList<Local> locales;
    private String telefono_domiciliario;

    public EasyFood() {
        locales = new ArrayList<>();
        mock();
    }

    public static EasyFood getInstancia() {
        if (instancia == null)
            return instancia = new EasyFood();
        else
            return instancia;
    }

    public ArrayList<Local> getLocales() {
        return locales;
    }

    public String getTelefono_domiciliario() {
        return telefono_domiciliario;
    }

    public void setTelefono_domiciliario(String telefono_domiciliario) {
        this.telefono_domiciliario = telefono_domiciliario;
    }

    private void mock() {
        for (int i = 0; i < 5; i++) {
            String[] s = new String[2];
            s[0] = "" + i;
            s[1] = "" + i;

            ArrayList<Producto> pr = new ArrayList<>();
            pr.add(new Producto("1" + i, "1", 12, null));
            pr.add(new Producto("2" + i, "2", 12, null));
            pr.add(new Producto("3" + i, "3", 12, null));

            locales.add(new Local("Local " + i, "AAA", pr, s, "Descr. L" + i));
        }
    }
}
