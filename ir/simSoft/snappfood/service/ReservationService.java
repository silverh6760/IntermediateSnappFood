package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.ReservationDao;
import ir.simSoft.snappfood.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationService {

    ReservationDao reservationDao;
    @Autowired
    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public int getMaxFactorNumber() {
        int factorNumber= reservationDao.getMaxFactorNumber();
        return factorNumber;
    }

    public void saveOrderToDB(Reservation reservation) {
        if(reservation !=null) {
            reservationDao.saveOrderToDB(reservation);
        }else
            throw new RuntimeException("NUll Object");
    }
}
