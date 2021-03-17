package com.ml.mutant.controller;

import com.ml.mutant.model.MutantModel;
import com.ml.mutant.services.MutantServices;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MutantController {

    @Autowired
    private MutantServices mutantServices;
    HttpHeaders headers = new HttpHeaders();

    @PostMapping("/mutant")
    public ResponseEntity<?> consumirDNA(@RequestBody String body) throws JSONException, JSONException {
        JSONObject jsonBody = new JSONObject(body);
        boolean result = mutantServices.isMutant(jsonBody);
        headers.setContentType(MediaType.TEXT_PLAIN);
        if (result ){
            return new ResponseEntity<>("Sr. Magneto: Buenas noticias! Encontramos un mutante",headers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Sr. Magneto: Malas noticias! El DNA procesado no es mutante.",headers, HttpStatus.FORBIDDEN);
        }
    }
    // boolean result = mutantServices.isMutant(jsonBody);


    // -> Se retorna un JSON con: count_mutant_dna = total ADN humanos evaluados, count_human_dna = total de ADN mutantes evaluados, ratio = ratio entre las dos evaluaciones
    @GetMapping("/stats")
    public ResponseEntity<?> status() throws JSONException {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(mutantServices.stats(),headers, HttpStatus.OK);

    }

}
