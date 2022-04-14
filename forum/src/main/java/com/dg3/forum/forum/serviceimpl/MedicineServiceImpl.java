package com.dg3.forum.forum.serviceimpl;

import com.dg3.forum.forum.entity.Medicine;
import com.dg3.forum.forum.repository.MedicineRepository;
import com.dg3.forum.forum.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine createMedicine (Medicine medicine){
        return medicineRepository.save(medicine);
    }

    @Override
    public void updateMedicine (Medicine medicine){
        String name_medicine = medicine.getName_medicine();
        String where_production = medicine.getWhere_production();
        String price = medicine.getPrice();
        String effect = medicine.getEffect();
        String details_medicine = medicine.getDetails_medicine();
        boolean enable_medicine = medicine.isEnable_medicine();
        Long medicine_pk = medicine.getMedicine_pk();
        Long dealer_pk = medicine.getDealer_pk();

        medicineRepository.updateMedicine(name_medicine, where_production, price, effect, details_medicine, enable_medicine, medicine_pk, dealer_pk);
    }

    @Override
    public void deleteMedicine(Long medicine_pk){
        medicineRepository.deleteMedicine(medicine_pk);
    }
}
