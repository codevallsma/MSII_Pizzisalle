package model.Delegation;

//implementing strategy pattern in order to have a function that returns us the
// special pizza of a delegation according to the delegation class it is representing

public class TarragonaDelegation  extends Delegation implements SpecialPizza{

    public TarragonaDelegation(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String getDelegationSpecialPizza() {
        return "Tarragona";
    }
}
