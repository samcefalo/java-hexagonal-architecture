package me.samcefalo.courses.java.hexagonal.adapters.in.controller;

import jakarta.validation.Valid;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.request.CustomerRequest;
import me.samcefalo.courses.java.hexagonal.adapters.in.controller.response.CustomerResponse;
import me.samcefalo.courses.java.hexagonal.application.core.domain.Customer;
import me.samcefalo.courses.java.hexagonal.application.exceptions.CustomerNotFoundException;
import me.samcefalo.courses.java.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.in.InsertCustomerInputPort;
import me.samcefalo.courses.java.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final InsertCustomerInputPort insertCustomerInputPort;
    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final UpdateCustomerInputPort updateCustomerInputPort;
    private final DeleteCustomerByIdInputPort deleteCustomerByIdInputPort;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(InsertCustomerInputPort insertCustomerInputPort, FindCustomerByIdInputPort findCustomerByIdInputPort, UpdateCustomerInputPort updateCustomerInputPort, DeleteCustomerByIdInputPort deleteCustomerByIdInputPort, CustomerMapper customerMapper) {
        this.insertCustomerInputPort = insertCustomerInputPort;
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.updateCustomerInputPort = updateCustomerInputPort;
        this.deleteCustomerByIdInputPort = deleteCustomerByIdInputPort;
        this.customerMapper = customerMapper;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        insertCustomerInputPort.insert(customer, customerRequest.getZipCode());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id) throws CustomerNotFoundException {
        Customer customer = findCustomerByIdInputPort.find(id);
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customer);

        return ResponseEntity.ok().body(customerResponse);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody CustomerRequest customerRequest) throws CustomerNotFoundException {
        Customer customer = customerMapper.toCustomer(customerRequest);
        customer.setId(id);

        updateCustomerInputPort.update(customer, customerRequest.getZipCode());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String id) throws CustomerNotFoundException {
        deleteCustomerByIdInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
