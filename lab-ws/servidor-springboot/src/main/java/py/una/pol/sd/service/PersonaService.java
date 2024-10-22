package py.una.pol.sd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import py.una.pol.sd.model.Persona;
import py.una.pol.sd.repository.PersonaRepository;

@Service
public class PersonaService {


    @Autowired
    PersonaRepository repository;

    public Persona findById(Long id) {
        return repository.findByCedula(id);
    }
  
    public List<Persona> getPersonas(){

        return repository.findAll();
    }
    
    public Persona crear(Persona p){

        return repository.save(p);
    }

    public Persona actualizar(Long id, Persona p) {

        Persona persona = findById(id);
        if (persona != null){
            persona.setNombre(p.getNombre());
            persona.setApellido(p.getApellido());
            return repository.save(persona);
        }
        return null;
    }
    public void eliminar(Long id){
        Persona persona = findById(id);
        if (persona != null){
            repository.delete(persona);
        } else {
            System.out.println("No existe persona con id" + id);
        }

    }

}
