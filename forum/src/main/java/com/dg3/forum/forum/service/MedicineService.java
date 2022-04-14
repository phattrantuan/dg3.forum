package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.Medicine;

public interface MedicineService {
    /*
    * Create medicine
    *
    * */
    Medicine createMedicine (Medicine medicine);

    /*
    *
    * */
    void updateMedicine(Medicine medicine);

    /*
    *
    * */
    void deleteMedicine(Long medicine_pk);
}
