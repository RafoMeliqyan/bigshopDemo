package am.bigshopdemo.demo.repository;

import am.bigshopdemo.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findByUserId(Integer userId);

}
