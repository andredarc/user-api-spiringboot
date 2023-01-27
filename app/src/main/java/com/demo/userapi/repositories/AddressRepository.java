package com.demo.userapi.repositories;

import com.demo.userapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByIdIn(List<Long> ids);
}
