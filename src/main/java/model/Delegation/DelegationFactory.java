package model.Delegation;

/**
 * Factory method allows us to create diferent delegations according to the input given
 * This class is a Strategy factory
 */

public class DelegationFactory {
    public static Delegation getDelegation(int optionSelected){
        switch (optionSelected){
            case 1:
                return new BarcelonaDelegation(1,DelegationCentral.BARCELONA.toString());
            case 2:
                return new LleidaDelegation(2, DelegationCentral.LLEIDA.toString());
            case 3:
                return new TarragonaDelegation(3, DelegationCentral.TARRAGONA.toString());
            case 4:
                return new GironaDelegation(4, DelegationCentral.GIRONA.toString());
        }
        return null;

    }
}
