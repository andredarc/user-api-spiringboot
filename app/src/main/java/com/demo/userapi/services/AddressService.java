package com.demo.userapi.services;

import com.demo.userapi.models.Address;
import com.demo.userapi.models.User;
import com.demo.userapi.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) throws Exception {
        System.out.println("Init Service endpoint - create address");

        try {
            var addressCreated = addressRepository.save(address);
            return addressCreated;

        } catch (Exception e) {
            throw new Exception("Erro ao inserir endereco: ", e);
        }
    }

    public List<Address> getAllAddresses() {
        System.out.println("Init Service endpoint - get all addresses");
        List<Address> users = addressRepository.findAll();

        if(users.isEmpty()) {
            return new ArrayList<>();
        }

        System.out.println("End Service endpoint - get all addresses");
        return users;
    }

    public Optional<Address> getAddressById(long id) throws Exception {
        Optional<Address> user = addressRepository.findById(id);

        if(user.isPresent()) {
            return user;
        } else {
            throw new Exception("Endereco não encontrado");
        }

    }
}
