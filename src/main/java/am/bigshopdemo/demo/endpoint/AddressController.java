package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Address;
import am.bigshopdemo.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address/{userId}")
    public Address addresses(@PathVariable("userId") int userId) {
        return addressService.findByUserId(userId);
    }

    @PostMapping("/address/add")
    public void addAddress(@RequestBody Address address) {
        addressService.save(address);
    }

}
