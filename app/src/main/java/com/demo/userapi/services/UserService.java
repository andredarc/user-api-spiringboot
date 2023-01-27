package com.demo.userapi.services;

import com.demo.userapi.models.Address;
import com.demo.userapi.models.User;
import com.demo.userapi.repositories.AddressRepository;
import com.demo.userapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    public List<User> getAllUsers() {
        System.out.println("Init Service endpoint - get all services");
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return new ArrayList<>();
        }

        System.out.println("End Service endpoint - get all services");
        return users;
    }

    public Optional<User> getUserById(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user;
        } else {
            throw new Exception("Usuario não encontrado");
        }

    }

    public Optional<List<Address>> getUserAddressesByUserId(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            var addressesIds = user.get().getAddresses().stream().map(Address::getId).collect(Collectors.toList());
            var addresses = addressRepository.findByIdIn(addressesIds);
            if (!addresses.isEmpty()) {
                return Optional.of(addresses);
            } else {
                throw new Exception("Endereco não encontrado");
            }
        } else {
            throw new Exception("Usuario não encontrado");
        }
    }

    public User createUser(User user) throws Exception {
        System.out.println("Init Service endpoint - create user");

        try {
            var hasPrincipalAddress = user.getAddresses().stream().filter(Address::getIsPrincipal).collect(Collectors.toList());
            if (hasPrincipalAddress.isEmpty()) {
                user.getAddresses().get(0).setIsPrincipal(true);
            }
            List<Address> addressList = new ArrayList<>();
            user.getAddresses().forEach(a -> {
                var address = addressRepository.save(a);
                addressList.add(address);
            });
            user.setAddresses(addressList);
            return userRepository.save(user);

        } catch (Exception e) {
            throw new Exception("Erro ao inserir usuario: ", e);
        }
    }

    public User updateUser(long id, User user) throws Exception {
        User foundUser = userRepository.getOne(id);

        if (null != user.getName() && !user.getName().isEmpty()) {
            foundUser.setName(user.getName());
        }

        if (null != user.getBirthday() && !user.getBirthday().isEmpty()) {
            foundUser.setBirthday(user.getBirthday());
        }

        if (null != user.getAddresses()) {
            user.getAddresses().forEach(a -> {
                if (foundUser.getAddresses().stream().anyMatch(add -> {
                    return add.getId().equals(a.getId());
                })) {
                    verifyIfHasPrincipal(foundUser, a);
                } else {
                    if (null == a.getId()) {
                        verifyIfHasPrincipal(foundUser, a);
                    }
                }
            });
        }

        foundUser.setAddresses(foundUser.getAddresses().stream().distinct().collect(Collectors.toList()));
        return userRepository.save(foundUser);
    }

    private void verifyIfHasPrincipal(User foundUser, Address a) {
        var hasPrincipal = foundUser.getAddresses().stream().anyMatch(Address::getIsPrincipal);
        if (!hasPrincipal) {
            a.setIsPrincipal(true);
        } else {
            a.setIsPrincipal(false);
        }
        Address address = addressRepository.save(a);
        foundUser.getAddresses().add(address);
    }
}
