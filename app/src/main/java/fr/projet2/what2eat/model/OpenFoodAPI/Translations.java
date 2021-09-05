package fr.projet2.what2eat.model.OpenFoodAPI;

import java.io.Serializable;

public class Translations implements Serializable {

    private String de;
    private String fr;
    private String it;
    private String en;

    public String getDe() {
        return de;
    }

    public String getFr() {
        return fr;
    }

    public String getIt() {
        return it;
    }

    public String getEn() {
        return en;
    }
}
