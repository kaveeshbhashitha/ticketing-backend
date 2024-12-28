package com.ticketing.ticketing_backend.Repository;

import com.ticketing.ticketing_backend.Dto.DailyIncomeDto;
import com.ticketing.ticketing_backend.Model.Payment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByUserId(String userId);
    List<Payment> findByReservationId(String reservationId);
    @Aggregation(pipeline = {
            "{ '$group': { '_id': '$paymentDate', 'totalIncome': { '$sum': '$amount' } } }",
            "{ '$project': { 'date': '$_id', 'totalIncome': 1, '_id': 0 } }"
    })
    List<DailyIncomeDto> aggregateDailyIncome();
}
