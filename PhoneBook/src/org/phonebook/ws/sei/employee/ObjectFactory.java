
package org.phonebook.ws.sei.employee;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.phonebook.beans.Employee;
import org.phonebook.beans.Room;
import org.phonebook.exceptions.ServiceException;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.phonebook.ws.sei.employee package. 
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

    private final static QName _Update_QNAME = new QName("http://phonebook.org/ws", "update");
    private final static QName _FindEmployees_QNAME = new QName("http://phonebook.org/ws", "findEmployees");
    private final static QName _GetAllEmployees_QNAME = new QName("http://phonebook.org/ws", "getAllEmployees");
    private final static QName _Remove_QNAME = new QName("http://phonebook.org/ws", "remove");
    private final static QName _RemoveResponse_QNAME = new QName("http://phonebook.org/ws", "removeResponse");
    private final static QName _AddEmployee_QNAME = new QName("http://phonebook.org/ws", "addEmployee");
    private final static QName _AddEmployeeResponse_QNAME = new QName("http://phonebook.org/ws", "addEmployeeResponse");
    private final static QName _UpdateResponse_QNAME = new QName("http://phonebook.org/ws", "updateResponse");
    private final static QName _GetAllEmployeesResponse_QNAME = new QName("http://phonebook.org/ws", "getAllEmployeesResponse");
    private final static QName _FindEmployeesResponse_QNAME = new QName("http://phonebook.org/ws", "findEmployeesResponse");
    private final static QName _Employee_QNAME = new QName("http://phonebook.org/ws", "employee");
    private final static QName _Room_QNAME = new QName("http://phonebook.org/ws", "room");
    private final static QName _ServiceException_QNAME = new QName("http://phonebook.org/ws", "ServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.phonebook.ws.sei.employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link GetAllEmployees }
     * 
     */
    public GetAllEmployees createGetAllEmployees() {
        return new GetAllEmployees();
    }

    /**
     * Create an instance of {@link Remove }
     * 
     */
    public Remove createRemove() {
        return new Remove();
    }

    /**
     * Create an instance of {@link FindEmployees }
     * 
     */
    public FindEmployees createFindEmployees() {
        return new FindEmployees();
    }

    /**
     * Create an instance of {@link RemoveResponse }
     * 
     */
    public RemoveResponse createRemoveResponse() {
        return new RemoveResponse();
    }

    /**
     * Create an instance of {@link AddEmployee }
     * 
     */
    public AddEmployee createAddEmployee() {
        return new AddEmployee();
    }

    /**
     * Create an instance of {@link AddEmployeeResponse }
     * 
     */
    public AddEmployeeResponse createAddEmployeeResponse() {
        return new AddEmployeeResponse();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link GetAllEmployeesResponse }
     * 
     */
    public GetAllEmployeesResponse createGetAllEmployeesResponse() {
        return new GetAllEmployeesResponse();
    }

    /**
     * Create an instance of {@link FindEmployeesResponse }
     * 
     */
    public FindEmployeesResponse createFindEmployeesResponse() {
        return new FindEmployeesResponse();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEmployees }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "findEmployees")
    public JAXBElement<FindEmployees> createFindEmployees(FindEmployees value) {
        return new JAXBElement<FindEmployees>(_FindEmployees_QNAME, FindEmployees.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllEmployees }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getAllEmployees")
    public JAXBElement<GetAllEmployees> createGetAllEmployees(GetAllEmployees value) {
        return new JAXBElement<GetAllEmployees>(_GetAllEmployees_QNAME, GetAllEmployees.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Remove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "remove")
    public JAXBElement<Remove> createRemove(Remove value) {
        return new JAXBElement<Remove>(_Remove_QNAME, Remove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "removeResponse")
    public JAXBElement<RemoveResponse> createRemoveResponse(RemoveResponse value) {
        return new JAXBElement<RemoveResponse>(_RemoveResponse_QNAME, RemoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEmployee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "addEmployee")
    public JAXBElement<AddEmployee> createAddEmployee(AddEmployee value) {
        return new JAXBElement<AddEmployee>(_AddEmployee_QNAME, AddEmployee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddEmployeeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "addEmployeeResponse")
    public JAXBElement<AddEmployeeResponse> createAddEmployeeResponse(AddEmployeeResponse value) {
        return new JAXBElement<AddEmployeeResponse>(_AddEmployeeResponse_QNAME, AddEmployeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllEmployeesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "getAllEmployeesResponse")
    public JAXBElement<GetAllEmployeesResponse> createGetAllEmployeesResponse(GetAllEmployeesResponse value) {
        return new JAXBElement<GetAllEmployeesResponse>(_GetAllEmployeesResponse_QNAME, GetAllEmployeesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindEmployeesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "findEmployeesResponse")
    public JAXBElement<FindEmployeesResponse> createFindEmployeesResponse(FindEmployeesResponse value) {
        return new JAXBElement<FindEmployeesResponse>(_FindEmployeesResponse_QNAME, FindEmployeesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Employee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "employee")
    public JAXBElement<Employee> createEmployee(Employee value) {
        return new JAXBElement<Employee>(_Employee_QNAME, Employee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Room }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://phonebook.org/ws", name = "room")
    public JAXBElement<Room> createRoom(Room value) {
        return new JAXBElement<Room>(_Room_QNAME, Room.class, null, value);
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
