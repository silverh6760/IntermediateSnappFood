package controller;

import model.dao.ReservationDao;
import model.entity.Reservation;

public class ReservationService {

    ReservationDao reservationDao =new ReservationDao();

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
