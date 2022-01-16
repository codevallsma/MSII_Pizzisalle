package controller;

import controller.ControllerState;
import database.Connectors.GeneralDBConnector;
import database.Connectors.MysqlConnector;
import database.Connectors.enums.DBTypes;
import database.Connectors.enums.TableTypes;
import model.Model;
import view.View;

/**
 * Class that contains everything a typical MVC should contain
 */
public class ControllerContext {
    protected View view;
    protected Model model;
    protected boolean insert;


    public ControllerContext(View view, Model model) {
        this.view = view;
        this.model = model;
        insert = false;
    }

    public void checkIfUser(){
        //ask for username and check if exists in the database or if it is already logged in
        //if exists we proceed to ask what he wants to do
        //if it does not exist we ask for all his user information in a customer form
    }
}
