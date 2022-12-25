package com.restfultutorial.restApi.controller;

import java.util.ArrayList;
import java.util.List;

import com.restfultutorial.restApi.entity.Employee;
import com.restfultutorial.restApi.exceptions.EmployeeNotFoundException;
import com.restfultutorial.restApi.hateoas.EmployeeModelAssembler;
import com.restfultutorial.restApi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

  private final EmployeeRepository repository ;

  @Autowired
  private EmployeeModelAssembler assembler;

 public EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping
  CollectionModel<EntityModel<Employee>>  all() {
    List<Employee> employees = repository.findAll();
    List<EntityModel<Employee>> entityModelList = new ArrayList<>();
    for(Employee employee : employees){
        entityModelList.add(assembler.toModel(employee));
    }
    return CollectionModel.of(entityModelList,linkTo(EmployeeController.class).withSelfRel());
  }
  // end::get-aggregate-root[]

  @PostMapping
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  
  @GetMapping("/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {

    Employee employee = repository.findById(id) //
            .orElseThrow(() -> new EmployeeNotFoundException(id));


//    Link selfLink = linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel();
    return assembler.toModel(employee);
  }



  @PutMapping("/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(employee -> {
        employee.setName(newEmployee.getName());
        employee.setRole(newEmployee.getRole());
        return repository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return repository.save(newEmployee);
      });
  }

  @DeleteMapping("/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }
}