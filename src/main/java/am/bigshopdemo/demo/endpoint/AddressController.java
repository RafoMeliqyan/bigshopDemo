package am.bigshopdemo.demo.endpoint;

import am.bigshopdemo.demo.model.Address;
import am.bigshopdemo.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/address/add")
    public void addAddress(@RequestBody Address address) {
        addressService.save(address);
    }

}
