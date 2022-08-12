package com.path1.compute;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Input {

  @NotNull
  private List<Vertex> vertices;
  
  @NotNull
  private Vertex source;
  
  @Min(2)
  @Max(8)
  private int numOfCabs;
  
  @Max(8)
  private int numPerCar;
  
  /**
   * @return the vertices
   */
  public List<Vertex> getVertices() {
    return vertices;
  }
  /**
   * @return the source
   */
  public Vertex getSource() {
    return source;
  }
  /**
   * @return the numOfCabs
   */
  public int getNumOfCabs() {
    return numOfCabs;
  }
  /**
   * @return the numPerCar
   */
  public int getNumPerCar() {
    return numPerCar;
  }
  
  
}
