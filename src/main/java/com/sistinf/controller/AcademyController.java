package com.sistinf.controller;


import com.sistinf.entity.Academy;
import com.sistinf.exception.CustomException;
import com.sistinf.service.AcademyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
GET http://localhost:8080/rest/api/academies
GET http://localhost:8080/rest/api/academies/code/{code}
POST http://localhost:8080/rest/api/academies
PUT http://localhost:8080/rest/api/academies
DELETE http://localhost:8080/rest/api/academies/{code}
*/

/*
@RestController fa si che la classe diventi Restful Web Service Provider.
in grado di ricevere file JSON e di restituire file JSON.

 */

//se uso postman non serve. è un'annotation spring che consente di elencare LE ORIGINI di consumo
//che sono abilitate al consumo delle operazioni rest esposte da un web service provider.
// posso aggiungere: @CrossOrigin(localhost:3000) per usare solo quella porta. cosi è aperto invece a tutte le porte.

@CrossOrigin
@RestController
@RequestMapping("/rest/api/academies")
public class AcademyController {

    private final AcademyService academyService;
    public AcademyController(AcademyService academyService) {
        this.academyService = academyService;
    }

    @GetMapping
    public List<Academy> findAllAcademies() throws CustomException {

        /* nel caso positivo il metodo del service ritorna una list di academy
        che essendo oggetto java viene trasformata dall'object mapper in JSON.
         */
        return academyService.checkFindAllAcademies();
    }

    /*
    @PathVariable va utilizzata per chiedere a Spring Restful
    di convertire la variabile di path inserita della url del browser da parte del consumer
    in una variabile java.
     */
    @GetMapping("/code/{code}")
    public Academy findAcademyByCode(@PathVariable String code) throws CustomException {
        return academyService.checkFindAcademyByCode(code);
    }

    @PostMapping
    public Academy saveAcademy(@Valid @RequestBody Academy academy){
        return academyService.checkSaveOrUpdate(academy);
    }

    @PutMapping
    public Academy updateAcademy(@RequestBody Academy academy){
        return academyService.checkSaveOrUpdate(academy);
    }

    @DeleteMapping("/code/{code}")
    public Map<String,Boolean> removeAcademy(@PathVariable String code){
        return academyService.checkRemoveAcademy(code);
    }
}
