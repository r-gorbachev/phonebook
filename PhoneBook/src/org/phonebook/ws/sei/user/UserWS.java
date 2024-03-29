
package org.phonebook.ws.sei.user;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.phonebook.beans.User;
import org.phonebook.exceptions.WSException;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserWS", targetNamespace = "http://phonebook.org/ws")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserWS {


    /**
     * 
     * @return
     *     returns java.util.List<org.phonebook.ws.sei.user.User>
     * @throws WSException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllUsers", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.GetAllUsers")
    @ResponseWrapper(localName = "getAllUsersResponse", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.GetAllUsersResponse")
    @Action(input = "http://phonebook.org/ws/UserWS/getAllUsersRequest", output = "http://phonebook.org/ws/UserWS/getAllUsersResponse", fault = {
        @FaultAction(className = WSException.class, value = "http://phonebook.org/ws/UserWS/getAllUsers/Fault/ServiceException")
    })
    public List<User> getAllUsers()
        throws WSException
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     * @throws WSException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addUser", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.AddUser")
    @ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.AddUserResponse")
    @Action(input = "http://phonebook.org/ws/UserWS/addUserRequest", output = "http://phonebook.org/ws/UserWS/addUserResponse", fault = {
        @FaultAction(className = WSException.class, value = "http://phonebook.org/ws/UserWS/addUser/Fault/ServiceException")
    })
    public boolean addUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0)
        throws WSException
    ;

    /**
     * 
     * @param arg0
     * @throws WSException
     */
    @WebMethod
    @RequestWrapper(localName = "removeUser", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.RemoveUser")
    @ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.RemoveUserResponse")
    @Action(input = "http://phonebook.org/ws/UserWS/removeUserRequest", output = "http://phonebook.org/ws/UserWS/removeUserResponse", fault = {
        @FaultAction(className = WSException.class, value = "http://phonebook.org/ws/UserWS/removeUser/Fault/ServiceException")
    })
    public void removeUser(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0)
        throws WSException
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns org.phonebook.ws.sei.user.User
     * @throws WSException
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://phonebook.org/ws", className = "org.phonebook.ws.sei.user.GetUserResponse")
    @Action(input = "http://phonebook.org/ws/UserWS/getUserRequest", output = "http://phonebook.org/ws/UserWS/getUserResponse", fault = {
        @FaultAction(className = WSException.class, value = "http://phonebook.org/ws/UserWS/getUser/Fault/ServiceException")
    })
    public User getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws WSException
    ;

}
