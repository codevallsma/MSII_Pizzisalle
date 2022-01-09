package model.Delegation;

public class GironaDelegation extends Delegation implements SpecialPizza{

    public GironaDelegation(Integer id, String name) {
        super(id, name);
    }
    @Override
    public String getDelegationSpecialPizza() {
        return "Girona";
    }
}
