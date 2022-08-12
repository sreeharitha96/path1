package com.path1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.path1.compute.Input;
import com.path1.compute.PathGenerator;
import com.path1.compute.Vertex;

@RestController
public class PathRestController {
  
  @PostMapping("/getPaths")
  public List<List<Vertex>> generatePaths(@Valid @RequestBody Input input) {
    return PathGenerator.generatePaths(input);
  }

}
