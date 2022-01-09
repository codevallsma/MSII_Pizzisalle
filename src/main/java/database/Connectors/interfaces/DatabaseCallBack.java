package database.Connectors.interfaces;

import database.Connectors.enums.DBMethods;

public interface DatabaseCallBack {
    void onSuccess(Object object, DBMethods dbMethods);
    void onFailure(Object object);
}
