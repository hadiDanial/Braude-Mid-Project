
package controllers;

import java.io.IOException;
import java.util.HashSet;

import client.Client;
import entities.users.Order;
import gui.guimanagement.SceneManager;
import requests.EntityRequestWithId;
import requests.Request;
import utility.IEventListener;
import utility.IResponse;

public interface ClientControllerIF{
    public <T> void sendRequest(Request request, IResponse<T> response);

}