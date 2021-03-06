package fr.projet2.what2eat.util;

import fr.projet2.what2eat.model.Ingredient;
import fr.projet2.what2eat.model.OpenFoodAPI.ProductOpenFood;

public class Mapping {

    public static Ingredient mapOpenFoodToIngredient(ProductOpenFood productOpenFood){
        return new Ingredient(productOpenFood.getName(), productOpenFood.getBarcode(), 0,productOpenFood.getQuantity(), null, productOpenFood.getImageSmallUrl(), productOpenFood.getCategories());
    }

}