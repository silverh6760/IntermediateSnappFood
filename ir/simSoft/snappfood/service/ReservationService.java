package ir.simSoft.snappfood.service;

import ir.simSoft.snappfood.model.dao.ReservationDao;
import ir.simSoft.snappfood.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationService {

    ReservationDao reservationDao;
    @Autowired
    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Transactional
    public int getMaxFactorNumber() {
        int factorNumber=0;
        List<Integer> maxFactorNumber = reservationDao.getMaxFactorNumber();
        if (maxFactorNumber.get(0) != null) {
            factorNumber = (int) maxFactorNumber.get(0);
        }
        return factorNumber++;
    }
    @Transactional
    public void saveOrderToDB(Reservation reservation) {
        if(reservation !=null) {
            reservationDao.save(reservation);
        }else
            throw new RuntimeException("NUll Object");
    }
}
