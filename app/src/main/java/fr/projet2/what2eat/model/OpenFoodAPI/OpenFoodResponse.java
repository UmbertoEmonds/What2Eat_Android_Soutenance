package fr.projet2.what2eat.model.OpenFoodAPI;

import java.util.List;

public class OpenFoodResponse {

    private List<ProduitOpenFood> data;

    public OpenFoodResponse(List<ProduitOpenFood> data) {
        this.data = data;
    }

    public List<ProduitOpenFood> getData() {
        return data;
    }

    public void setData(List<ProduitOpenFood> data) {
        this.data = data;
    }
}
