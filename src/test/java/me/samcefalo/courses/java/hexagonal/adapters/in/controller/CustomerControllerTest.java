package me.samcefalo.courses.java.hexagonal.adapters.in.controller;

import me.samcefalo.courses.java.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.request.CustomerRequest;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.request.CustomerRequestMother;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.response.CustomerResponse;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.response.CustomerResponseMother;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.core.domain.CustomerMother;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;
import me.samcefalo.courses.java.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.in.InsertCustomerInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerControllerTest {
    private InsertCustomerInputPort insertCustomerInputPort;
    private FindCustomerByIdInputPort findCustomerByIdInputPort;
    private UpdateCustomerInputPort updateCustomerInputPort;
    private DeleteCustomerByIdInputPort deleteCustomerByIdInputPort;
    private CustomerMapper customerMapper;
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        insertCustomerInputPort = mock(InsertCustomerInputPort.class);
        findCustomerByIdInputPort = mock(FindCustomerByIdInputPort.class);
        updateCustomerInputPort = mock(UpdateCustomerInputPort.class);
        deleteCustomerByIdInputPort = mock(DeleteCustomerByIdInputPort.class);
        customerMapper = mock(CustomerMapper.class);
        customerController = new CustomerController(insertCustomerInputPort, findCustomerByIdInputPort, updateCustomerInputPort, deleteCustomerByIdInputPort, customerMapper);
    }

    @Test
    public void should_insert_customer() {
        CustomerRequest request = CustomerRequestMother.create();
        Customer customer = CustomerMother.create();

        when(customerMapper.toCustomer(request)).thenReturn(customer);
        doNothing().when(insertCustomerInputPort).insert(customer, request.getZipCode());

        ResponseEntity<Void> actual = customerController.insert(request);

        assertEquals(HttpStatus.OK.value(), actual.getStatusCode().value());
        verify(customerMapper, times(1)).toCustomer(request);
        verify(insertCustomerInputPort, times(1)).insert(customer, request.getZipCode());
    }

    @Test
    public void should_find_by_id() throws CustomerNotFoundException {
        String id = "1";
        CustomerResponse expected = CustomerResponseMother.create();
        Customer customer = CustomerMother.create();

        when(findCustomerByIdInputPort.find(id)).thenReturn(customer);
        when(customerMapper.toCustomerResponse(customer)).thenReturn(expected);

        ResponseEntity<CustomerResponse> actual = customerController.findById(id);

        assertEquals(HttpStatus.OK.value(), actual.getStatusCode().value());
        verify(customerMapper, times(1)).toCustomerResponse(customer);
        verify(findCustomerByIdInputPort, times(1)).find(id);
    }

    @Test
    public void should_find_by_id_with_customer_not_found_exception() throws CustomerNotFoundException {
        String id = "1";

        doThrow(CustomerNotFoundException.class).when(findCustomerByIdInputPort).find(id);

        assertThrows(CustomerNotFoundException.class, () -> customerController.findById(id));
    }

    @Test
    public void should_update_customer() throws CustomerNotFoundException {
        String id = "1";
        CustomerRequest request = CustomerRequestMother.create();
        Customer customer = CustomerMother.create();

        when(customerMapper.toCustomer(request)).thenReturn(customer);
        doNothing().when(updateCustomerInputPort).update(customer, request.getZipCode());

        ResponseEntity<Void> actual = customerController.update(id, request);

        assertEquals(HttpStatus.NO_CONTENT.value(), actual.getStatusCode().value());
        assertEquals(id, customer.getId());
        verify(customerMapper, times(1)).toCustomer(request);
        verify(updateCustomerInputPort, times(1)).update(customer, request.getZipCode());
    }

    @Test
    public void should_update_customer_with_customer_not_found_exception() throws CustomerNotFoundException {
        String id = "1";
        CustomerRequest request = CustomerRequestMother.create();
        Customer customer = CustomerMother.create();

        when(customerMapper.toCustomer(request)).thenReturn(customer);
        doThrow(CustomerNotFoundException.class).when(updateCustomerInputPort).update(customer, request.getZipCode());

        assertThrows(CustomerNotFoundException.class, () -> customerController.update(id, request));
    }

    @Test
    public void should_delete_customer() throws CustomerNotFoundException {
        String id = "1";

        doNothing().when(deleteCustomerByIdInputPort).delete(id);

        ResponseEntity<Void> actual = customerController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT.value(), actual.getStatusCode().value());
        verify(deleteCustomerByIdInputPort, times(1)).delete(id);
    }

    @Test
    public void should_delete_customer_with_customer_not_found_exception() throws CustomerNotFoundException {
        String id = "1";

        doThrow(CustomerNotFoundException.class).when(deleteCustomerByIdInputPort).delete(id);

        assertThrows(CustomerNotFoundException.class, () -> customerController.delete(id));
    }
}