package com.restfultutorial.restApi.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.restfultutorial.restApi.controller.EmployeeController;
import com.restfultutorial.restApi.entity.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

  @Override
  public EntityModel<Employee> toModel(Employee employee) {

    return EntityModel.of(employee,
            linkTo(EmployeeController.class).slash(employee.getId()).withSelfRel(),
            linkTo(EmployeeController.class).withRel("employees"));
  }
}