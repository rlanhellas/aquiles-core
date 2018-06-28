package br.com.aquiles.core.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * This service should be used when you don't need any special logic, so
 * you don't need to create a Service with empty body. Just use BasicService
 * @author  Ronaldo Lanhellas
 * */
@Named
@RequestScoped
public class BasicService extends GenericService {
}
