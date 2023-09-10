package com.beyzanuryuksel.amadeuscasestudy.converter;

import com.beyzanuryuksel.amadeuscasestudy.entity.Airplane;
import com.beyzanuryuksel.amadeuscasestudy.model.AirplaneResponse;
import com.beyzanuryuksel.amadeuscasestudy.model.CreateAirplane;
import com.beyzanuryuksel.amadeuscasestudy.model.UpdateAirplane;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AirplaneConverter {

    public AirplaneResponse convertToAirplaneResponse(Airplane airplane) {
        AirplaneResponse airplaneResponse = new AirplaneResponse();

        if (airplane != null) {
            BeanUtils.copyProperties(airplane, airplaneResponse);
        }
        return airplaneResponse;
    }

    public Airplane convertToEntity(CreateAirplane airplane) {
        Airplane airplaneEntity = new Airplane();
        airplaneEntity.setType(airplane.getType());
        airplaneEntity.setIsActive(airplane.getIsActive());
        airplaneEntity.setSeatCapacity(airplane.getSeatCapacity());
        return airplaneEntity;
    }

    public Airplane convertUpdateModelToEntity(UpdateAirplane updateAirplane) {
        Airplane airplane = new Airplane();
        if (updateAirplane != null) {
            BeanUtils.copyProperties(updateAirplane, airplane);
        }
        return airplane;
    }
}
