package com.navulia.audit.service;

import com.navulia.audit.dto.SupplierInputDTO;
import com.navulia.audit.dto.SupplierOutputDTO;

import java.util.List;

public interface SupplierService {

    public List<SupplierOutputDTO> getAllSuppliers();

    public SupplierOutputDTO getSupplierById(Long id);

    public SupplierOutputDTO createSupplier(SupplierInputDTO supplierDto);

    public SupplierOutputDTO updateSupplier(long id, SupplierInputDTO supplierDto);

}
