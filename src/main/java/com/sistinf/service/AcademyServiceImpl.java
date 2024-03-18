package com.sistinf.service;

import com.sistinf.entity.Academy;
import com.sistinf.exception.CustomException;
import com.sistinf.repository.AcademyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcademyServiceImpl implements AcademyService {

    @Value("${no.academies}")
    private String noAcademies;

    @Value("${no.academy}")
    private String noAcademy;


    private AcademyRepository academyRepository;

    public AcademyServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }


    @Override
    public Academy checkSaveOrUpdate(Academy academy) {

        Academy savedOrUpdated = null;

        try{
            savedOrUpdated= academyRepository.save(academy);

        } catch(IllegalArgumentException | OptimisticLockingFailureException e){
            e.printStackTrace();
        }
        return savedOrUpdated;
    }

    @Override
    public List<Academy> checkFindAllAcademies() throws CustomException {
        List<Academy> academies= academyRepository.findAll();

        if(academies.isEmpty()){
            throw new CustomException(noAcademies);
        }
        return academies;
    }

    @Override
    public Academy checkFindAcademyByCode(String code) throws CustomException {

        //il metodo findById restituisce un oggetto java che rappresenta il contenuto informativo
        //relativo ad un record con una specifica chiave primaria passata in input al metodo.
        //è un metodo funzionale. possiamo invocare con l'operatore dot"." una funzione di nome
        //orElseThrow alla quale passare in input una eccezione.

        return academyRepository.findById(code).orElseThrow(() -> new CustomException(noAcademy));

    }

    @Override
    public Map<String, Boolean> checkRemoveAcademy(String code) {

        Map<String,Boolean> removedMap= new HashMap<>();

        try{
            academyRepository.deleteById(code);
            removedMap.put("deletion",true);
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        }
        return removedMap;
    }
}
