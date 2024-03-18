package com.sistinf.service;

import com.sistinf.entity.Academy;
import com.sistinf.exception.CustomException;



import java.util.List;
import java.util.Map;

public interface AcademyService {

        public Academy checkSaveOrUpdate(Academy academy);

        public List<Academy> checkFindAllAcademies() throws CustomException;

        public Academy checkFindAcademyByCode(String code) throws CustomException;

        public Map<String,Boolean> checkRemoveAcademy(String code);

}
