package ma.enset.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ebankingbackend.dtos.CustomerDTO;
import ma.enset.ebankingbackend.entities.Customer;
import ma.enset.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.enset.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor @Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listcustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword" , defaultValue = "") String keyword){
        return bankAccountService.searchcustomers(keyword);
    }


    @GetMapping("/customers/{id}")
    public CustomerDTO getCutomer(@PathVariable(name = "id") long customerId) throws CustomerNotFoundException {
            return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public  CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
       return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDTO UpdateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.UpdateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void DeleteCustomer(@PathVariable Long id){
            bankAccountService.DeleteCustomer(id);
    }

}
