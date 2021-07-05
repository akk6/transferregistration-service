package au.gov.nsw.revenue.transferregistrationservice.dao;

import au.gov.nsw.revenue.transferregistrationservice.entities.PersonEntity;
import au.gov.nsw.revenue.transferregistrationservice.exception.PersonAlreadyExistsException;
import au.gov.nsw.revenue.transferregistrationservice.exception.PersonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScope
@Component
@Slf4j
public class PersonRepository {

    private Map<String,PersonEntity> persons = Collections.synchronizedMap(new HashMap<>());

    public PersonEntity add(PersonEntity personEntity) {
        persons.merge(personEntity.getEmailId(), personEntity, (v1, v2) -> {
            throw new PersonAlreadyExistsException("Customer Already Exists with Email Address '" + personEntity.getEmailId() + "'.");
        });
        return personEntity;
    }

    public PersonEntity findByEmailIdAmdMobileNumber(String emailId,String mobileNumber) {
        PersonEntity personEntity = this.persons.get(emailId);
   if(personEntity!=null){
        if(StringUtils.isNotEmpty(mobileNumber)){
            if(personEntity.getMobileNumber().equals(mobileNumber)){
                return personEntity;
            }
        }
        else{
            return personEntity;
        }
   }
        throw new PersonNotFoundException("Customer Not found for parameters provided");
    }
}
