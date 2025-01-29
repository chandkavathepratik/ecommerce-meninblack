package com.meninblack.repositories;

import com.meninblack.entities.sub.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, ObjectId> {
}
