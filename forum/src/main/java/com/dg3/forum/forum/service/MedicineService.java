package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Medicine;

import java.util.List;

public interface MedicineService {
    /*
    * Create medicine
    * @param medicine
    * @return object medicine
    * */
    Medicine createMedicine (Medicine medicine);

    /*
    * Update medicine
    * @param medicine
    * */
    void updateMedicine(Medicine medicine);

    /*
    * Delete medicine
    * @param medicine
    * */
    void deleteMedicine(Long medicine_pk);

    /*
    * Check exist information medicine through primary key medicine_pk
    * @param medicine_pk
    * @return object medicine
    * */
    Medicine checkExistByMedicine(Long medicine_pk);

    /*
    * Check exist information name medicine
    * @param name_medicine and dealer_pk
    * @return object medicine
    * */
    Medicine checkExistsByName_Medicine(String name_medicine, Long dealer_pk);

    /*
    * List all information medicine by dealer
    * @param dealer_pk
    * @return list object medicine
    * */
    List<Medicine> listAllMedicine_Dealer(Long dealer_pk);
}
