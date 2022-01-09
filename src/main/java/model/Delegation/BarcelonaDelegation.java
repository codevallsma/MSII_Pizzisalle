package model.Delegation;

//implementing STRATEGY pattern in order to have a function that returns us the
// special pizza of a delegation according to the delegation class it is representing
public class BarcelonaDelegation extends Delegation implements SpecialPizza{

    public BarcelonaDelegation(Integer id, String name) {
        super(id, name);
    }

    public String getDelegationSpecialPizza() {
        return "Barcelona";
    }
}
