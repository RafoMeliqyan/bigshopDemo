package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.Address;
import am.bigshopdemo.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address findByUserId(int userId) {
        return addressRepository.findByUserId(userId);
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

}
