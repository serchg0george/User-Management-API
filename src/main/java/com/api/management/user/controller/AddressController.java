package com.api.management.user.controller;

import com.api.management.user.dto.AddressDto;
import com.api.management.user.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.management.user.controller.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<String> createAddress(@Valid @RequestBody AddressDto address) {
        addressService.save(address);
        return new ResponseEntity<>(CREATE_SUCCESS, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Long addressId) {
        return new ResponseEntity<>(addressService.get(addressId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AddressDto>> getAllAddresses(Pageable pageable) {
        return new ResponseEntity<>(addressService.getAll(pageable), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateAddress(@PathVariable("id") Long addressId,
                                                    @Valid @RequestBody AddressDto address) {
        addressService.update(address, addressId);
        return new ResponseEntity<>(UPDATE_SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long addressId) {
        addressService.delete(addressId);
        return new ResponseEntity<>(DELETE_SUCCESS, HttpStatus.OK);
    }
}