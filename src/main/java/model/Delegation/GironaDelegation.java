package model.Delegation;

/**
 * Strategy pattern in the getDelegationSpecialPizza method, it implements the SpecialPizza interface
 */
public class GironaDelegation extends Delegation implements SpecialPizza{

    public GironaDelegation(Integer id, String name) {
        super(id, name);
    }
    @Override
    public String getDelegationSpecialPizza() {
        return "Girona";
    }
}
