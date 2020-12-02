package com.parcel.delivery.repository;

import com.parcel.delivery.domain.Parcel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParcelRepository extends MongoRepository<Parcel, String> {
   List<Parcel>  findByAgentId(String agentId);

}
