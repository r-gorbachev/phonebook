
package org.phonebook.ws.sei.role;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.phonebook.beans.Role;
import org.phonebook.exceptions.ServiceException;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.phonebook.ws.sei.role package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRoleByNameResponse_QNAME = new QName("http://phonebook.org/ws", "getRoleByNameResponse");
    private final static QName _GetAllRolesResponse_QNAME = new QName("http://phonebook.org/ws", "getAllRolesResponse");
    private final static QName _GetRoleByIdResponse_QNAME = new QName("http://phonebook.org/ws", "getRoleByIdResponse");
    private final static QName _Role_QNAME = new QName("http://phonebook.org/ws", "role");
    private final static QName _GetAllRoles_QNAME = new QName("http://phonebook.org/ws", "getAllRoles");
    private final static QName _GetRoleById_QNAME = new QName("http://phonebook.org/ws", "getRoleById");
    private final static QName _GetRoleByName_QNAME = new QName("http://phonebook.org/ws", "getRoleByName");
    private final static QName _ServiceException_QNAME = new QName("http://phonebook.org/ws", "ServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.phonebook.ws.sei.role
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRoleByNameResponse }
     * 
     */
    public GetRoleByNameResponse createGetRoleByNameResponse() {
        return new GetRoleByNameResponse();
    }

    /**
     * Create an instance of {@link GetRoleByIdResponse }
     * 
     */
    public GetRoleByIdResponse createGetRoleByIdResponse() {
        return new GetRoleByIdResponse();
    }

    /**
     * Create an instance of {@link GetAllRolesResponse }
     * 
     */
    public GetAllRolesResponse createGetAllRolesResponse() {
        return new GetAllRolesResponse();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link GetRoleByName }
     * 
     */
    public GetRoleByName createGetRoleByName() {
        return new GetRoleByName();
    }

    /**
     * Create an instance of {@link GetRoleById }
     * 
     */
    public GetRoleById createGetRoleById() {
        return new GetRoleById();
    }

    /**
     * Create an instance of {@link GetAllRoles }
     * 
     */
    public GetAllRoles createGetAllRoles() {
        return new GetAllRoles();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getRoleByNameResponse")
    public JAXBElement<GetRoleByNameResponse> createGetRoleByNameResponse(GetRoleByNameResponse value) {
        return new JAXBElement<GetRoleByNameResponse>(_GetRoleByNameResponse_QNAME, GetRoleByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRolesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getAllRolesResponse")
    public JAXBElement<GetAllRolesResponse> createGetAllRolesResponse(GetAllRolesResponse value) {
        return new JAXBElement<GetAllRolesResponse>(_GetAllRolesResponse_QNAME, GetAllRolesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getRoleByIdResponse")
    public JAXBElement<GetRoleByIdResponse> createGetRoleByIdResponse(GetRoleByIdResponse value) {
        return new JAXBElement<GetRoleByIdResponse>(_GetRoleByIdResponse_QNAME, GetRoleByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Role }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "role")
    public JAXBElement<Role> createRole(Role value) {
        return new JAXBElement<Role>(_Role_QNAME, Role.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getAllRoles")
    public JAXBElement<GetAllRoles> createGetAllRoles(GetAllRoles value) {
        return new JAXBElement<GetAllRoles>(_GetAllRoles_QNAME, GetAllRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getRoleById")
    public JAXBElement<GetRoleById> createGetRoleById(GetRoleById value) {
        return new JAXBElement<GetRoleById>(_GetRoleById_QNAME, GetRoleById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getRoleByName")
    public JAXBElement<GetRoleByName> createGetRoleByName(GetRoleByName value) {
        return new JAXBElement<GetRoleByName>(_GetRoleByName_QNAME, GetRoleByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "ServiceException")
    public JAXBElement<ServiceException> createServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_ServiceException_QNAME, ServiceException.class, null, value);
    }

}
