
package com.alkemy.ong.service;

import javax.persistence.EntityNotFoundException;


public interface SlideService {
    
  public void deleteSlide(Long id) throws EntityNotFoundException;  
}
