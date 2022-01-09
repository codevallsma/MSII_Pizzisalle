package model.pizza.Drinks;

import model.pizza.Drinks.exception.DrinkNotFoundException;

//Builder
public class DrinkBuilder {
    /**
     * Building drink types given an string and checks if there is an enum type that corresponds to them
     * @param drinkTypeStr: String that indicates the drink type given
     * @return The drink type class that matches the string given
     * @throws DrinkNotFoundException
     */
    public static DrinkType drinkBuilder(String drinkTypeStr) throws DrinkNotFoundException {
        drinkTypeStr = drinkTypeStr.toUpperCase();
        //getting all the drink enumeration types
        final DrinkType[] values = DrinkType.values();
        // iterate throught all enum types to see if there is a matching one
        for(DrinkType drinkType: values){
            if(drinkType.toString().equals(drinkTypeStr)) return drinkType;
        }
        throw new DrinkNotFoundException("Error! The drink you inserted is not found");
    }
}
