package py.una.pol.sd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import py.una.pol.sd.model.Persona;
import py.una.pol.sd.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	PersonaService personaService;

	@GetMapping("/saludo")
	public String index() {
		return "Hola mundo caluroso de Springboot";
	}

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<List<Persona>> getPersonas() 
	{
		/*List<PersonaDTO> r =  new ArrayList<PersonaDTO>();
		r.add(new PersonaDTO(2000L, "Juan", "Perez"));
		r.add(new PersonaDTO(2001L, "Pedro", "Alonso"));
		r.add(new PersonaDTO(2002L, "Maria", "Lopez"));
		r.add(new PersonaDTO(2003L, "Rosana", "Romero"));
		r.add(new PersonaDTO(2004L, "Liz", "Santos"));
		r.add(new PersonaDTO(2005L, "Luis", "Cabral"));
		*/
		List<Persona> r = personaService.getPersonas();

		return new ResponseEntity<>(r, HttpStatus.OK);
    }


	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Persona per) {


		if(per != null && per.getCedula() != null) {
			System.out.println("Persona recepcionada "+ per.getNombre());
			
			personaService.crear(per); 

			return new ResponseEntity<>("Se recepcionó correctamente la persona: " + per.toString(), HttpStatus.OK);
		}else{

			System.out.println("Datos mal enviados por el cliente");
			return new ResponseEntity<>("Debe enviar el campo cédula. ", HttpStatus.BAD_REQUEST);
		}

    }

	@PutMapping(value = "/actualizar/{cedula}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@PathVariable Long cedula, @RequestBody Persona per) {
		Persona p = personaService.actualizar(cedula, per);
		if (p != null && p.getCedula() != null){
			return new ResponseEntity<>("Se actualizo la persona: " + p, HttpStatus.OK);
		}
		return new ResponseEntity<>("No se encontro la persona", HttpStatus.NOT_FOUND);

	}

	@DeleteMapping(value = "/borrar/{cedula}")
	public ResponseEntity<String> delete(@PathVariable Long cedula) {
		Persona p = personaService.findById(cedula);
		if (p != null){
			personaService.eliminar(cedula);
			return new ResponseEntity<>("Se elimino la persona: " + p, HttpStatus.OK);
		}
		return new ResponseEntity<>("No se encontro la persona", HttpStatus.NOT_FOUND);
	}



}
